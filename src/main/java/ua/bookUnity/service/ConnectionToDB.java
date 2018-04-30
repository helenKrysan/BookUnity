package ua.bookUnity.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDB {
	
	private static final String DBURL = "jdbc:postgresql://localhost:5432/bookUnity";
	private static final String DBUser = "postgres";
	private static final String DBUserPassword="22041999";
	
	
	public static Connection getConnectionToDB() throws DAOException{
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(DBURL,DBUser,DBUserPassword);
			if(connection!=null) {
				return connection;
			}else {
				throw new DAOException();
			}
		}catch(Exception e) {
			throw new DAOException();
		}
	
	}
	
}
