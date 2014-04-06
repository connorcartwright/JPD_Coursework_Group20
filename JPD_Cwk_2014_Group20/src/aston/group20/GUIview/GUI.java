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
	private JComboBox<String> strategy; // selects the strategy to be used -
										// still working on how to implement
										// this - may have strategy classes
	private String[] strategies = { "Waiting Time Strategy", "Fuel Strategy" };
	private static final int padding = 5;
	private boolean reportOpen = false;

	// need a JScrollPane for the report window

	public GUI(Simulator sim) {
		this.sim = sim;

		// Step 1: create the components
		JButton runButton = new JButton("Run Simulation");
		JButton quitButton = new JButton("Quit");

		LabelledSlider commercialSlider = new LabelledSlider(
				"Commercial Probability", 0.1, 0, 1, 1);
		LabelledSlider gliderSlider = new LabelledSlider("Glider Probability",
				0.002, 0, 1, 1);
		LabelledSlider lightSlider = new LabelledSlider("Light Probability",
				0.005, 0, 1, 1);

		strategy = new JComboBox<>(strategies);

		results = new JTextArea();
		results.setEditable(false);

		// Step 2: Set the properties of the components
		lengthSlider = new LabelledSlider("Simulation Length", 2880, 1, 10000, 1);
		lengthSlider.setMajorTickSpacing(720); // 6 hours
		lengthSlider.setPreferredSize(new Dimension(500, 80));

		runButton.setToolTipText("Run the simulation.");
		quitButton.setToolTipText("Exit the application.");

		gliderSlider.setToolTipText("Used to set the probability of generating a glider.");
		gliderSlider.setPreferredSize(new Dimension(275, 70));

		lightSlider.setToolTipText("Used to set the probability of generating a light aircraft.");
		lightSlider.setPreferredSize(new Dimension(275, 70));

		commercialSlider.setToolTipText("Used to set the probability of generating a commercial aircraft.");
		commercialSlider.setPreferredSize(new Dimension(275, 70));

		strategy.setPreferredSize(new Dimension(250, 80));
		strategy.setMaximumSize(new Dimension(250, 80));
		strategy.setMinimumSize(new Dimension(250, 80));

		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("100/100 Airport Simulator");
		mainFrame.getContentPane().setBackground(new Color(0, 178, 238));
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(800, 260));
		mainFrame.setResizable(false);

		JPanel commandBox = new JPanel();
		JPanel sliderBox = new JPanel();
		JPanel buttonBox = new JPanel();

		// Step 4: Specify LayoutManagers
		mainFrame.setLayout(new BorderLayout());
		((JPanel) mainFrame.getContentPane()).setBorder(new EmptyBorder(
				padding, padding, padding, padding));
		commandBox.setLayout(new BorderLayout());
		commandBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		sliderBox.setLayout(new BorderLayout());
		sliderBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		buttonBox.setLayout(new BorderLayout());
		buttonBox.setBorder(new EmptyBorder(padding, padding, padding, padding));

		// Step 5: Add components to containers
		buttonBox.add(strategy, BorderLayout.NORTH);
		buttonBox.add(runButton, BorderLayout.WEST);
		buttonBox.add(quitButton, BorderLayout.EAST);
		

		commandBox.add(lengthSlider, BorderLayout.NORTH);
		//commandBox.add(strategy, BorderLayout.CENTER);
		commandBox.add(buttonBox, BorderLayout.SOUTH);

		sliderBox.add(gliderSlider, BorderLayout.NORTH);
		sliderBox.add(lightSlider, BorderLayout.CENTER);
		sliderBox.add(commercialSlider, BorderLayout.SOUTH);

		mainFrame.add(commandBox, BorderLayout.EAST);
		mainFrame.add(sliderBox, BorderLayout.WEST);

		// Step 6: Arrange to handle events in the user interface
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reportOpen) {
					
				} else {
					setStrategy();
					openReport();
					runSimulation();
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
			}
		});

		runAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetSimulation();
				results.setText(null);
				runSimulation();
			}
		});
		
		reportFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				reportOpen = false;
				resetSimulation();
				results.setText(null);
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
		sim.reset();
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

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}
}