package artificialAnt;

import java.awt.Color;
import java.awt.Graphics;

public class Grid 
{
	private GridObject[][] grid;
	private int rows;
	private int columns;
	private Ant ant;
	private double score;
	
	public Grid(int rows, int columns)
	{
		this.rows = rows;
		this.columns = columns;
		grid = new GridObject[rows][columns];
		
		for(int i = 0; i < grid.length; i++)
			for(int f = 0; f < grid[i].length; f++)
				grid[i][f] = new GridObject();
		
		ant = new Ant(3, 0);
		score = 0.0;
		grid[3][0] = ant;
		grid[3][1] = new Food();
		grid[3][2] = new Food();
		grid[3][3] = new Food();
		grid[3][4] = new Food();
		//grid[4][4] = new Food();
		grid[5][4] = new Food();
		grid[6][4] = new Food();
		//grid[7][4] = new Food();
		grid[8][4] = new Food();
		//grid[8][5] = new Food();
		//grid[8][6] = new Food();
		grid[8][7] = new Food();
		grid[8][8] = new Food();
		grid[7][9] = new Food();
		grid[6][9] = new Food();
		grid[5][9] = new Food();
		grid[4][9] = new Food();
	}
	
	public void execute(String instruction)
	{
		if(instruction.equals("00"))
			turnAntRight();
		else if(instruction.equals("01"))
			turnAntLeft();
		else if(instruction.equals("10"))
			moveAntForward();
		else
			noOp();
	}
	
	public void turnAntRight()
	{
		ant.turnAntRight();
	}
	
	public void turnAntLeft()
	{
		ant.turnAntLeft();
	}
	
	public void moveAntForward()
	{
		int nextRow = ant.getNextRow();
		int nextCol = ant.getNextCol();
		
		if(valid(nextRow, nextCol))
		{
			if (grid[nextRow][nextCol] instanceof Food)
				score++;
			
			grid[nextRow][nextCol] = ant;
			grid[ant.getRow()][ant.getCol()] = new GridObject();
			ant.setPosition(nextRow, nextCol);
		}
	}
	
	public void noOp()
	{
		
	}
	
	public GridObject getFacingObject()
	{
		int nextRow = ant.getNextRow();
		int nextCol = ant.getNextCol();
		
		if(valid(nextRow, nextCol))
		{
			return grid[nextRow][nextCol];
		}
		
		return new GridObject();
	}
	
	public boolean valid(int r, int c)
	{
		return r >= 0 && r < rows && c >= 0 && c < columns;
	}
	
	public void drawGrid(Graphics g)
	{
		for(int i = 0; i < rows; i++)
		{
			for(int f = 0; f < columns; f++)
			{
				if(grid[i][f] instanceof Ant)
					g.setColor(Color.red);
				else if(grid[i][f] instanceof Food)
					g.setColor(Color.black);
				else
					g.setColor(Color.WHITE);
				
				g.fillRect(f * 10, i * 10, 10, 10);
			}
		}
	}
	
	public void printGrid()
	{
		for(int i = 0; i < rows; i++)
		{
			for(int f = 0; f < columns; f++)
			{
				if(grid[i][f] instanceof Ant)
					System.out.print("a");
				else if(grid[i][f] instanceof Food)
					System.out.print("o");
				else
					System.out.print("x");
			}
			
			System.out.println();
		}
		
		System.out.println("");
	}
	
	public double getScore()
	{
		return score;
	}
}
