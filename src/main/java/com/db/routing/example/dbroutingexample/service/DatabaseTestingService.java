package com.db.routing.example.dbroutingexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
// Service class annotated with @Transactional(readOnly = true). It means if @Transactional is not present, request will by default go to replica DB
@Transactional(readOnly = true)
public class DatabaseTestingService {
	
	private final DatabaseTestingService self;
	
	@Lazy
	public DatabaseTestingService(DatabaseTestingService self) {
		this.self = self;
	}
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String sqlQuery = "SELECT element FROM TABLE_NAME GROUP BY element";
	
	public List<String> defaultDb() {
		return namedParameterJdbcTemplate.query(sqlQuery, (rs, rowNum) -> rs.getString(1));
	}
	
	public List<String> replica() {
		return namedParameterJdbcTemplate.query(sqlQuery, (rs, rowNum) -> rs.getString(1));
	}
	
	@Transactional
	public List<String> primary() {
		  return namedParameterJdbcTemplate.query(sqlQuery, (rs, rowNum) -> rs.getString(1));
	}
	
}
