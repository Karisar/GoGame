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
		array=new ClickItem[19][19];
	}
	
	public GoGame() {
		array=new ClickItem[19][19];
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
	
	public void deleteClick(int row, int col){
		ClickItemDao dao = new ClickItemSQLDaoImpl();
		ClickItem item = array[row][col];
		dao.deleteClickItem(item); //"delete" from the database
		array[row][col] = null; //delete from the array
		switchTurns(); //and switch the turn
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
		for (int row=1;row<=18;row++) // loop through the rows
		{
			for (int col=1;col<=18;col++) // loop through each cell
			{
				if (array[row][col] != null) // if the cell is not empty
				{
					ClickItem item = array[row][col];
					boolean surrounded=true;
					
					// check the upper row
					for (int x=-1;x<=1;x++) 
					{
						if (((col+x)>0) && ((col+x)<18)){ //dont check the cells "outside of the table"
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
						if (((col+x)>0) && ((col+x)<19) && ((row+1)<19)){ //dont check the cells "outside of the table"

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
					if ((col+1)<19){
						//check the right side
						if ((array[row][col+1] == null)||(array[row][col+1].getTurn() == item.getTurn())){
							surrounded=false;
						}
					}
					
					//if totally surrounded, delete
					if (surrounded==true){
						deleteClick(item.getRow(), item.getColumn());
					}
				}
			}
		}
	}
	
}
