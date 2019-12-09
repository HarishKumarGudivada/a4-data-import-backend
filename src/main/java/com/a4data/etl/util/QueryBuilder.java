package com.a4data.etl.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {


	public String selectQuery(String tableName) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select * from ").append(tableName).append(";");
		return queryBuilder.toString();
	}


	@SuppressWarnings("unchecked")
	public String createTable(String tableName,Map<String,Object> data) {
		StringBuilder queryBuilder = new StringBuilder();

		Map<String, String> destinationDB = (Map<String, String>) data.get("destinationdb");
		queryBuilder.append("create table ").append(data.get("table_name")).append("(");
		int count = 1;
		for(Entry<String,String> details : destinationDB.entrySet()) {
			queryBuilder.append(details.getKey()).append(" ").append(details.getValue());
			count++;
			if (destinationDB.size() != count) {
				queryBuilder.append(",");
			}
		}
		queryBuilder.append(");");
		return queryBuilder.toString();
	}



	public String insertQuery(String tableName,Map<String, Object> data) {
		StringBuilder queryBuilder = new StringBuilder();
		StringBuilder queryValueBuilder = new StringBuilder();
		queryValueBuilder.append("values(");
		try {
			if(data!=null) {
				queryBuilder.append("insert into ").append(tableName).append("(");
				int count = 0;
				for(Entry<String,Object> details : data.entrySet()) {
					queryBuilder.append(details.getKey());
					System.out.println();
					if (details.getValue().getClass().getName().contains("String")) {
						queryValueBuilder.append("\"").append(details.getValue()).append("\"");
					}else {
						queryValueBuilder.append(details.getValue());
					}
					count++;
					if (data.size() != count) {
						queryBuilder.append(",");
						queryValueBuilder.append(",");
					}
				}
				queryBuilder.append(") ");
				queryValueBuilder.append(")");

				queryBuilder.append(queryValueBuilder);
				System.out.println(queryBuilder);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return queryBuilder.toString();
	}


	public String getColumns(String tableName) {
		StringBuilder builder = new StringBuilder();
		builder.append("select column_name,data_type from information_schema.columns where table_name = '");
		builder.append(tableName).append("'");
		return builder.toString();
	}


	public String getTableNames() {
		return "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
	}
}
