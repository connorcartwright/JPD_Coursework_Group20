package aston.group20.GUIview;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class GUI {

	private JFrame mainFrame;
	private JFrame reportFrame;
	private LabelledSlider commercialSlider; // (g slider)
	private LabelledSlider gliderSlider;
	private LabelledSlider lightSlider;
	private LabelledSlider timeSlider;
	private JComboBox strategy;
	
	public GUI() {
		JButton runButton = new JButton();
		JButton quitButton = new JButton();
		
		gliderSlider = new LabelledSlider(); //the 3 sliders need things added beteen the ()
		lightSlider = new LabelledSlider();
		commercialSlider = new LabelledSlider();
		
		runButton.setText("Run");
		runButton.setToolTipText("Run simulation");
		quitButton.setText("Quit");
		quitButton.setToolTipText("Quit simulation");
		
		gliderSlider.setToolTipText("Set probability of generating a glider.");
		lightSlider.setToolTipText("Set probability of generating a light aircraft.");
		commercialSlider.setToolTipText("Set probability of generating a commercial aircraft.");
	}

}
