package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dsUtils.TTTGUI;
import dsUtils.TTTPosition;

public class LearningPlayer implements Player {

	private int playerNum;
	private TTTGame gb;
	private HashMap<Board,Integer> st;
	private List<Board> list;
	
	
	public LearningPlayer(int player, TTTGame gameBoard)
	{
		gb = gameBoard;
		playerNum = player;
		st = new HashMap<Board,Integer>();
		list = new ArrayList<Board>();
	}
	
	public int getPlayerNum()
	{
		return playerNum;
	}
	
	public void updateST(boolean won)
	{
		int adder = 0;
		if(won)
			adder=1;
		else
			adder= -1;
		for(Board b : list)
		{
			
			if(st.get(b)!=null){
				st.put(new Board(b), st.get(b)+adder);
			}
			else
				st.put(new Board(b), adder);
		}
		list.clear();
	}
	
	@Override
	public void makeMove() {
		// TODO Auto-generated method stub
		TTTGUI gui = gb.getGUI();
		TTTPosition pos = getPos();	
		gui.makeMove(pos, playerNum);
		gb.editBoard(playerNum, pos);
		System.out.printf("Player %d moved: %d, %d%n",playerNum,  pos.getRow(), pos.getCol());
		
		list.add(new Board(gb.getBoard()));
	}
	
	public TTTPosition getPos()
	{
		List<Integer> avail = gb.openSpots();
		List<TTTPosition> enc = new ArrayList<TTTPosition>();
		TTTPosition pos = null;
		int maxSoFar = Integer.MIN_VALUE;
		
		for(Integer i : avail)
		{

			gb.getBoard().set(i, playerNum);
			if(st.get(gb.getBoard())==null)
				st.put(new Board(gb.getBoard()), 0);
			
			int curr =st.get(gb.getBoard());
			if(curr >= maxSoFar)
			{
				if(curr >maxSoFar)
					enc.clear();
				pos = new TTTPosition(i/3, i%3);
				enc.add(pos);
				maxSoFar =st.get(gb.getBoard());
			}
			gb.getBoard().set(i, 0);
		}
		
		
		if(enc.size()>1)
		{
			int index = (int) (Math.random()*(enc.size()+0.0));
			return enc.get(index);
		}
		return pos;
	}

	
	

}
	


