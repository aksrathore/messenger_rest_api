package org.abhinav.rest.messenger.dao.base;

import java.sql.Connection;

import org.abhinav.rest.messenger.utility.DatabaseConfiguration;

public abstract class BaseDAO {

	private static DatabaseConfiguration dataSource = new DatabaseConfiguration();
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			connection = dataSource.getConnection();
		}
		return connection;
	}

}
