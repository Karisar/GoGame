package com.sarsila.model;

import org.apache.log4j.Logger;
import com.sarsila.model.dao.*;

public class GoGame {
	
	final static Logger logger = Logger.getLogger(GoGame.class);
	private ClickItem[][] array;
	private Long id;
	
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
		//TODO: save new game into db and return its key
		return new Long(123);
	}
	
	public void addClick(ClickItem item){
		array[item.getRow()][item.getColumn()]=item;
		
		ClickItemDao dao = new ClickItemSQLDaoImpl();
		dao.saveClickItem(this, item);
		//TODO: save to dao
		
	}
	
	
}
