package com.sarsila.model;

import org.apache.log4j.Logger;

import com.sarsila.model.dao.*;

public class GoGame {
	
	final static Logger logger = Logger.getLogger(GoGame.class);
	private ClickItem[][] array;
	private Long id;
	public int turn; //1=black, 2=white
	private int table_size_rows=18; 
	private int table_size_cols=18;
	int blacks;
	int whites;
	
	public GoGame(Long id){
		//TODO: is there any need to get the game from db? propably not?
		this.id=id;
		array=new ClickItem[table_size_rows+1][table_size_cols+1];
	}
	
	public GoGame() {
		array=new ClickItem[table_size_rows+1][table_size_cols+1];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getWhitesCount(){
		return whites;
	}
	
	public int getBlacksCount(){
		return blacks;
	}

	public Long startNewGame(){
		turn=1; //start with black
		
	   	GoGameDao dao = new GoGameSQLDaoImpl(); 
    	Long id = dao.saveNewGame(this);
 
		return id;
	}
	
	public void addClick(ClickItem item){
		array[item.getRow()][item.getColumn()]=item;
		
		//save to db
		ClickItemDao dao = new ClickItemSQLDaoImpl();
		dao.saveClickItem(this, item);
		
		//save the stastics
		if (turn == 1) blacks++;
		else whites++;
		
		switchTurns();
	}
	
	public void deleteClick(int row, int col, boolean switchTurns){
		ClickItemDao dao = new ClickItemSQLDaoImpl();
		ClickItem item = array[row][col];
		dao.deleteClickItem(item); //"delete" from the database
		array[row][col] = null; //delete from the array
		
		if (item.getTurn()==1) blacks--;
		else whites--;
		
		if (switchTurns) switchTurns(); //and switch the turn
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
	
	public boolean isCellEmpty(int row, int col){
		if (array[row][col]==null) return true;
		else return false;
	}
	
	public void analyzeAndClean(){ //TODO: maybe this could be separated into its own class
		for (int row=1;row<=table_size_rows;row++) // loop through the rows
		{
			for (int col=1;col<=table_size_cols;col++) // loop through each cell
			{
				if (array[row][col] != null) // if the cell is not empty
				{
					ClickItem item = array[row][col];
					boolean surrounded=true;
					
					// check the upper row
					for (int x=-1;x<=1;x++) 
					{
						if (((col+x)>0) && ((col+x)<table_size_cols) && ((row-1)>0)){ //dont check the cells "outside of the table"
							if (array[row-1][col+x] == null){
								surrounded=false;
								break;
							}
							else if (array[row-1][col+x].getTurn() == item.getTurn())
							{
								surrounded=false;
								break;
							}	
						}
								
						
					}	
					
					// check the lower row
					for (int x=-1;x<=1;x++) 
					{
						if (((col+x)>0) && ((col+x)<table_size_cols+1) && ((row+1)<table_size_rows+1)){ //dont check the cells "outside of the table"

							if (array[row+1][col+x] == null){
								surrounded=false;
								break;
							}
							else if (array[row+1][col+x].getTurn() == item.getTurn())
							{
								surrounded=false;
								break;
							}
						}
					}
					
					//dont check cells outside the table
					if ((col-1)>0){
						//check the left side
						if ((array[row][col-1] == null)||(array[row][col-1].getTurn() == item.getTurn())){ 
							surrounded=false;
						}	
					}
					
					//dont check cells outside the table		
					if ((col+1)<table_size_cols+1){
						//check the right side
						if ((array[row][col+1] == null)||(array[row][col+1].getTurn() == item.getTurn())){
							surrounded=false;
						}
					}
					
					//if totally surrounded, delete
					if (surrounded==true){
						deleteClick(item.getRow(), item.getColumn(), false);
					}
				}
			}
		}
	}
	
}
