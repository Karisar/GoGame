package com.sarsila.model;

public class GoGame {
	private ClickItem[][] array;
	private Long id;
	
	public GoGame(Long id){
		//TODO: is there any need to get the game from db? propably not?
		this.id=id;
		array=new ClickItem[5][5];
	}
	
	public GoGame() {
		array=new ClickItem[5][5];
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
		
	}
	
	
}
