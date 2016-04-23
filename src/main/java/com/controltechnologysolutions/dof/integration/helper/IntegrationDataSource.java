package com.controltechnologysolutions.dof.integration.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationDataSource {
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationDataSource.class);

	private static final int POOL_SIZE = 20;
	private static final String DB_URL = "jdbc:mysql://localhost/swdb?serverTimezone=UTC";
	private static final String USERNAME = "sw";
	private static final String PASSWORD = "sw";

	private static BasicDataSource ds;

	public static boolean startup() {
		LOG.info("Creating data source...");
		try {
			ds = new BasicDataSource();
			ds.setMaxActive(POOL_SIZE);
			ds.setUsername(USERNAME);
			ds.setPassword(PASSWORD);
			ds.setUrl(DB_URL);
			// just to validate db info
			getConnection();
			return true;
		} catch (Exception e) {
			LOG.error("Fail to create datasource.", e);
			return false;
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void close() {
		try {
			ds.close();
		} catch (SQLException e) {
			//
		}
	}
}
