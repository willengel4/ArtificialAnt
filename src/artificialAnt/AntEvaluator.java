package artificialAnt;

import java.awt.Graphics;
import java.util.ArrayList;

import geneticStateMachine.Evaluator;
import geneticStateMachine.StateMachine;


public class AntEvaluator extends Evaluator
{
	private int numStates;
	private int numInputs;
	private int numOutputs;

	public AntEvaluator(int numStates, int numInputs, int numOutputs)
	{
		this.numStates = numStates;
		this.numInputs = numInputs;
		this.numOutputs = numOutputs;
	}
	
	public double evaluate(String encoding) 
	{
		Grid grid = new Grid(20, 20);
		
		/* Create the stateMachine to move the ant
		 * Use the machine to get the ant's moves */
		StateMachine stateMachine = new StateMachine(numStates, numInputs, numOutputs, encoding);		
		
		/* Read the input environment, use the state machine to calculate
		 * the action, execute the action, read the input environment... */
		for(int i = 0; i < 28; i++)
		{
			String output;
			String input;
			if(grid.getFacingObject() instanceof Food)
				input = "1";
			else
				input = "0";
			
			output = stateMachine.execute(input);
			grid.execute(output);
		}
		
		return grid.getScore();
	}	
}
