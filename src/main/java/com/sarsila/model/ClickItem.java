package com.sarsila.model;


public class ClickItem {
	private int row;
	private int column;
	private Long id;
	public int turn; //1=black, 2=white

	public ClickItem(Integer row, Integer column){
		this.row=row;
		this.column=column;
		//TODO: save to db
		//TODO: add turn
	}
	
	public ClickItem(Long id){
		this.id=id;
		//TODO: get from db
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
		return "Row:"+row +", column:"+column;
	}
	
	public String marker(int row, int col)
	{
		if (turn==1) return "X";
		else if (turn==2) return "O";
		else return null;
	}
}
