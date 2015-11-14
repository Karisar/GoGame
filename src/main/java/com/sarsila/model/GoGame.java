package com.sarsila.model;

import org.apache.log4j.Logger;

import com.sarsila.model.dao.*;

public class GoGame {
	
	final static Logger logger = Logger.getLogger(GoGame.class);
	private ClickItem[][] array;
	private Long id;
	public int turn; //1=black, 2=white
	
	public GoGame(Long id){
		//TODO: is there any need to get the game from db? propably not?
		this.id=id;
		array=new ClickItem[6][6];
	}
	
	public GoGame() {
		array=new ClickItem[6][6];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long startNewGame(){
		turn=1; //start with black
	   	GoGameDao dao = new GoGameSQLDaoImpl(); //TODO: refactor,move this to gogamedao
    	Long id = dao.saveNewGame(this);
 
		return id;
	}
	
	public void addClick(ClickItem item){
		array[item.getRow()][item.getColumn()]=item;
		
		ClickItemDao dao = new ClickItemSQLDaoImpl();
		dao.saveClickItem(this, item);
		switchTurns();
	}
	
	public void switchTurns(){
    	if (turn==1) turn=2;
    	else turn=1;
    	
	}
	
	public String getMarker(int row, int col){
		ClickItem item = array[row][col];
		if (item != null) return item.getMarker(row, col);
		else return " ";
	}
	
}
