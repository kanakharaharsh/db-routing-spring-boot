package com.db.routing.example.dbroutingexample.database;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomTransactionManager extends DataSourceTransactionManager {

	private static final long serialVersionUID = 1L;

	public CustomTransactionManager(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		log.info("Init. Transaction for Service: {} : {}", definition.getName(), definition);
		TransactionSynchronizationManager.setCurrentTransactionIsolationLevel(
				definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT
				? definition.getIsolationLevel()
						: null);
		TransactionSynchronizationManager.setCurrentTransactionReadOnly(definition.isReadOnly());
		TransactionSynchronizationManager.setCurrentTransactionName(definition.getName());
		super.doBegin(transaction, definition);
	}
}
