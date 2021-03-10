package project;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//Harsh Patel
public class PlayTestTTT {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub

	
		StdOut.print("Player 1 (H)uman or (R)andom or (L)earning or (P)erfect: ");
		char c1 = Character.toLowerCase(StdIn.readLine().charAt(0));
		StdOut.print("Player 2 (H)uman or (R)andom or (L)earning or (P)erfect: ");
		char c2 = Character.toLowerCase(StdIn.readLine().charAt(0));
			
		TTTGame t = new TTTGame();
		boolean p1Won = false;
		boolean p2Won = false;
		
		Player p1;
		Player p2;
		
		if(c1 == 'h')
			p1 = new HumanPlayer(1,t);
		else if(c1=='l')
			p1 = new LearningPlayer(1,t);
		else if(c1 == 'p')
			p1 = new PerfectPlayer(1,t);
		else
			p1 = new RandomPlayer(1,t);
		
		if(c2=='h')
			p2 = new HumanPlayer(2,t);
		else if(c2=='l')
			p2 = new LearningPlayer(2,t);
		else if(c2 == 'p')
			p2 = new PerfectPlayer(2,t);
		else
			p2 = new RandomPlayer(2,t);
		System.out.println("");
		
		
		t.setPlayers(p1, p2);
	do
	{
		t.restartGame();
		p1Won = false;
		p2Won = false;
		InnerLoop:
		while(!t.gameOver())
		{
			
			System.out.println("Player 1 Turn: ");
			p1.makeMove();
			p1Won = t.winnerExists(1);
			if(p1Won || t.gameOver())
				break InnerLoop;

		
			System.out.println("Player 2 Turn: ");
			p2.makeMove();
			p2Won = t.winnerExists(2);
			if(p2Won)
				break InnerLoop;
			
		}
		
		if(t.gameOver() && !p2Won && !p1Won)
		{
			System.out.println("Tie Game!");
		}
		else 
		{	if(p1Won && p1.getClass()==HumanPlayer.class)
			{
			System.out.println("Player 1 has Won!");
			}
			else if(!p1Won && p1.getClass()==HumanPlayer.class)
			{
			System.out.println("Player 1 has Lost!");
			}
		
			if(p2Won && p2.getClass()==HumanPlayer.class)
			{
			System.out.println("Player 2 has Won!");
			}
			else if(!p2Won && p2.getClass()==HumanPlayer.class)
			{
			System.out.println("Player 2 has Lost!");
			}
		}
		
		System.out.println();
		
	}while(t.getGUI().askPlayAgain());
	}

}
