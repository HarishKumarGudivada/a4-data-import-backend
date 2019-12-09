package com.a4data.etl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.a4data.etl.constants.A4dataConstants;
import com.a4data.etl.exception.ETLMigrationException;
import com.a4data.etl.exception.ErrorCodes;
import com.a4data.etl.util.CreateBeanDynamically;
import com.a4data.etl.util.QueryBuilder;

@Service
public class DataMigrationService {
	
	
	@Autowired
	@Qualifier("databases")
	private Map<String,JdbcTemplate> dbList;
	
	@Autowired
	CreateBeanDynamically createBeans;
	
	@Autowired
	QueryBuilder queryBuilder;
	
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	JdbcTemplate srcDB;
	
	public List<String> loadListOfDatabases(){
		List<String> list = new ArrayList<>();
		list.add("mysqldb");
		list.add("postgresdb");
		return list;
	}
	
	
	public void registerDatabases(String sourceDBName,String destinationDBName,HttpServletRequest request){
		request.getSession().setAttribute(A4dataConstants.SOURCE_DB , dbList.get(sourceDBName));
		request.getSession().setAttribute(A4dataConstants.DESTINATION_DB, dbList.get(destinationDBName));
	}
	
	
	public JdbcTemplate loadSourceDB(HttpServletRequest request) {
		return (JdbcTemplate) request.getSession().getAttribute(A4dataConstants.SOURCE_DB);
	}
	
	public JdbcTemplate loadDestinationDB(HttpServletRequest request) {
		return (JdbcTemplate) request.getSession().getAttribute(A4dataConstants.DESTINATION_DB);
	}
	
	
	public List<String> getTableNames(HttpServletRequest request){
		JdbcTemplate sourceDB = loadSourceDB(request);
		String query = queryBuilder.getTableNames();
		//String query = "SHOW tables;";
		List<String> tableNames = sourceDB.queryForList(query, String.class);
		return tableNames;
	}
	
	
	public List<Map<String, Object>> getColumnNames(String tableName,HttpServletRequest request){
		//JdbcTemplate sourceDB = loadSourceDB(request);
		String sqlQuery = queryBuilder.getColumns(tableName);
		List<Map<String, Object>> columnNames = srcDB.queryForList(sqlQuery);
		return columnNames;
	}
	
	
	public void migrateData(Map<String,Object> payload,HttpServletRequest request) {
		try {
			JdbcTemplate sourceDB = loadSourceDB(request);
			JdbcTemplate destinationDB = loadSourceDB(request);
		if(payload!=null){
			String tableName = (String) payload.get(A4dataConstants.TABLE_NAME);
			//createBeans.createBean(payload);
			//Object obj = createBeans.getInstance(payload.get(A4dataConstants.CLASSNAME));
			
			String sql = queryBuilder.selectQuery((String)payload.get(A4dataConstants.TABLE_NAME));
			
			Map<String, Object> data = sourceDB.queryForMap(sql);
			
			String insertquery = queryBuilder.insertQuery(tableName,data);
			destinationDB.execute(insertquery);
			
		}else {
			throw new ETLMigrationException(ErrorCodes.RegisterDatabase);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
