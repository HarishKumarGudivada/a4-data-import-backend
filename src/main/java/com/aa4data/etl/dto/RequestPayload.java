package com.aa4data.etl.dto;

public class RequestPayload {

	private String dbName;
	private String tablename;
	private String[] columns;
	
	
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	
	
	
	
	
	
}
