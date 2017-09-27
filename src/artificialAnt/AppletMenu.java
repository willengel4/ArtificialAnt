package artificialAnt;

import geneticStateMachine.Genome;
import geneticStateMachine.Population;
import geneticStateMachine.StateMachine;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class AppletMenu extends Applet
{
	private Image bufferedImage;
	private Graphics g;
	private Grid grid;
	
	private Population currentPopulation;
	private StateMachine pocketMachine;
	private Genome bestGenome;
	private AntEvaluator evaluator;
	
	private int displayTokens;
	
	public void init()
	{
		setSize(500, 400);
		setBackground(Color.white);
		bufferedImage = createImage(getWidth(), getHeight());
		g = bufferedImage.getGraphics();
		
		grid = new Grid(20, 20);
		
		evaluator = new AntEvaluator(8, 2, 4);
		currentPopulation = new Population(1000, evaluator);
		currentPopulation.createInitialPopulation(83);
		displayTokens = 0;
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void paint(Graphics gr)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		
		if(displayTokens == 0)
		{
			Population nextGeneration = currentPopulation.createNextGeneration();
			
			//if(bestGenome == null || currentPopulation.getPopulationBest().getFitness() > bestGenome.getFitness())
			bestGenome = currentPopulation.getPopulationBest();
			
			currentPopulation = nextGeneration;
			displayTokens = 50;
			pocketMachine = new StateMachine(8, 2, 4, bestGenome.getEncoding());
			System.out.println("Best genome fitness: " + bestGenome.getFitness());
			grid = new Grid(40, 40);
		}
		else
		{
			grid.drawGrid(g);
			String output;
			String input;
			if(grid.getFacingObject() instanceof Food)
				input = "1";
			else
				input = "0";
			output = pocketMachine.execute(input);
			grid.execute(output);
			displayTokens--;
		}
		
		gr.drawImage(bufferedImage, 0, 0, this);
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		repaint();
	}
}
