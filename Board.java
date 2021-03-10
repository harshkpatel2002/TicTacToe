package project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Board {

	private int[] board;
	private int usedSlots;
	private HashSet<Integer> avail;
	
	public Board()
	{
		board = new int[9];
		usedSlots = 0;
		avail = new HashSet<Integer>();
		for(int i = 0; i < 9; i++)
			avail.add(i);
		
	}
	
	public Board(Board  b)
	{
		board = new int[9];
		avail = new HashSet<Integer>();
		for(int i = 0; i<9; i++)
		{
			board[i] = b.get(i);
			if(board[i] == 0)
				avail.add(i);
				
		}
		usedSlots = b.getUsed();
	}
	
	public HashSet<Integer> getAvail()
	{
		HashSet<Integer> newAvail = new HashSet<Integer>();
		for(Integer i: avail)
		{
			newAvail.add(i);
		}
		return newAvail;
	}
	public List<Board> getNextBoards()
	{
		List<Board> h = new ArrayList<Board>();
		HashSet<Integer> avail = getAvail();
		int player =2;
		if(this.getUsed()%2==0)
			player =1;
		for(Integer i : avail)
		{
			this.set(i, player);
			h.add(new Board(this));
			this.set(i, 0);
		}
		return h;
	}
	
	public int getUsed()
	{
		return usedSlots;
	}
	
	public boolean overlap(Board b)
	{
		for(int i = 0; i< 9; i++)
		{
			if(board[i] == 0 && b.asArray()[i] == 0)
			{
				
			}
			else
				return true;
		}
		return false;
	}
	
	public void reset()
	{
		board = new int[9];
		usedSlots = 0;
		avail = new HashSet<Integer>();
		for(int i = 0; i < 9; i++)
			avail.add(i);
	}
	
	public void set(int index, int val)
	{
		board[index]=val;	
		if(val == 0)
		{
			avail.add(index);
			usedSlots--;
		}
		else
		{
			avail.remove(index);
			usedSlots++;
		}
	}
	public int get(int index)
	{
		return board[index];
	}
	
	public int[] asArray()
	{
		return board;
	}
	@Override
	public boolean equals(Object to)
	{
		if(to == null || to.getClass() != this.getClass() )
			return false;
		if(to == this)
			return true;
		Board t = (Board) to;
		for(int i =0; i < 9; i++)
		{
			if(this.get(i) != t.get(i))
			{
				//if(hashCode()==t.hashCode()){
				//	System.out.println(this);
				//	System.out.println(t);
				//	throw new IllegalArgumentException("COLLISION");		}
				return false;
			}
		}

		return true;
	}
	
	@Override
	public int hashCode()
	{
		
		StringBuilder s = new StringBuilder();
		s.append(1);
		for(int i = 0; i < 9; i++)
		{
			s.append(get(i));
		}
		return Integer.parseInt(s.toString());
	
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(" \n");
		for(int i = 0; i<9;i++)
		{
			s.append(get(i));
			if(i!=8)
				s.append(", ");
			if(i ==2)
				s.append("\n");
			if(i==5)
				s.append("\n");
		}
		s.append(" \n");
		return s.toString();
	}
}