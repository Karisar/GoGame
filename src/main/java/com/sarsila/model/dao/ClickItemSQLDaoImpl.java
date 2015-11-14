package com.sarsila.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.sarsila.model.ClickItem;
import com.sarsila.model.GoGame;

public class ClickItemSQLDaoImpl implements ClickItemDao {
	 private Connection connect = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	 
	@Override
	public Long saveClickItem(GoGame game, ClickItem item) {
	    
		Long db_id = new Long(0);
	 	 
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
		         .prepareStatement("insert into  sql496421.clickitem values (default, ?, ?, ?, ?, ?, ?)"
		        		 , Statement.RETURN_GENERATED_KEYS);
		
		     Date date = new Date();

		     preparedStatement.setString(1, date.toString());
		     preparedStatement.setString(2, new Long(item.getRow()).toString());
		     preparedStatement.setString(3, new Long(item.getColumn()).toString());
		     preparedStatement.setString(4, new Long(game.turn).toString());
		     preparedStatement.setString(5, "1"); // new clickitem is always active
		     preparedStatement.setString(6, game.getId().toString());
			  
		     preparedStatement.executeUpdate();
		     	     
		     ResultSet rs = preparedStatement.getGeneratedKeys();
		     int generatedKey = 0;
		     if (rs.next()) {
		         db_id = new Long(rs.getInt(1));
		     }
		     
		     preparedStatement.close();

		     
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
		item.setId(db_id);
		return db_id;//TODO: get the id you've just inserted
	}

	@Override
	public ClickItem getClickItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteClickItem(ClickItem item){
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
		         .prepareStatement("update sql496421.clickitem set status='2' where id = '" + item.getId() +"'");
					  
		     preparedStatement.executeUpdate();     	     
		     preparedStatement.close();

		   } 
		 catch (Exception e) {
			   e.printStackTrace();
		   } 
		 
		 finally {
		     try {
				connect.close();
			} 
		     catch (SQLException e) {
				e.printStackTrace();
			}
		   }		
	}

}
