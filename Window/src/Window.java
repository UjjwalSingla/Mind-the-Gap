import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/*
 *  The main window of the gui
 *  Notice that it extends JFrame - so we can add our own components.
 *  Notice that it implements ActionListener - so we can handle user input.
 */
public class Window extends JFrame implements ActionListener
{
	// gui components that are contained in this frame:
	private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
	private JLabel topLabel;				// a text label to appear in the top panel
	private JButton topButton;				// a 'reset' button to appear in the top panel
	private GridSquare [][] gridSquares;// squares to appear in grid formation in the bottom panel
	//private int x,y;					// the size of the grid
	public int tmp,column,row;
	
	/*
	 *  constructor method takes as input how many rows and columns of gridsquares to create
	 *  it then creates the panels, their subcomponents and puts them all together in the main frame
	 *  it makes sure that action listeners are added to selectable items
	 *  it makes sure that the gui will be visible
	 */
	public Window()
	{
		//this.x = x;
		//this.y = y;
		
		// first create the panels
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout( new GridLayout( 4, 4));
		
		// then create the components for each panel and add them to it
		
		// for the top panel:
		topLabel = new JLabel("Don't Select the Neighbour Square!Click the Buttons>>");
		topButton = new JButton("New Game");
		topButton.addActionListener( this);			// IMPORTANT! Without this, clicking the button does nothing.
		
		topPanel.add( topLabel,BorderLayout.CENTER);
		topPanel.add ( topButton,BorderLayout.EAST);
		
	
		// for the bottom panel:	
		// create the buttons and add them to the grid
		gridSquares = new GridSquare [4][4];
		for ( int column = 0; column < 4; column ++)
		{
			for ( int row = 0; row < 4; row ++)
			{
				gridSquares [column][row] = new GridSquare( 4,4);
				gridSquares [column][row].setSize( 20, 20);
				gridSquares [column][row].setColor( column + row);
				gridSquares [column][row].setOpaque( true);				// without this line and the next the OS' default
				gridSquares [column][row].setBorderPainted( true);		// look & feel will dominate / interfere
																		// (try commenting each out & see what happens)
				
				gridSquares [column][row].addActionListener( this);		// AGAIN, don't forget this line!
				
				bottomPanel.add( gridSquares [column][row]);
			}
		}
		
		// now add the top and bottom panels to the main frame
		getContentPane().setLayout( new BorderLayout());
		getContentPane().add( topPanel, BorderLayout.NORTH);
		getContentPane().add( bottomPanel, BorderLayout.CENTER);
		pack();
		
		// housekeeping : behaviour
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		setResizable( true);
		setSize(500,500);
		setVisible( true);
	}
	
	
	/*
	 *  handles actions performed in the gui
	 *  this method must be present to correctly implement the ActionListener interface
	 */
	public void actionPerformed (ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();
		if ( selected.equals( topButton) )
		{
		    java.util.Random random = new java.util.Random();
		    tmp = random.nextInt(2) + 1;
		 
		    
			topLabel.setText("Player " + tmp + " turn");
			
			for ( int column = 0; column < 4; column ++)
			{
				for ( int row = 0; row < 4; row ++)
				{
					gridSquares [column][row].setColor( column + row);
				}
			}
		}
		if ( selected instanceof GridSquare)
		{
			((GridSquare) selected).switchColor(tmp);
			tmp = tmp+1;
			if (tmp%2==0) {
				tmp=2;
				topLabel.setText("Player " + tmp + " turn");
				((GridSquare) selected).switchColor(tmp);
				
				
				}
				
			else {
				tmp =1;
				topLabel.setText("Player " + tmp + " turn");
				((GridSquare) selected).switchColor(tmp);
			}
		
		
	}
	}}
