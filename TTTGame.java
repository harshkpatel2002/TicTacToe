package project;

import java.util.ArrayList;
import java.util.List;

import dsUtils.TTTGUI;
import dsUtils.TTTPosition;
//Harsh Patel
public class TTTGame {

	private TTTGUI gui;
	private List<Integer> avail;
	private int left;
	private Board board;
	private Player p1;
	private Player p2;
	
	
	public TTTGame()
	{
		board = new Board();
		gui = new TTTGUI(150);
		avail = new ArrayList<Integer>();
		left=9;
		for(int i = 0; i<9; i++)
			avail.add(i);
		
	}
	public void setPlayers(Player p1, Player p2)
	{
		this.p1=p1;
		this.p2=p2;
	}
	
	//Restarts game board by clearing the gui and setting the fields to their starting values
	public void restartGame()
	{
		board.reset();
		gui.clearBoard();
		avail = new ArrayList<Integer>();
		left = 9;
		for(int i = 0; i<9; i++)
			avail.add(i);
		if(p1.getClass() == LearningPlayer.class)
		{
			
		}
	}
	
	//return the TicTacToe Gui
	public TTTGUI getGUI()
	{
		return gui;
	}
	
	//Makes changes to the board based on the player and the position selected. 
	public void editBoard(int player, TTTPosition pos)
	{
		int row = pos.getRow();
		int col = pos.getCol();


		board.set(row*3+col, player);
		avail.remove(avail.indexOf(row*3+col));
		left--;
		
	}
	
	//Checks if the given position is open on the board
	public boolean isValid(TTTPosition p)
	{
		int row = p.getRow();
		int col = p.getCol();
		
		if(board.get( row*3 + col) == 0)
			return true;
		return false;
	}
	
	public boolean winnerExists(int player) 
	{
		LearningPlayer x = null;
		LearningPlayer y = null;
		if(p1.getClass()== LearningPlayer.class)
			x= (LearningPlayer) p1;
		if(p2.getClass()== LearningPlayer.class)
			y= (LearningPlayer) p2;
			
		
		if(avail.size() > 4)//If there are 5 or more open spots on the board
			return false;
		for(int i = 0, j = 1, k =2; i<7; i+=3)//Horizontal Winner Check
		{
			if(board.get( i) == player && board.get( j) == player && board.get( k) == player)
			{	
				if(x!=null)
				{
					if(x.getPlayerNum()!=player)
						x.updateST(false);
					else
						x.updateST(true);
				}
				if(y!=null)
				{
					if(y.getPlayerNum()!=player)
						y.updateST(false);
					else
						y.updateST(true);
				}
				return true;
			}
			j+=3;
			k+=3;
		}
		for(int i = 0, j = 3, k =6; i<3; i++)//Vertical Winner Check
		{
			if(board.get( i) == player && board.get( j) == player && board.get( k) == player)
			{
				if(x!=null)
				{
					if(x.getPlayerNum()!=player)
						x.updateST(false);
					else
						x.updateST(true);
				}
				if(y!=null)
				{
					if(y.getPlayerNum()!=player)
						y.updateST(false);
					else
						y.updateST(true);
				}
				return true;
			}
			j++;
			k++;
		}
		if(board.get( 0) == player && board.get( 4) == player && board.get( 8) == player)//Diagonal Winner Check
		{
			if(x!=null)
			{
				if(x.getPlayerNum()!=player)
					x.updateST(false);
				else
					x.updateST(true);
			}
			if(y!=null)
			{
				if(y.getPlayerNum()!=player)
					y.updateST(false);
				else
					y.updateST(true);
			}
			return true;
		}
		if(board.get( 2) == player && board.get( 4) == player && board.get( 6) == player)//Other Diagonal Winner Check
		{
			if(x!=null)
			{
				if(x.getPlayerNum()!=player)
					x.updateST(false);
				else
					x.updateST(true);
			}
			if(y!=null)
			{
				if(y.getPlayerNum()!=player)
					y.updateST(false);
				else
					y.updateST(true);
			}
			return true;
		}
		return false;
	}
	
	public int getLeft()
	{
		return left; //How many open spaces left
	}
	
	//Returns a list containing the positions of open spots
	public List<Integer> openSpots()
	{
		return avail;
	}
	
	public Board getBoard()
	{
		return board;
	}
	//Game is over once there are no spaces left
	public boolean gameOver()
	{
		if(avail.size() == 0)
			return true;
		return false;
	}
	
	

	
}
