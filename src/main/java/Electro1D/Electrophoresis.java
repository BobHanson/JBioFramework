package main.java.Electro1D;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

//
import main.java.Utilities.BrowserLauncher;

/**
 * @author Bader Alharbi Desktop application The Swing version 1D
 *         Electrophoresis simulation,
 */
public class Electrophoresis extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6625659357016475348L;
	JTabbedPane tabPane, tabPane2;
	Parameters paramPanel;
	Simulation simPanel;
	ProteinData dataPanel;
	Plot plotPanel;

	public void Header() {
		// @TODO: figure out where we want to put the help button on E1D
		JPanel headPanel = new JPanel();
		JButton help = new JButton("Help");
		help.setToolTipText("Open Help wiki for Electro1D");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "http://sourceforge.net/p/jbf/wiki/Electro1D";
				try {
					BrowserLauncher.openURL(url);
				} catch (IOException i) {
					System.err.println(i.getMessage());
				}
			}
		});
		headPanel.add(help);
		super.add(headPanel);
	}

	public Electrophoresis() {
		super.setPreferredSize(new Dimension(875, 450));//550(i think i want 825, 450
		
		paramPanel = new Parameters(this);
		simPanel = new Simulation(this);
		dataPanel = new ProteinData(this);
		plotPanel = new Plot(this);

//        super.setSize(20,35);
		this.setLayout(new GridLayout(1, 2, 5, 0));
		// set up left Panel
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.setPreferredSize(new Dimension(275, 415));
		paramPanel.setPreferredSize(new Dimension(275, 415));
		dataPanel.setPreferredSize(new Dimension(275, 415));
		tabPane = new JTabbedPane();
		tabPane.addTab("Parameters", paramPanel);
		tabPane.addTab("ProteinData", dataPanel);
		
		tempPanel.add(tabPane);
		
		// set up right Panel
		JPanel tempPanel2 = new JPanel();
		
		tabPane2 = new JTabbedPane();
		tabPane2.add("Casting Tray ", simPanel);
		tabPane2.add("Plot", plotPanel);

//        this.add(headPanel);
		this.add(tempPanel);//tabPane
		this.add(tabPane2);

		paramPanel.setDefaults();

	}

	/**
	 * addStandard() add standards proteins
	 */
	public void addStandard() {

		simPanel.addStandard();
	}

	/**
	 * Stop running the simulation on the gel
	 */
	public void stopRun() {

		simPanel.stopRun();
	}

	/**
	 * display the simulation panel
	 */
	public void displaySim() {

		tabPane.setVisible(true);
	}

	/**
	 * set the acrylamide percentage
	 *
	 * @param acrylamide acrylamide reference
	 */
	public void setAcrylamide(Acrylamide acrylamide) {

		simPanel.setAcrylamide(acrylamide);
	}

	/**
	 * start running the simulation
	 *
	 * @param aprotein is an array of standard proteins (known)
	 * @param protein  is a unknown protein (sample)
	 * @param protein1 is a dye
	 * @param protein2 is a dye
	 */
	public void startRun(Protein aprotein[], Protein protein, Protein protein1, Protein protein2) {
		simPanel.startRun(aprotein, protein, protein1, protein2);
	}

	/**
	 * setPlotData() set the plot data
	 *
	 * @param aprotein is an array of standard proteins (known)
	 * @param protein  is a unknown protein (sample)
	 * @param protein1 is a dye
	 */
	public void setPlotData(Protein aprotein[], Protein protein, Protein protein1) {
		plotPanel.setResults(aprotein, protein, protein1);
	}

	/**
	 * displayData()display the data
	 */
	public void displayData() {
		tabPane2.setVisible(true);

	}

	/**
	 * adding the sample
	 */
	public void addSample() {

		simPanel.addSample();
	}

	/**
	 * configure the animation speed
	 *
	 * @param s the animation speed
	 */
	public void setAnimationSpeed(String s) {
		simPanel.setPause(s);
	}

	/**
	 * displayProtein(Protein protein) display protein info on the plotting panel
	 *
	 * @param protein the protein to display
	 */
	public void displayProtein(Protein protein) {
		dataPanel.displayData(protein);
	}

	static public void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(new Electrophoresis());
		f.pack();
		f.setVisible(true);
	}

}