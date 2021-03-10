package project;

import dsUtils.TTTGUI;
import dsUtils.TTTPosition;

public class HumanPlayer implements Player{

	private int playerNum;
	private TTTGame gameBoard;
	
	public HumanPlayer(int player, TTTGame gameBoard)
	{
		this.gameBoard=gameBoard;
		playerNum = player;
	}
	public int getPlayerNum()
	{
		return playerNum;
	}
	public void makeMove()
	{
		
		TTTGUI gui = gameBoard.getGUI();
		TTTPosition pos = gui.getMove();
		while(!gameBoard.isValid(pos))
		{
			System.out.println("Pick an open spot");
			pos = gui.getMove();
		}
		
		gui.makeMove(pos, playerNum);
		gameBoard.editBoard(playerNum, pos);
		System.out.printf("Player %d moved: %d, %d%n",playerNum,  pos.getRow(), pos.getCol());
			
	}
}
