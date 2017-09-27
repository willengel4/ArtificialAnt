package artificialAnt;

public class Ant extends GridObject
{
	private int direction;
	private int row, col;
	
	public Ant(int row, int col)
	{
		direction = 0;
		this.row = row;
		this.col = col;
	}
	
	public void turnAntRight()
	{
		direction -= 90;
		repositionAnt();
	}
	
	public void turnAntLeft()
	{
		direction += 90;
		repositionAnt();
	}
	
	private void repositionAnt()
	{
		if(direction >= 360)
			direction = 0;
		
		if (direction < 0)
			direction = 270;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public int getNextRow()
	{
		if (direction == 270)
			return row - 1;
		else if(direction == 90)
			return row + 1;
		else
			return row;
	}
	
	public int getNextCol()
	{
		if (direction == 0)
			return col + 1;
		else if(direction == 180)
			return col - 1;
		else
			return col;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setPosition(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}
