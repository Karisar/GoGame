package com.sarsila.model;


public class ClickItem {
	private int row;
	private int column;
	private Long id;

	public ClickItem(Integer row, Integer column){
		this.row=row;
		this.column=column;
		//TODO: save to db
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
}
