package aston.group20.GUIview;
import aston.group20.model.FuelStrategy;
import aston.group20.model.Simulator;
import aston.group20.model.Strategy;
import aston.group20.model.WaitingTimeStrategy;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


public class GUI {
	
	private Simulator sim;
	
	private JFrame mainFrame;	
	private JFrame reportFrame;
	private LabelledSlider commercialSlider; // (g slider)
	private LabelledSlider gliderSlider; // probability of glider creation
	private LabelledSlider lightSlider; // probability of light creation
	private LabelledSlider timeSlider; // how long the simulation will run for
	private JComboBox<String> strategy; // selects the strategy to be used - still working on how to implement this - may have strategy classes
	private String[] strategies = {
			"Waiting Time Strategy",
			"Fuel Strategy"		
	};
	private static final int PROB_MIN = 0;
	private static final int PROB_MAX = 1;
	// need a JScrollPane for the report window
	
	public GUI(Simulator sim) {
		final int padding = 6;
		this.sim = sim;
		
		// Step 1: create the components
		JButton runButton = new JButton();
		JButton quitButton = new JButton();
		
		// Step 2: Set the properties of the components
		gliderSlider = new LabelledSlider("Glider Probability", 0.002, PROB_MIN, PROB_MAX, 1); //the 3 sliders need things added betWeen the ()
		lightSlider = new LabelledSlider("Light Aircraft Probability", 0.005, PROB_MIN, PROB_MAX, 1);
		commercialSlider = new LabelledSlider("Commercial Aircraft Probability (g)", 0.1, PROB_MIN, PROB_MAX, 1);
		timeSlider = new LabelledSlider("Simulation Length", 2880, 1, 10000, 1);
		strategy = new JComboBox<>(strategies);
		timeSlider.setMajorTickSpacing(120); // 1 hour
		
		
		runButton.setText("Run");
		runButton.setToolTipText("Run simulation");
		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit simulation");
		gliderSlider.setToolTipText("Set probability of generating a glider.");
		lightSlider.setToolTipText("Set probability of generating a light aircraft.");
		commercialSlider.setToolTipText("Set probability of generating a commercial aircraft.");
		
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("100/100 Airport Simulator");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel commandBox = new JPanel();
		JPanel sliderBox = new JPanel();
		
		// Step 4: Specify LayoutManagers
		mainFrame.setLayout(new BorderLayout());
		((JPanel)mainFrame.getContentPane()).setBorder(new 
				EmptyBorder(padding, padding, padding, padding));
		commandBox.setLayout(new FlowLayout());
		commandBox.setBorder(new 
				EmptyBorder(padding, padding, padding, padding));
		sliderBox.setLayout(new BorderLayout());
		sliderBox.setBorder(new 
				EmptyBorder(padding, padding, padding, padding));
		
		// Step 5: Add components to containers 
		commandBox.add(timeSlider);
		commandBox.add(strategy);
		commandBox.add(runButton);
		commandBox.add(quitButton);
		sliderBox.add(gliderSlider, BorderLayout.NORTH);
		sliderBox.add(lightSlider, BorderLayout.CENTER);
		sliderBox.add(commercialSlider, BorderLayout.SOUTH);
		mainFrame.add(commandBox, BorderLayout.EAST);
		mainFrame.add(sliderBox, BorderLayout.WEST);
		
		// Step 6: Arrange to handle events in the user interface
		
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStrategy();
				runSimulation();
			}
		});
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp();
			}
		});
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		// Step 7: Display the GUI
		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
	
	/**
	 * Helper method to ensure consistency in leaving application.
	 */
	private void exitApp() {
		// Display confirmation dialog before exiting application
		int response = JOptionPane.showConfirmDialog(mainFrame, 
				"Do you really want to quit?",
				"Quit?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
		// Don't quit
	}
	
	private void runSimulation() {	
		sim.simulate((int) timeSlider.getValue()); // should be used in the reportFrame
	}
	
	private void setStrategy() {
		System.out.println(strategy.getSelectedIndex() + "bbbbbbbbbbbbbbbboooooooooooooooobs");
		switch (strategy.getSelectedIndex()) {
		case 0: sim.setStrategy(0);
		break;
		case 1: sim.setStrategy(1);
		}
	}
}