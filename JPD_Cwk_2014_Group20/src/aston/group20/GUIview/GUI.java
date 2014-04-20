package aston.group20.GUIview;

import aston.group20.model.Simulator; // importing required resources

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI class is used to provide the user with a graphical interface
 * so that they can interact with the program. It allows them to change the 
 * various probabilities of creating aircraft as well as the length of the simulation.
 * It gives them both a brief and a detailed view of the results of the simulation, and
 * also allows the user to save the detailed information to a text file in a location
 * of their choice.
 *
 * @author Group_20
 * @version 1.0, April 2014
 */
public class GUI {

	private Simulator sim; // the simulator to be used
	private JFrame mainFrame; // the mainframe/backbone of the GUI
	private JTextArea results, longResults; // text areas for the report and long report frames
	private LabelledSlider commercialSlider, gliderSlider, lightSlider, lengthSlider; // sliders for the mainframe
	private JComboBox<String> strategy; // lets the user select the strategy to be used
	private JComboBox<Integer> seed; // lets the user select the seed to be used
	private String[] strategies = { "Waiting Time Strategy", "Fuel Strategy" }; // add more strategies here when needed in the future
	private Integer[] seedSelection = new Integer[100]; // array holding the seed numbers
	{  for(int i = 0; i < 100; i++) { seedSelection[i] = i+1; } }; // populating the seed array
	private static final int padding = 5; // blank space for layout management
	private boolean reportOpen, longReportOpen = false; // initialising so that we know the report frames aren't open
	protected Component errorFrame; // used when producing the error message

    /**
     * Creating a new AirControlTower, which will initialise the queues
     * and set the strategy for how to manage the Incoming aircraft.
     * 
     * @param sim the Simulator that will be used for the GUI
     * @see Simulator to understand the methods used
     */
	public GUI(Simulator sim) {
		this.sim = sim;

		// Step 1: create the components
		JButton runButton = new JButton("Run Simulation"); // creating/initialising the mainframe buttons
		JButton quitButton = new JButton("Quit");
		JLabel seedText = new JLabel("Seed: "); // creating/initialising the mainframe labels
		JLabel strategyText = new JLabel("Strategy: ");

		lengthSlider = new LabelledSlider("Simulation Length: ", 2880, 60, 10000, 1); // initialising the sliders
		commercialSlider = new LabelledSlider("Commercial Probability (p): ",0.1, 0, 1000, 1000); // initialising the sliders
		gliderSlider = new LabelledSlider("Glider Probability: ", 0.002, 0,1000, 1000); // initialising the sliders
		lightSlider = new LabelledSlider("Light Probability: ", 0.005, 0, 1000, 1000); // initialising the sliders

		strategy = new JComboBox<String>(strategies); // initialising the combo boxes
		seed = new JComboBox<Integer>(seedSelection); // initialising the combo boxes

		results = new JTextArea(); // initialising the text areas and making them uneditable
		results.setEditable(false);
		longResults = new JTextArea(); // initialising the text areas and making them uneditable
		longResults.setEditable(false);

		// Step 2: Set the properties of the components
		runButton.setToolTipText("Run the simulation."); // setting the tooltip of the buttons and their preferred size
		runButton.setPreferredSize(new Dimension(140, 34));
		quitButton.setToolTipText("Exit the application."); // setting the tooltip of the buttons and their preferred size
		quitButton.setPreferredSize(new Dimension(100, 34));
		
		lengthSlider.setToolTipText("Used to set the simulation length in half minutes.");
		lengthSlider.setMajorTickSpacing(720); // 6 hour major spacing
		lengthSlider.setPreferredSize(new Dimension(500, 80)); // setting the preferred size of the length slider

		commercialSlider.setToolTipText("Used to set the probability of generating a commercial aircraft.");
		commercialSlider.setPreferredSize(new Dimension(275, 70)); // setting the preferred size of the commercial slider
		
		gliderSlider.setToolTipText("Used to set the probability of generating a glider.");
		gliderSlider.setPreferredSize(new Dimension(275, 70)); // setting the preferred size of the glider slider

		lightSlider.setToolTipText("Used to set the probability of generating a light aircraft.");
		lightSlider.setPreferredSize(new Dimension(275, 70)); // setting the preferred size of the light slider 

		strategy.setPreferredSize(new Dimension(180, 32)); // setting the preferred size of the strategy combo box
		strategy.setToolTipText("Select the scheduling strategy to be used."); // setting the tooltip of the strategy combo box
		((JLabel) strategy.getRenderer()).setHorizontalAlignment(JLabel.CENTER); // centering the text in the strategy combo box
		seed.setPreferredSize(new Dimension(60, 32)); // setting the preferred size of the seed combo box
		seed.setToolTipText("Select the seed the random number generator will use."); // setting the tooltip of the seed combo box
		((JLabel) seed.getRenderer()).setHorizontalAlignment(JLabel.CENTER); // centering the text in the seed combo box

		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("100/100 Airport Simulator"); // initialising the mainframe
		mainFrame.getContentPane().setBackground(new Color(61, 145, 64)); // setting the background colour of the mainframe
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting the default close operation
		mainFrame.setPreferredSize(new Dimension(800, 260)); // setting the preferred size
		mainFrame.setResizable(false); // making it so that the frame cannot be resized, making the preferred size the fixed size.

		JPanel commandBox = new JPanel(); // initialising the panels that will be present on the mainframe
		JPanel sliderBox = new JPanel(); // that will hold the components that need to be used
		JPanel buttonBox = new JPanel(); // initialising the panels that will be present on the mainframe
		JPanel comboHolder = new JPanel(); // that will hold the components that need to be used

		// Step 4: Specify LayoutManagers
		mainFrame.getContentPane().setLayout(new BorderLayout()); // set the layout for the mainframe
		((JPanel) mainFrame.getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));

