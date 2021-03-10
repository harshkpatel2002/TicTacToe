package project;

import java.util.List;
import java.util.Random;

import dsUtils.TTTGUI;
import dsUtils.TTTPosition;

public class RandomPlayer implements Player {

	private int playerNum;
	private TTTGame gameBoard;
	public RandomPlayer(int player, TTTGame gb)
	{
		playerNum = player;
		gameBoard =gb;
	}
	public int getPlayerNum()
	{
		return playerNum;
	}
	public void makeMove() {
		// TODO Auto-generated method stub

		TTTGUI gui = gameBoard.getGUI();
		Random num = new Random();
		List<Integer> avail = gameBoard.openSpots();
		int rand = avail.get(num.nextInt(gameBoard.getLeft()));
		int row = rand/3;
		int col = rand %3;
		TTTPosition pos =  new TTTPosition(row,col);		
		gui.makeMove(pos, playerNum);
		gameBoard.editBoard(playerNum, pos);
		System.out.printf("Player %d moved: %d, %d%n",playerNum,  row, col);
	}

}
