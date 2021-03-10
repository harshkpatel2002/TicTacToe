package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import dsUtils.TTTGUI;
import dsUtils.TTTPosition;

public class PerfectPlayer implements Player{

	private TTTGame gb;
	private int playerNum;
	private int otherPlayer;
	private HashMap<Board, Integer> scores;
	private HashSet<Board> visited;
	
	public PerfectPlayer(int player, TTTGame gb)
	{
		playerNum = player;
		if(player == 1)
			otherPlayer =2;
		else
			otherPlayer=1;
		this.gb = gb;
		scores = new HashMap<Board,Integer>();
		visited = new HashSet<Board>();
		boolean myTurn = true;
		if(player==2)
			myTurn = false;
		calcScores(new Board(), myTurn);
	}
	
	private int calcScores(Board b, boolean myTurn)
	{
		if(!scores.containsKey(b))
		{
			visited.add(b);
			if(b.getUsed() == 9 || potentialWinner(playerNum, b) || potentialWinner(otherPlayer,b))
			{

				
				if(potentialWinner(playerNum,b))
				{
					//System.out.println("SCORE 1");
					return 1;
				}
				else if(potentialWinner(otherPlayer,b))
				{
				//	System.out.println("SCORE --1");
					return -1;
				}
				return 0;
			}
			else
			{
				HashSet<Board> children = new HashSet<Board>();
				for(Board c : b.getNextBoards())
				{
					
					children.add(new Board(c));
					if(!visited.contains(c))
					{
						scores.put(new Board(c), calcScores(c, !myTurn));
					//	System.out.println(scores.get(new Board(c)));
					}
				}
				if(myTurn)
				{
					int max = -1;
					for(Board j : children)
					{
					//	System.out.println(scores.get(j));
						if(max < scores.get(j))
							max = scores.get(j);
					}
					children.clear();
					
					return max;
				}
				else
				{
					{
						int min = 2;
						for(Board j : children)
						{
							
							if(min > scores.get(j))
								min = scores.get(j);
						}
						children.clear();
						return min;
				}
			
			}
		}
		}
		return scores.get(b);
	}
	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		TTTPosition pos = getPos();
		TTTGUI gui = gb.getGUI();
		gui.makeMove(pos, playerNum);
		gb.editBoard(playerNum, pos);
		System.out.printf("Player %d moved: %d, %d%n",playerNum,  pos.getRow(), pos.getCol());
	}

	@Override
	public int getPlayerNum() {
		// TODO Auto-generated method stub
		return playerNum;
	}
	
	public TTTPosition getPos()
	{
		int next = nextMoveWinner(playerNum);
		if(next!=-1)
		{
			return new TTTPosition(next/3, next%3);
		}
		List<Board> idealBoards = new ArrayList<Board>();
		int max = -1;
		
		for(Board b : gb.getBoard().getNextBoards())
		{
			if(scores.get(b) >= max) // max can never be -1, assuming this works as intended
			{	if(scores.get(b)>max)
					idealBoards.clear();
				idealBoards.add(b);
				max = scores.get(b);
				
			}
		}
		Board ideal = idealBoards.get((int) Math.floor(Math.random()*idealBoards.size()));
			for(int i= 0; i<9; i++)
			{
				if(gb.getBoard().get(i)!=ideal.get(i) && gb.isValid(new TTTPosition(i/3, i%3))) 
				{
					return new TTTPosition(i/3, i%3);
				}
			}
		
		return null;
		
	}
	
	private int nextMoveWinner(int player)
	{
		
		HashSet<Integer> avail = gb.getBoard().getAvail();
		Board b = gb.getBoard();
		for(Integer i : avail)
		{
			b.set(i, player);
			if(potentialWinner(player, b))
				return i;
			b.set(i, 0);
		}
		return -1;
	}
	
	private static boolean potentialWinner(int player, Board board)
	{
		for(int i = 0, j = 1, k =2; i<7; i+=3)//Horizontal Winner Check
		{
			if(board.get( i) == player && board.get( j) == player && board.get( k) == player)
			{	
				return true;
			}
			j+=3;
			k+=3;
		}
		for(int i = 0, j = 3, k =6; i<3; i++)//Vertical Winner Check
		{
			if(board.get( i) == player && board.get( j) == player && board.get( k) == player)
			{
				return true;
			}
			j++;
			k++;
		}
		if(board.get( 0) == player && board.get( 4) == player && board.get( 8) == player)//Diagonal Winner Check
		{
			return true;
		}
		if(board.get( 2) == player && board.get( 4) == player && board.get( 6) == player)//Other Diagonal Winner Check
		{
			
			return true;
		}
		return false;
		
	}
	
	

}