		commandBox.setLayout(new BorderLayout()); // set the layout for the command panel
		commandBox.setBorder(new EmptyBorder(padding, padding, padding, padding));

		sliderBox.setLayout(new BorderLayout()); // set the layout for the slider panel
		sliderBox.setBorder(new EmptyBorder(padding, padding, padding, padding));

		buttonBox.setLayout(new BorderLayout()); // set the layout for the button panel
		buttonBox.setBorder(new EmptyBorder(padding, padding, padding, padding));

		comboHolder.setLayout(new FlowLayout()); // set the layout for the combo panel
		comboHolder.setBorder(new EmptyBorder(20, padding, padding, padding));

		buttonBox.add(runButton, BorderLayout.WEST); // adding the buttons to the button panel
		buttonBox.add(quitButton, BorderLayout.EAST); // adding the buttons to the button panel

		comboHolder.add(strategyText); // adding the text/combo boxes to the combo panel
		comboHolder.add(strategy); // adding the text/combo boxes to the combo panel
		comboHolder.add(seedText); // adding the text/combo boxes to the combo panel
		comboHolder.add(seed); // adding the text/combo boxes to the combo panel

		commandBox.add(lengthSlider, BorderLayout.NORTH); // adding the length slider to the command panel at the top
		commandBox.add(comboHolder, BorderLayout.CENTER); // adding the combo panel to the command panel at the middle
		commandBox.add(buttonBox, BorderLayout.SOUTH); // adding the button panel to the command panel at the bottom

		sliderBox.add(commercialSlider, BorderLayout.NORTH); // adding the sliders to the slider panel
		sliderBox.add(gliderSlider, BorderLayout.CENTER); // adding the sliders to the slider panel
		sliderBox.add(lightSlider, BorderLayout.SOUTH); // adding the sliders to the slider panel

		mainFrame.getContentPane().add(commandBox, BorderLayout.EAST); // adding the command box to the right of the mainframe
		mainFrame.getContentPane().add(sliderBox, BorderLayout.WEST); // adding the slider box to the left of the mainframe

