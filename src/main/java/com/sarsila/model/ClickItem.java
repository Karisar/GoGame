package com.sarsila.model;


public class ClickItem {
	private int row;
	private int column;
	private Long id;
	public int turn; //1=black, 2=white

	public ClickItem(Integer row, Integer column, int turn){
		this.row=row;
		this.column=column;
		this.turn=turn;
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
	
	public String getMarker(int row, int col)//TODO: why these parameters are here?
	{
		if (turn==1) return "X";
		else if (turn==2) return "O";
		else return " ";
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
