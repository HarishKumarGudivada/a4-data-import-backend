package com.a4data.etl.exception;

public enum ErrorCodes {

	
	RegisterDatabase(100,"Please Register the DatabaseDriver");
	
	private int errorCode;
	private String errorMessage;
	
	
	private ErrorCodes(int code,String message){
		this.errorCode = code;
		this.errorMessage = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
