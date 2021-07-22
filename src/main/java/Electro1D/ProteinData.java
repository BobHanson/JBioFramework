package main.java.Electro1D;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Bader Alharbi
 *         ProteinData.java class that handle the GUI components of the protein data panel
 */
public class ProteinData extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3836828666209394917L;
	Electrophoresis parent;
    String mw;
    JTextField name;
    JTextField fullName;
    JTextField abbr;
    JTextField molwt;
    JTextField logMolWt;
    JPanel titlePanel;
    JPanel namePanel;
    JPanel fullNamePanel;
    JPanel abbrPanel;
    JPanel molWtPanel;
    JPanel logMolWtPanel;

    /*
     * DEFAULT constructor  initiate the main GUI components of the protein data panel
     */
    ProteinData(Electrophoresis electrophoresis) {
        mw = "0";
        titlePanel = new JPanel();
        namePanel = new JPanel();
        fullNamePanel = new JPanel();
        abbrPanel = new JPanel();
        molWtPanel = new JPanel();
        logMolWtPanel = new JPanel();
        parent = electrophoresis;
        setLayout(new GridLayout(6, 1));
        titlePanel.setBackground(Color.lightGray);
        namePanel.setBackground(Color.lightGray);
        fullNamePanel.setBackground(Color.lightGray);
        abbrPanel.setBackground(Color.lightGray);
        molWtPanel.setBackground(Color.lightGray);
        logMolWtPanel.setBackground(Color.lightGray);
        titlePanel.add(new JLabel("PROTEIN DATA"));
        namePanel.add(new JLabel("Identifier"));
        name = new JTextField(8);
        namePanel.add(name);
        fullNamePanel.add(new JLabel("Protein Name"));
        fullName = new JTextField(15);
        fullNamePanel.add(fullName);
        abbrPanel.add(new JLabel("Abbreviation"));
        abbr = new JTextField(5);
        abbrPanel.add(abbr);
        molWtPanel.add(new JLabel("Molecular Wt"));
        molwt = new JTextField(5);
        molWtPanel.add(molwt);
        logMolWtPanel.add(new JLabel("Log Mol Wt"));
        logMolWt = new JTextField(5);
        logMolWtPanel.add(logMolWt);
        name.setEditable(false);
        fullName.setEditable(false);
        abbr.setEditable(false);
        molwt.setEditable(false);
        logMolWt.setEditable(false);
        add(titlePanel);
        add(namePanel);
        add(fullNamePanel);
        add(abbrPanel);
        add(molWtPanel);
        add(logMolWtPanel);
    }

    /**
     * show protein information including protein name,abbreviation, molecular weight,
     *
     * @param protein extracts the data from this protein
     */
    public void displayData(Protein protein) {
        name.setText(protein.name);
        fullName.setText(protein.fullName);
        abbr.setText(protein.abbr);
        mw = String.valueOf(protein.mw);
        molwt.setText(mw);
        logMolWt.setText(String.valueOf(Math.log(protein.mw) / Math.log(10)));
    }
}