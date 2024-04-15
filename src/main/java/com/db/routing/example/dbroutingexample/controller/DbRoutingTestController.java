package com.db.routing.example.dbroutingexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.routing.example.dbroutingexample.service.DatabaseTestingService;

@RestController
public class DbRoutingTestController {
	
	@Autowired
	private DatabaseTestingService databaseTestingService;

	@GetMapping("default")
	public List<String> defaultDb() {
		return databaseTestingService.defaultDb();
	}
	
	@GetMapping("replica")
	public List<String> replica() {
		return databaseTestingService.replica();
	}

	@GetMapping("primary")
	public List<String> primary() {
		return databaseTestingService.primary();
	}
}