		// Step 6: Arrange to handle events in the user interface
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reportOpen) {
					// do nothing if the report window is already open.
				} 
				else {
					if (commercialSlider.getValue() + gliderSlider.getValue() + lightSlider.getValue() > 1) { // if the combined probabilities are more than 1 / ineligble
						JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
								"The combined probabilties cannot be more than 1. \n Please adjust the values.",
								"Aircraft Probability Error", JOptionPane.ERROR_MESSAGE);
					} 
					else {
						resetSimulation(); // ELSE, reset the simulation / clear the previous results
						openReport(); // open the report window
						runSimulation(); // and run the simulation
					}
				}
			}
		});

		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp(); // run the exitApp method if the quit button is pressed
			}
		});

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp(); // run the exitApp method if the window 'x' button is pressed
			}
		});

		// Step 7: Display the GUI
		mainFrame.pack(); // build the mainframe
		centreWindow(mainFrame); // centre the mainframe
		mainFrame.setVisible(true); // and make it visible

	}

	/**
	 * This method is called from the action listener on the Run button in 
	 * the GUI constructor/on the mainframe. It opens the smaller of the two report windows, 
	 * which shows short details regarding the currently run simulation. Buttons
	 * will be included which allow the user to run the simulation again, as well as
	 * one that will allow the user to show more detailed information about the simulation.
	 */
	private void openReport() {
		reportOpen = true;
		final String reportFrameString = "ReportFrame"; // Used to find client property

		// Step 1: create the components
		final JFrame reportFrame = new JFrame("Simulation Results");
		JScrollPane listScroller = new JScrollPane(results);
		listScroller.setPreferredSize(new Dimension(220, 150));
		listScroller.setMinimumSize(new Dimension(220, 150));
		
		JButton runAgainButton = new JButton("Run Again");
		JButton closeButton = new JButton("Close");
		JButton detailsButton = new JButton("Details");

		// Step 2: Set the properties of the components
		closeButton.putClientProperty(reportFrameString, reportFrame);
		closeButton.setToolTipText("Close this window.");
		runAgainButton.setToolTipText("Run the simulation again.");
		detailsButton.setToolTipText("Show a more detailed report.");

		// Step 3: Create containers to hold the components
		reportFrame.setPreferredSize(new Dimension(240, 270));
		reportFrame.setResizable(false); // make it so the frame can't be resized
		reportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // setting default close operation

		JPanel reportPanel = new JPanel(); // creating the panel to hold the text
		JPanel buttonPanel = new JPanel(); // creating the panel to hold the buttons

		// Step 4: Specify LayoutManagers
		reportFrame.getContentPane().setLayout(new BorderLayout()); // setting the layout for the report frame
		((JComponent) reportFrame.getContentPane()).setBorder(new EmptyBorder(
				padding, padding, padding, padding));
		
		reportPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		reportPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		buttonPanel.setLayout(new FlowLayout()); // setting the layout for the panel holding the buttons
		buttonPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));

		// Step 5: Add components to containers
		reportPanel.add(listScroller, BorderLayout.NORTH); // add the text to the report panel
		
		buttonPanel.add(detailsButton); // adding the buttons to the button panel
		buttonPanel.add(runAgainButton);
		buttonPanel.add(closeButton);
		
		reportFrame.getContentPane().add(reportPanel, BorderLayout.NORTH); // put the text/report at the top of the panel
		reportFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

		// Step 6: Arrange to handle events in the user interface

		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent c = (JComponent) e.getSource();
				JFrame f = (JFrame) c.getClientProperty(reportFrameString);
				f.dispose(); // close the window and switch focus back to the mainframe
				reportOpen = false; // the report is no longer open
				resetSimulation(); // reset the results of the window
			}
		});

		runAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetSimulation(); // clear the results
				runSimulation(); // and run the simulation
			}
		});

		detailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (longReportOpen) {
					// do nothing if the window is already open.
				} else {
					longResults.setText(null); // clear the text
					longResults.append(getSimulationDetails()); // add the simulation details
					longResults.append(sim.getLongReport().toString()); // add the simulation results
					openLongReport(); // and open the long report frame
				}
			}
		});

		reportFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				reportOpen = false; // the report frame is no longer open
				resetSimulation(); // reset the results
			}
		});

		// Step 7: Display the GUI
		reportFrame.pack(); // pack the report frame
		centreWindow(reportFrame); // centre the report frame
		reportFrame.setVisible(true); // and make it visible
	}

	/**
	 * This method is called from the action listener on the Details button in
	 * the openReport method/window. It opens the larger of the two report windows,
	 * which displays detailed information regarding the currently run simulation. Buttons
	 * are included that allow the user to save the results of the simulation to a text file 
	 * of their choice, as well as one that will return the user to the smaller report window.
	 */
	private void openLongReport() {
		longReportOpen = true;
		// Step 1: create the components
		final JFrame longReportFrame = new JFrame("Detailed Results"); // creating the frame to be used
		
		JScrollPane listScroller = new JScrollPane(longResults); // creating the listscroller and setting the text it takes
		
		JButton closeButton = new JButton("Close"); // creating the buttons
		JButton saveFileButton = new JButton("Save to file"); // creating the buttons

		// Step 2: Set the properties of the components
		closeButton.setToolTipText("Close this window."); // setting the tooltip text of the buttons
		saveFileButton.setToolTipText("Save the results of the simulation to a text file."); // setting the tooltip text of the buttons
		
		listScroller.setPreferredSize(new Dimension(620, 506)); // setting the preferred size of the listscroller

		// Step 3: Create containers to hold the components
		longReportFrame.setPreferredSize(new Dimension(1000, 600)); // setting the preferred/fixed size
		longReportFrame.setResizable(false); // setting it so that the window can't be changed in size
		longReportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // setting close operation

		JPanel reportPanel = new JPanel(); // create the panel to hold the text
		JPanel buttonPanel = new JPanel(); // create the panel to hold the buttons
		
		// Step 4: Specify LayoutManagers
		longReportFrame.getContentPane().setLayout(new BorderLayout()); // setting the layout for the long report frame
		((JComponent) longReportFrame.getContentPane()).setBorder
		(new EmptyBorder(padding, padding, padding, padding));
		
		reportPanel.setLayout(new BorderLayout()); // setting the layout for the area containing the text
		reportPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		buttonPanel.setLayout(new FlowLayout()); // setting the layout for the area containing the buttons
		buttonPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));

		// Step 5: Add components to containers
		reportPanel.add(listScroller, BorderLayout.NORTH); // adding the listScroller
		buttonPanel.add(saveFileButton); // adding the save button
		buttonPanel.add(closeButton); // adding the close button
		longReportFrame.getContentPane().add(reportPanel, BorderLayout.NORTH); // put the text at the top
		longReportFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER); // put the buttons just below the text

		// Step 6: Arrange to handle events in the user interface
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				longReportFrame.dispose(); // switch focus back to the previous window
				longReportOpen = false; // the longReport is no longer open
			}
		});

		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser(); // create new file chooser
				saveFile.setCurrentDirectory(new File("Documents")); // default location at My Documents
				int retrieval = saveFile.showSaveDialog(null);
				if (retrieval == JFileChooser.APPROVE_OPTION) { // if the user chose to save the file
					try (FileWriter fw = new FileWriter(saveFile.getSelectedFile() + ".txt")) {
						longResults.write(fw); // write the results to the file
					} catch (Exception ex) {
						ex.printStackTrace(); // else print details as to why it didn't work
					}
				}
			}
		});

		longReportFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				longReportOpen = false; // the longReport is no longer open
			}
		});

		// Step 7: Display the GUI
		longReportFrame.pack();
		centreWindow(longReportFrame); // centre the window
		longReportFrame.setVisible(true); // make the longReport frame visible

	}

	/**
	 * Helper method to ensure consistency in leaving application.
	 */
	private void exitApp() {
		// Display confirmation dialog before exiting application
		int response = JOptionPane.showConfirmDialog(mainFrame,
				"Do you really want to quit?", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		// Don't quit
	}
	
	/**
	 * This method is called when the simulation is required to run, e.g. when the
	 * Run button is pressed on the mainframe, or when the Run Again button is 
	 * pressed on the smaller report window. It runs the simulation for the value
	 * selected on the length slider, and adds the results to the results text area.
	 * 
	 */
	private void runSimulation() {
		sim.simulate((int) lengthSlider.getValue());
		results.append(sim.getResults());
	}

	/**
	 * This method is called when the simulation is first run, or is rerun, so that the
	 * the changes to the mainframe can be accounted for, e.g. a change in the seed or 
	 * strategy. It resets the simulation, updates the variables and clears the report text.
	 */
	private void resetSimulation() {
		setSeed();
		setStrategy();
		setProbabilities();
		sim.reset();
		results.setText(null);
	}

	/**
	 * This method is called from the resetSimulation method; it gets the value
	 * of the three probability sliders (commercial, glider, light) and applies those
	 * values to the Simulation variable using the setProbabilities method.
	 * 
	 * @see resetSimulation
	 * @see setProbabilities
	 */
	private void setProbabilities() {
		sim.setProbabilities(commercialSlider.getValue(),
				gliderSlider.getValue(), lightSlider.getValue());
	}

	/**
	 * This method is called from the resetSimulation method; it gets the selected 
	 * index of the strategy combo box and then selects that strategy in the Simulation
	 * class.
	 * 
	 * @see resetSimulation
	 * @see setStrategy in the Simulation class.
	 */
	private void setStrategy() {
		switch (strategy.getSelectedIndex()) {
		case 0:
			sim.setStrategy(0);
			break;
		case 1:
			sim.setStrategy(1);
			break;
		case 2: // new Strategy here when required;
			break;
		}
	}

	/**
	 * This method is called from the resetSimulation method; it gets the selected
	 * index of the seed combo box and then applies that seed to the 'sim' Simulation 
	 * object.
	 * 
	 * @see resetSimulation
	 * @see setSeed
	 */
	private void setSeed() {
		sim.setSeed(seed.getSelectedIndex());
	}

	/**
	 * This method is called from the longReport window/the openLongReport method. 
	 * It is essentially used as the header of the report to display summary information.
	 * It is very useful for saved text files, as the user can see what seed/probabilities/etc
	 * was used for a simulation.
	 */
	private String getSimulationDetails() {
		return (" Simulation Length:  " + (int) lengthSlider.getValue() + "     |     " + 
	            "Strategy:  " + strategy.getSelectedItem() + "     |     " + 
				"Seed:  " + seed.getSelectedIndex() + "     |     " + 
	            "Commercial Probability: "+ commercialSlider.getValue() + "     |     " + 
				"Glider Probability: " + gliderSlider.getValue() + "     |     " + 
	            "Light Probability: " + lightSlider.getValue() + "\n \n");
	}

	/**
	 * This method centres the GUI window and works on all screen sizes.
	 * With credit to Don @ StackOverflow.
	 * 
	 * @param frame the window/frame to be centred
	 * @see http://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
	 */
	private static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}