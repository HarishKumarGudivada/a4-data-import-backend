package com.a4data.etl.exception;

public class ETLMigrationException extends RuntimeException{

	
	public ETLMigrationException(ErrorCodes exceptionMesage){
		super(exceptionMesage.getErrorMessage());
	}
}
