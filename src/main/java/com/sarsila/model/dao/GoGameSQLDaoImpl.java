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
	 private Connection connetion = null;
	 private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	 
	@Override
	public Long saveNewGame(GoGame game) {
		 try {
		     // This will load the MySQL driver, each DB has its own driver
		     Class.forName("com.mysql.jdbc.Driver");
		     // Setup the connection with the DB
		     connetion = DriverManager
		         .getConnection("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql496421?"
		             + "user=sql496421&password=J2rglKhc6i");

		     // Statements allow to issue SQL queries to the database
		  //   statement = connetion.createStatement();
		     // PreparedStatements can use variables and are more efficient
		     preparedStatement = connetion
		         .prepareStatement("insert into  sql496421.gogame values (default, ?)", Statement.RETURN_GENERATED_KEYS);
		
		     Date date = new Date();

		     preparedStatement.setString(1, date.toString());
		     preparedStatement.executeUpdate();
		     
		     Long db_id = new Long(0);
		     
		     ResultSet rs = preparedStatement.getGeneratedKeys();
		     int generatedKey = 0;
		     if (rs.next()) {
		         db_id = new Long(rs.getInt(1));
		     }
		     
		     preparedStatement.close();
		     
		     game.setId(db_id); //TODO: clean this as well, should retrieve from db
		     
		   } 
		 catch (Exception e) {
			   e.printStackTrace();
		   } 
		 
		 finally {
		     try {
				connetion.close();
			} 
		     catch (SQLException e) {
				e.printStackTrace();
			}
		   }
		return game.getId();//TODO: get the id you've just inserted
	}

	@Override
	public GoGame getGame(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
