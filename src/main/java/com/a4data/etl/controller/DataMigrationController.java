package com.a4data.etl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a4data.etl.constants.A4dataConstants;
import com.a4data.etl.service.DataMigrationService;

@RestController
@RequestMapping("/datamigration")
@CrossOrigin(origins ="*")
public class DataMigrationController {
	
	
	@Autowired
	DataMigrationService service;
	
	
	@GetMapping(path = "/loadDataBases", produces = "application/json")
	public ResponseEntity<Object> loadListOfDatabases() {
		try {
		List<String> dbsList = service.loadListOfDatabases(); 
		return ResponseEntity.ok().body(dbsList);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	
	/*@PostMapping(path = "/registerDb", consumes = "application/json")
	public ResponseEntity registerDataBase(@RequestBody Map<String,String> payload,HttpServletRequest request){
		try {
		 service.registerDatabases(payload.get(A4dataConstants.SOURCE_DB) , payload.get(A4dataConstants.DESTINATION_DB) , request);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok().build();
	}
	
	*/
	
	@PostMapping(path = "/migrateData", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> postData(@RequestBody Map<String,Object> payload,HttpServletRequest request){
		try {
		service.migrateData(payload,request);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return null;
	}
	
	
	@PostMapping(path = "/loadTables", produces = "application/json")
	public ResponseEntity<Object> loadTableData(@RequestBody Map<String,String> payload,HttpServletRequest request){
		List<String> tableNames = null; 
		try {
			service.registerDatabases(payload.get(A4dataConstants.SOURCE_DB) , payload.get(A4dataConstants.DESTINATION_DB) , request);
			tableNames = service.getTableNames(request);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok().body(tableNames);
	}
	
	
	
	@PostMapping(path = "/loadColumnNames", consumes = "application/json",produces = "application/json")
	public ResponseEntity<Object> loadColumnNames(@RequestBody Map<String,String> tableName,HttpServletRequest request){
		List<Map<String, Object>> tableNames = null; 
		try {
		tableNames = service.getColumnNames(tableName.get(A4dataConstants.TABLE_NAME),request);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok().body(tableNames);
	}
	
	

}
