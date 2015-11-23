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
		     Class.forName("com.mysql.jdbc.Driver");
		     connetion = DriverManager
		         .getConnection("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql496421?"
		             + "user=sql496421&password=J2rglKhc6i");

		     preparedStatement = connetion
		         .prepareStatement("insert into  sql496421.gogame values (default, ?)", Statement.RETURN_GENERATED_KEYS);
		
		     Date date = new Date();

		     preparedStatement.setString(1, date.toString());
		     preparedStatement.executeUpdate();
		     
		     Long db_id = new Long(0);

		     // get the row id
		     ResultSet rs = preparedStatement.getGeneratedKeys();
		  
		     if (rs.next()) {
		         db_id = new Long(rs.getInt(1));
		     }
		     
		     rs.close();
		     preparedStatement.close();
		     
		     game.setId(db_id); 
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
