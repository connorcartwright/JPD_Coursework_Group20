package aston.group20.GUIview;

import aston.group20.model.Simulator;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.*;

public class GUI {

	private Simulator sim;

	private JFrame mainFrame;
	private JTextArea results;
	private LabelledSlider lengthSlider; // how long the simulation will run for
	private JComboBox<String> strategy; // lets the user select the strategy to be used
	private JComboBox<Integer> seed;
	private String[] strategies = { "Waiting Time Strategy", "Fuel Strategy" }; // add more strategies here when needed in the future
	private Integer[] seedSelection = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 
			19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 };
	private static final int padding = 5; // blank space for layout management
	private boolean reportOpen = false;

	public GUI(Simulator sim) {
		this.sim = sim;

		// Step 1: create the components
		JButton runButton = new JButton("Run Simulation");
		JButton quitButton = new JButton("Quit");
		JLabel seedText = new JLabel("Seed: ");
		JLabel strategyText = new JLabel("Strategy: ");

		LabelledSlider commercialSlider = new LabelledSlider("Commercial Probability: ", 0.1, 0, 1, 1);
		LabelledSlider gliderSlider = new LabelledSlider("Glider Probability: ", 0.002, 0, 1, 1);
		LabelledSlider lightSlider = new LabelledSlider("Light Probability: ",0.005, 0, 1, 1);

		strategy = new JComboBox(strategies);
		seed = new JComboBox(seedSelection);
		
		results = new JTextArea();
		results.setEditable(false);

		// Step 2: Set the properties of the components
		lengthSlider = new LabelledSlider("Simulation Length: ", 2880, 60, 10000, 1);
		lengthSlider.setMajorTickSpacing(720); // 6 hours
		lengthSlider.setPreferredSize(new Dimension(500, 80));

		runButton.setToolTipText("Run the simulation.");
		runButton.setPreferredSize(new Dimension(140, 34));
		quitButton.setToolTipText("Exit the application.");
		quitButton.setPreferredSize(new Dimension(100, 34));

		gliderSlider.setToolTipText("Used to set the probability of generating a glider.");
		gliderSlider.setPreferredSize(new Dimension(275, 70));

		lightSlider.setToolTipText("Used to set the probability of generating a light aircraft.");
		lightSlider.setPreferredSize(new Dimension(275, 70));

		commercialSlider.setToolTipText("Used to set the probability of generating a commercial aircraft.");
		commercialSlider.setPreferredSize(new Dimension(275, 70));
		
		strategy.setPreferredSize(new Dimension(180, 32));
		((JLabel)strategy.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		seed.setPreferredSize(new Dimension(60, 32));
		((JLabel)seed.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("100/100 Airport Simulator");
		mainFrame.getContentPane().setBackground(new Color(0, 178, 238));
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(800, 260));
		mainFrame.setResizable(false);

		JPanel commandBox = new JPanel();
		JPanel sliderBox = new JPanel();
		JPanel buttonBox = new JPanel();
		JPanel comboHolder = new JPanel();

		// Step 4: Specify LayoutManagers
		mainFrame.getContentPane().setLayout(new BorderLayout());
		((JPanel) mainFrame.getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		commandBox.setLayout(new BorderLayout());
		commandBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
	    sliderBox.setLayout(new BorderLayout());
		sliderBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		buttonBox.setLayout(new BorderLayout());
		buttonBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		comboHolder.setLayout(new FlowLayout());
		comboHolder.setBorder(new EmptyBorder(20, padding, padding, padding));
		
		buttonBox.add(runButton, BorderLayout.WEST);
		buttonBox.add(quitButton, BorderLayout.EAST);
		
		comboHolder.add(strategyText);
		comboHolder.add(strategy);
		comboHolder.add(seedText);
		comboHolder.add(seed);
		
		commandBox.add(comboHolder, BorderLayout.CENTER);
		commandBox.add(lengthSlider, BorderLayout.NORTH);
		commandBox.add(buttonBox, BorderLayout.SOUTH);

		sliderBox.add(gliderSlider, BorderLayout.NORTH);
		sliderBox.add(lightSlider, BorderLayout.CENTER);
		sliderBox.add(commercialSlider, BorderLayout.SOUTH);

		mainFrame.getContentPane().add(commandBox, BorderLayout.EAST);
		mainFrame.getContentPane().add(sliderBox, BorderLayout.WEST);

		// Step 6: Arrange to handle events in the user interface
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reportOpen) {
					// do nothing if the report window is already open.
				} else {
					resetSimulation(); // reset the simulation/clear previous results
					openReport(); // open the report window
					runSimulation(); // and run the simulation
				}
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
		centreWindow(mainFrame);
		mainFrame.setVisible(true);

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

	private void openReport() {
		reportOpen = true;
		final String reportFrameString = "ReportFrame"; // Used to find client property
		
		// Step 1: create the components
		JScrollPane listScroller = new JScrollPane(results);
		listScroller.setPreferredSize(new Dimension(220, 150));
		listScroller.setMinimumSize(new Dimension(220, 150));
		final JFrame reportFrame = new JFrame("Simulation Results");
		JButton runAgainButton = new JButton("Run Again");
		JButton closeButton = new JButton("Close");

		// Step 2: Set the properties of the components
		closeButton.putClientProperty(reportFrameString, reportFrame);
		closeButton.setToolTipText("Close this window.");
		runAgainButton.setToolTipText("Run the simulation again.");

		// Step 3: Create containers to hold the components
		reportFrame.setPreferredSize(new Dimension(240, 240));
		reportFrame.setResizable(false);
		reportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JPanel reportPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		// Step 4: Specify LayoutManagers
		reportPanel.setLayout(new BorderLayout());
		reportPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		reportFrame.getContentPane().setLayout(new BorderLayout());
		((JComponent) reportFrame.getContentPane()).setBorder(new EmptyBorder(
				padding, padding, padding, padding));

		// Step 5: Add components to containers
		reportPanel.add(listScroller, BorderLayout.NORTH);
		buttonPanel.add(runAgainButton);
		buttonPanel.add(closeButton);
		reportFrame.getContentPane().add(reportPanel, BorderLayout.NORTH);
		reportFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// Step 6: Arrange to handle events in the user interface

		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent c = (JComponent) e.getSource();
				JFrame f = (JFrame) c.getClientProperty(reportFrameString);
				f.dispose();
				reportOpen = false;
				resetSimulation();
			}
		});

		runAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetSimulation();
				runSimulation();
			}
		});
		
		reportFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				reportOpen = false;
				resetSimulation();
			}
		});

		// Step 7: Display the GUI
		reportFrame.pack();
		centreWindow(reportFrame);
		reportFrame.setVisible(true);
	}

	private void runSimulation() {
		sim.simulate((int) lengthSlider.getValue());
		results.append(sim.getResults());
	}

	private void resetSimulation() {
		setSeed();
		setStrategy();
		sim.reset();
		results.setText(null);
	}

	private void setStrategy() {
		switch (strategy.getSelectedIndex()) {
		case 0:
			sim.setStrategy(0);
			break;
		case 1:
			sim.setStrategy(1);
			break;
		case 2: // new Strategy here;
			break;
		}
	}
	
	private void setSeed() {
		sim.setSeed(seed.getSelectedIndex());
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}