package com.sarsila.model.dao;

import com.sarsila.model.ClickItem;

import com.sarsila.model.GoGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GoGameSQLDaoImpl implements GoGameDao {


	private Connection connetion = null;
	private PreparedStatement preparedStatement = null;
	
	 public GoGameSQLDaoImpl() throws Exception {
		super();
		try {
		Class.forName("com.mysql.jdbc.Driver");
	    connetion = DriverManager
	         .getConnection("jdbc:mysql://sql4.freemysqlhosting.net:3306/sql496421?"
	             + "user=sql496421&password=J2rglKhc6i");
		}
		catch (Exception e)
		{
			throw new ConnectionException("Could not establish connection:" + e.toString());
		}
	}
	 
	@Override
	public Long saveNewGame(GoGame game) {
		 try {
		    
		     preparedStatement = connetion
		         .prepareStatement("insert into  sql496421.gogame values (default, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		
		     Date date = new Date();

		     preparedStatement.setString(1, date.toString());
		     preparedStatement.setInt(2, game.getTurn());
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
	public GoGame getGameFromDB(GoGame game) {
		try {
		 
		     preparedStatement = connetion
		         .prepareStatement("select * from sql496421.gogame where id ='" + game.getId().toString()+"'");

		     preparedStatement.executeQuery();
		     
		     // get the row id and turn
		     ResultSet rs = preparedStatement.getResultSet();
		  
		     if (rs.next()) {
		    	 Long db_id = null;
		    	 db_id = rs.getLong("ID");
		         int turn= rs.getInt("TURN");
		         game.setTurn(turn);
		     //    game = new GoGame();
		     //    game.setId(db_id);
		         updateClickItems(game);
		     }
		     
		     rs.close();
		     preparedStatement.close();
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
		return game;
	}
	
	private void updateClickItems(GoGame game) {
		 try {
		     preparedStatement = connetion
		         .prepareStatement("select * from sql496421.clickitem where gogame_id = '" + game.getId()+ "'");
		
		     preparedStatement.executeQuery();
		     ResultSet rs = preparedStatement.getResultSet();
		     List list = new ArrayList();
		     while (rs.next()) {
		    	 if (rs.getInt("STATUS") == 1) {
		    		 ClickItem item = new ClickItem(rs.getInt("ROW"), rs.getInt("COL"), rs.getInt("TURN"), rs.getLong("ID"));
		    		 list.add(item);
		    	    }
		     }
		     game.updateClickItems(list); //TODO: this now doesnt make any sense. Circular dependency? Maybe this should just return a list
		     
		     rs.close();
		     preparedStatement.close();
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
		}
		
	public void updateTurn(GoGame game)
	{
		 try {
			    
		     preparedStatement = connetion
		         .prepareStatement("update sql496421.gogame set turn= " + game.getTurn() + " where id = "+ game.getId());
		
		     preparedStatement.executeUpdate();
		     preparedStatement.close();
		     
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
	}
}

