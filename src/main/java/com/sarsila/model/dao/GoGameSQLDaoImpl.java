package com.sarsila.model.dao;

import com.sarsila.model.GoGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;



public class GoGameSQLDaoImpl implements GoGameDao {
	 private Connection connect = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	 
	@Override
	public Long saveNewGame(GoGame game) {
		 try {
		     // This will load the MySQL driver, each DB has its own driver
		     Class.forName("com.mysql.jdbc.Driver");
		     // Setup the connection with the DB
		     connect = DriverManager
		         .getConnection("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql496421?"
		             + "user=sql496421&password=J2rglKhc6i");

		     // Statements allow to issue SQL queries to the database
		     statement = connect.createStatement();
		     // PreparedStatements can use variables and are more efficient
		     preparedStatement = connect
		         .prepareStatement("insert into  sql496421.gogame values (default, ?)");
		
		     Date date = new Date();

		     preparedStatement.setString(1, date.toString());
		     preparedStatement.executeUpdate();
		     preparedStatement.close();
		     
		     game.setId(new Long(3433)); //TODO: clean this as well, should retrieve from db
		     
		   } 
		 catch (Exception e) {
			   e.printStackTrace();
		   } 
		 
		 finally {
		     try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   }
		return new Long(121);//TODO: get the id you've just inserted
	}

	@Override
	public GoGame getGame(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
