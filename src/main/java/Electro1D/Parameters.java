package main.java.Electro1D;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import javajs.async.AsyncFileChooser;

/**
 * @author Bader Alharbi
 *         updated Swing version
 *         the parameters class intiate the paramerters panels to contole various simulation parametrers
 *         the parameters defulat values are deleared in the Connstants.java interface
 */

@SuppressWarnings("unused")
public class Parameters extends JPanel implements Constants {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5982938969814262007L;
	// class attributes
    Protein selectedSample1;
    Protein selectedSample2;
    Acrylamide selectedGel;
    double selectedSpeed;
    JComboBox<String> acrylamide;
    JComboBox<String> sample1;
    JComboBox<String> sample2;
    JComboBox<String> voltages;
    ButtonGroup voltage;
    ButtonGroup speed;
    int std1Ref;

    Color dyeColor;
    Protein unknown1;
    Protein unknown2;
    Protein unknown3;
    Protein unknown4;
    Protein unknown5;
    Protein unknown6;
    Protein unknown7;
    Protein unknown8;
    Protein unknown9;
    Protein unknown10;

    Electrophoresis parent;
    Protein stdProteinArray[] = new Protein[7];
	String directoryString = "." + File.separator + 
			//".." + File.separator + 
			"data";
	

    /**
     * constructor
     *
     * @param electrophoresis
     */
    Parameters(Electrophoresis electrophoresis) {

        selectedSpeed = medium;

        unknown1 = new Protein("Unknown #1", "Aconitase", "Acon", 0x14250, Color.black);
        unknown2 = new Protein("Unknown #2", "Conconavalin A", "Con A", 25556, Color.black);
        unknown3 = new Protein("Unknown #3", "Glucose Oxidase", "GO", 63058, Color.black);
        unknown4 = new Protein("Unknown #4", "Neuraminidase", "Neur", 43505, Color.black);
        unknown5 = new Protein("Unknown #5", "Phosphorylase b", "Phos b", 0x172f9, Color.black);
        unknown6 = new Protein("Unknown #6", "Pyruvate Kinase", "Pyr Kin", 56773, Color.black);
        unknown7 = new Protein("Unknown #7", "Ribonuclease A", "Ribo A", 13673, Color.black);
        unknown8 = new Protein("Unknown #8", "Chymotrypsinogen", "Chymo", 23564, Color.black);
        unknown9 = new Protein("Unknown #9", "p-Hydroxybenzoate", "Hydrox", 43939, Color.black);
        unknown10 = new Protein("Unknown #10", "Ribonuclease H", "Ribo H", 16638, Color.black);

        // string array, holds unknown proteins names
        String[] samples = {
                unknown1.name, unknown2.name, unknown3.name,
                unknown4.name, unknown5.name, unknown6.name, unknown7.name,
                unknown8.name, unknown9.name, unknown10.name
        };
        String[] gelList = {
                gel1.percentGel, gel2.percentGel, gel3.percentGel,
                gel4.percentGel
        };
        String[] voltageList = {"50V", "100V", "150V", "200V"};
        
        //array of proteins for passing to start run
        Protein[] dyes = {dye1, dye2, dye3, dye4, dye5, dye6};

        acrylamide = new JComboBox<String>(gelList);
        sample1 = new JComboBox<String>(samples);
        sample2 = new JComboBox<String>(samples);
        voltages = new JComboBox<String>(voltageList);
        UnknownListHandler1 unl1 = new UnknownListHandler1();
        UnknownListHandler2 unl2 = new UnknownListHandler2();
        GelPercentageHandler gh = new GelPercentageHandler();
        VoltageListHandler vl = new VoltageListHandler();
        acrylamide.setSelectedItem("7.5%");
        sample2.setSelectedItem("Unknown #2");
        voltages.setSelectedItem("150V");
      
        sample1.addItemListener(unl1);
        sample2.addItemListener(unl2);
        acrylamide.addItemListener(gh);
        voltages.addItemListener(vl);
        // button groups
        voltage = new ButtonGroup();
        speed = new ButtonGroup();
        // panels
        
        
        headerPanel = new JPanel();
        headerSub1 = new JPanel(new GridLayout(1, 3, 1, 1));
        headerSub2 = new JPanel();
        labelPanel1 = new JPanel();
        labelPanel2 = new JPanel();
        dropPanel = new JPanel();
        selectionPanel1 = new JPanel();
        selectionPanel2 = new JPanel();
        standardPanel = new JPanel();
        colorPanel = new JPanel();
        voltagePanel = new JPanel();
        voltageSub1Panel = new JPanel();
        voltageSub2Panel = new JPanel();
        controlPanel = new JPanel();
        color1Panel = new JPanel();
        color2Panel = new JPanel();
        color3Panel = new JPanel();
        color4Panel = new JPanel();
        color5Panel = new JPanel();
        color6Panel = new JPanel();
        color7Panel = new JPanel();
        voltacrPanel = new JPanel();
        parent = electrophoresis;

        stdProteinArray[std1Ref] = new Protein("Standard #1",
                "beta-Galactosidase", "b-gal", 0x1c58b, Color.blue);
        stdProteinArray[std2Ref] = new Protein("Standard #2", "Ovalbumin",
                "oval", 42734, Color.yellow);
        stdProteinArray[std3Ref] = new Protein("Standard #3",
                "Carbonic Anhydrase", "carb anh", 29011, Color.gray);
        stdProteinArray[std4Ref] = new Protein("Standard #4",
                "Triose Phosphate Isomerase", "TIM", 26527, Color.green);
        stdProteinArray[std5Ref] = new Protein("Standard #5", "Myoglobin",
                "Myo", 17183, Color.magenta);
        stdProteinArray[std6Ref] = new Protein("Standard #6", "Lysozyme",
                "Lyso", 14296, Color.white);
        stdProteinArray[std7Ref] = new Protein("Standard #7",
                "MassSpec.Trypsin Inhibitor", "BPTI", 6500, Color.red);
        // create borders subtitles
        Border border = BorderFactory.createTitledBorder("ELECTROPHORESIS PARAMETERS");
        Border border1 = BorderFactory.createTitledBorder("Animation Speed");

        // helper methods
        setPanelsColors();

        setLayout(new GridLayout(4, 1, 2, 2));
        headerPanel.setLayout(new GridLayout(1, 1, 5, 5));
        headerPanel.setBorder(border);

        JRadioButton speed1button = new JRadioButton(slow, false);
        speed1button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationSpeed(slow);
            }
        });
        JRadioButton speed2button = new JRadioButton(moderate, true);
        speed2button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationSpeed(moderate);
            }
        });
        JRadioButton speed3button = new JRadioButton(fast, false);
        speed3button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setAnimationSpeed(fast);

            }
        });
        // add to button group to allow one selection only
        speed.add(speed3button);
        speed.add(speed2button);
        speed.add(speed1button);

        headerSub2.add(speed1button);
        headerSub2.add(speed2button);
        headerSub2.add(speed3button);
        headerSub2.setBorder(border1);
        headerSub2.setToolTipText("Affects how quickly the animations run");
        // headerPanel.add(headerSub1);
        headerPanel.add(headerSub2);

        dropPanel.setLayout(new GridLayout(3, 1));
        labelPanel1.setLayout(new GridLayout(1, 2));

        JLabel unknownLabel1 = new JLabel("Well 2");//Unkown Proteins
        unknownLabel1.setToolTipText("Set of unknown samples for well 2");
        labelPanel1.add(unknownLabel1);
        JLabel unknownLabel2 = new JLabel("Well 3");//Unkown Proteins
        unknownLabel2.setToolTipText("Set of unknown samples for well 3");
        labelPanel1.add(unknownLabel2);
        JLabel wellLabel4 = new JLabel("Well 4");
        JLabel wellLabel5 = new JLabel("Well 5");
        JLabel wellLabel6 = new JLabel("Well 6");
        labelPanel1.add(wellLabel4);
        labelPanel1.add(wellLabel5);
        labelPanel1.add(wellLabel6);
        //JLabel percentAcrylamideLabel = new JLabel("% Acrylamide");
        //percentAcrylamideLabel.setToolTipText("Affects the density of the gel");
        //labelPanel1.add(percentAcrylamideLabel);

        JLabel standardsLabel = new JLabel("Standards");
        standardsLabel.setToolTipText("Set of known values for comparison");
        labelPanel2.add(standardsLabel);

        selectionPanel1.setLayout(new GridLayout(1, 2));
        selectionPanel2.setLayout(new GridLayout(1, 2));

        voltagePanel.setLayout(new BorderLayout());
        voltagePanel.setToolTipText("Affects how quickly proteins travel across the gel");
        voltageSub2Panel.setLayout(new GridLayout(1, 4));
        
        JRadioButton volt1 = new JRadioButton("50V");
        volt1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSpeed = low;
                setSpeed(selectedSpeed);
            }
        });
        JRadioButton volt2 = new JRadioButton("100V");
        volt2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectedSpeed = medium;
                setSpeed(selectedSpeed);
            }
        });
        JRadioButton volt3 = new JRadioButton("150V", true);
        volt3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSpeed = high;
                setSpeed(selectedSpeed);
            }
        });
        JRadioButton volt4 = new JRadioButton("200V");
        volt4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSpeed = highX2;
                setSpeed(selectedSpeed);

            }
        });
        
        voltage.add(volt1);
        voltage.add(volt2);
        voltage.add(volt3);
        voltage.add(volt4);
        Border border3 = BorderFactory.createTitledBorder("Voltage");
        voltageSub2Panel.setBorder(border3);

        voltageSub2Panel.add(volt1);
        voltageSub2Panel.add(volt2);
        voltageSub2Panel.add(volt3);
        voltageSub2Panel.add(volt4);

        voltagePanel.add(voltageSub1Panel, BorderLayout.NORTH);
        voltagePanel.add(voltageSub2Panel, BorderLayout.CENTER);
        
        voltacrPanel.setLayout(new GridLayout(2, 2));
        voltacrPanel.setBackground(Color.lightGray);
        //add new section for volatages and acrlyamide percentages
        //JPanel voltacrLabel = new JPanel();
        JLabel voltLabel = new JLabel("Voltage");
        JLabel acrLabel = new JLabel("Acrylamide %");
        //voltacrLabel.add(voltLabel);
        //voltacrLabel.add(acrLabel);
        voltacrPanel.add(voltLabel);
        voltacrPanel.add(acrLabel);
        
        

        standardPanel.setLayout(new GridLayout(7, 1, 0, 1));

        // check boxes

        standard1 = new JCheckBox(stdProteinArray[0].abbr);
        standard2 = new JCheckBox(stdProteinArray[1].abbr);
        standard3 = new JCheckBox(stdProteinArray[2].abbr);
        standard4 = new JCheckBox(stdProteinArray[3].abbr);
        standard5 = new JCheckBox(stdProteinArray[4].abbr);
        standard6 = new JCheckBox(stdProteinArray[5].abbr);
        standard7 = new JCheckBox(stdProteinArray[6].abbr);

        standardPanel.add(standard1);
        standardPanel.add(standard2);
        standardPanel.add(standard3);
        standardPanel.add(standard4);
        standardPanel.add(standard5);
        standardPanel.add(standard6);
        standardPanel.add(standard7);
        // StandardsListListener
        StandardsListListener sll = new StandardsListListener();
        standard1.addItemListener(sll);
        standard2.addItemListener(sll);
        standard3.addItemListener(sll);
        standard4.addItemListener(sll);
        standard5.addItemListener(sll);
        standard6.addItemListener(sll);
        standard7.addItemListener(sll);
        // end check boxes
        helperMethod1();

        JButton addStandard = new JButton("Add Standard");
        addStandard.setToolTipText("Pipette selected standards into well 1");
        addStandard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parent.addStandard();

            }
        });
        JButton addSample = new JButton("Add Sample");
        addSample.setToolTipText("Pipette selected unknown into well 2");
        addSample.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                parent.addSample();

            }
            
        });
        
        JButton addSample2 = new JButton("Samp2");
        addSample2.setToolTipText("Pipette selected unknown into well 3");
        addSample2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                parent.addSample2();

            }
        });
        JButton startButton = new JButton("Start Run");//Start run
        startButton.setToolTipText("Powers on the battery to begin run");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // when click start
                parent.startRun(stdProteinArray, selectedSample1, selectedSample2, dyes);

            }
        });
        JButton stopButton = new JButton("Stop Run");//Stop Run
        stopButton.setToolTipText("Ends current to stop the run");
        stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // when click stop
                parent.stopRun();
                parent.setPlotData(stdProteinArray, selectedSample1, dye1);
            }
        });
        
        //The file selectors for the other 4 wells
        JButton fileSelector1 = new JButton("#3");
        fileSelector1.setToolTipText("Select a file to be put in well 3");
        fileSelector1.addActionListener(new ActionListener() {
            
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	loadFile("Well 3");
            }
        });
        JButton fileSelector2 = new JButton("#4");
        fileSelector2.setToolTipText("Select a file to be put in well 4");
        fileSelector2.addActionListener(new ActionListener() {
            
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	loadFile("Well 4");
            }
        });
        JButton fileSelector3 = new JButton("#5");
        fileSelector3.setToolTipText("Select a file to be put in well 5");
        fileSelector3.addActionListener(new ActionListener() {
            
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	loadFile("Well 5");
            }
        });
        JButton fileSelector4 = new JButton("#6");
        fileSelector4.setToolTipText("Select a file to be put in well 6");
        fileSelector4.addActionListener(new ActionListener() {
            
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	loadFile("Well 6");
            }
        });

        controlPanel.add(addStandard);
        controlPanel.add(addSample);
        //controlPanel.add(addSample2);
        //controlPanel.add(addSample3);
        //controlPanel.add(addSample4);
        //controlPanel.add(addSample5);
        //controlPanel.add(fileSelector);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        //higher panel for choosing wells
        selectionPanel1.add(fileSelector1);
        selectionPanel1.add(fileSelector2);
        selectionPanel1.add(fileSelector3);
        selectionPanel1.add(fileSelector4);

        //add(headerPanel);
        add(dropPanel);
        add(selectionPanel2);
        //add(voltagePanel);
        add(voltacrPanel);
        add(controlPanel);

        selectedGel = gel1;
        setSpeed(selectedSpeed);
        selectedSample1 = unknown1;
        selectedSample2 = unknown2;
    }

    private void helperMethod1() {
        color1Panel.setBackground(stdProteinArray[std1Ref].color);
        color2Panel.setBackground(stdProteinArray[std2Ref].color);
        color3Panel.setBackground(stdProteinArray[std3Ref].color);
        color4Panel.setBackground(stdProteinArray[std4Ref].color);
        color5Panel.setBackground(stdProteinArray[std5Ref].color);
        color6Panel.setBackground(stdProteinArray[std6Ref].color);
        color7Panel.setBackground(stdProteinArray[std7Ref].color);
        colorPanel.setLayout(new GridLayout(7, 1, 1, 3));
        colorPanel.add(color1Panel);
        colorPanel.add(color2Panel);
        colorPanel.add(color3Panel);
        colorPanel.add(color4Panel);
        colorPanel.add(color5Panel);
        colorPanel.add(color6Panel);
        colorPanel.add(color7Panel);

        selectionPanel1.add(sample1);
        //selectionPanel1.add(sample2);
        //selectionPanel1.add(acrylamide);
        voltacrPanel.add(voltages);
        voltacrPanel.add(acrylamide);

        selectionPanel2.add(standardPanel);
        selectionPanel2.add(colorPanel);
        dropPanel.add(labelPanel1);
        dropPanel.add(selectionPanel1);
        dropPanel.add(labelPanel2);
        controlPanel.setLayout(new GridLayout(2, 2, 10, 10));//should be 3, 2, 10, 10

        // create the control panel buttons & use anonymous inner handling
    }

    /**
     * set the animation speed, take string parameter
     *
     * @param s the speed of the animation
     */
    protected void setAnimationSpeed(String s) {
        parent.setAnimationSpeed(s);
    }

    /**
     * set the Acrylamide effect
     */
    protected void setAcrylamideEffect() {
        int i = 0;
        do
            if (selectedGel.getConc() > 12D) {
                if (stdProteinArray[i].mw > 26000)
                    stdProteinArray[i].SetDecider(selectedGel.suppressor);
                else
                    stdProteinArray[i].ResetDecider();
            } else if (selectedGel.getConc() > 10D) {
                if (stdProteinArray[i].mw > 29000)
                    stdProteinArray[i].SetDecider(selectedGel.suppressor);
                else
                    stdProteinArray[i].ResetDecider();
            } else if (selectedGel.getConc() > 7.5D) {
                if (stdProteinArray[i].mw > 40000)
                    stdProteinArray[i].SetDecider(selectedGel.suppressor);
                else
                    stdProteinArray[i].ResetDecider();
            } else {
                stdProteinArray[i].ResetDecider();
            }
        while (++i < 7);
        if (selectedGel.getConc() > 12D)
            if (selectedSample1.mw > 26000) {
                selectedSample1.SetDecider(selectedGel.suppressor);
                return;
            } else {
                selectedSample1.ResetDecider();
                return;
            }
        if (selectedGel.getConc() > 10D)
            if (selectedSample1.mw > 29000) {
                selectedSample1.SetDecider(selectedGel.suppressor);
                return;
            } else {
                selectedSample1.ResetDecider();
                return;
            }
        if (selectedGel.getConc() > 7.5D) {
            if (selectedSample1.mw > 40000) {
                selectedSample1.SetDecider(selectedGel.suppressor);
                return;
            } else {
                selectedSample1.ResetDecider();
                return;
            }
        } else {
            selectedSample1.ResetDecider();
            return;
        }
    }

    /**
     * set the proteins bands speed
     * take double value
     *
     * @param d the speed of the protein bands
     */
    protected void setSpeed(double d) {
        dye1.speed = d;
        dye2.speed = d;
        dye3.speed = d;
        dye4.speed = d;
        dye5.speed = d;
        dye6.speed = d;
        stdProteinArray[std1Ref].speed = 0.048245000000000003D * d;
        stdProteinArray[std2Ref].speed = 0.35087200000000002D * d;
        stdProteinArray[std3Ref].speed = 0.46814299999999998D * d;
        stdProteinArray[std4Ref].speed = 0.49524400000000002D * d;
        stdProteinArray[std5Ref].speed = 0.62672099999999997D * d;
        stdProteinArray[std6Ref].speed = 0.68241399999999997D * d;
        stdProteinArray[std7Ref].speed = 0.92105300000000001D * d;
        unknown1.speed = 0.15166299999999999D * d;
        unknown2.speed = 0.50653499999999996D * d;
        unknown3.speed = 0.233075D * d;
        unknown4.speed = 0.34545900000000002D * d;
        unknown5.speed = 0.10909099999999999D * d;
        unknown6.speed = 0.26486500000000002D * d;
        unknown7.speed = 0.69590399999999997D * d;
        unknown8.speed = 0.53110599999999997D * d;
        unknown9.speed = 0.34245300000000001D * d;
        unknown10.speed = 0.63648000000000005D * d;
        parent.updateSpeed(d, selectedGel);
    }

    /**
     * set the parameter panel colors
     */
    private void setPanelsColors() {

        headerPanel.setBackground(Color.lightGray);
        selectionPanel1.setBackground(Color.lightGray);
        selectionPanel2.setBackground(Color.lightGray);
        standardPanel.setBackground(Color.lightGray);
        voltagePanel.setBackground(Color.lightGray);
        voltageSub1Panel.setBackground(Color.lightGray);
        voltageSub2Panel.setBackground(Color.lightGray);
        controlPanel.setBackground(Color.lightGray);
        labelPanel1.setBackground(Color.lightGray);
        labelPanel2.setBackground(Color.lightGray);
        dropPanel.setBackground(Color.lightGray);

    }

    /**
     * set default values for acrylamide gel properties
     */
    public void setDefaults() {
        parent.setAcrylamide(gel1);
        selectedGel = gel1;
        setAcrylamideEffect();

    }
    
    public void loadFile(String wellNum) {
    	setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	FileInput fi = new FileInput();
    	//JFrame fileSelctorFrame = this;
    	
    	File dir = new File(directoryString);
		System.out.println(dir.getAbsolutePath());
		if (!dir.isDirectory())
			dir = new File("." + File.separator + ".." + File.separator + "data");
		AsyncFileChooser chooser = new AsyncFileChooser(dir);
		if(parent.isReady()) {
			chooser.showOpenDialog(this, () -> {
				proteins = fi.loadFile(chooser.getSelectedFile(), wellNum);
				// set the cursor image back to normal
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				// close the frame
				//dispose();
				}, () -> {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				//dispose();				
			});
			parent.addSampleFromFile(proteins, wellNum);
		}
    }

    // GUI attributes
    JPanel headerPanel;
    JPanel headerSub1;
    JPanel headerSub2;
    JPanel labelPanel1;
    JPanel labelPanel2;
    JPanel dropPanel;
    JPanel selectionPanel1;
    JPanel selectionPanel2;
    JPanel standardPanel;
    JPanel colorPanel;
    JPanel voltacrPanel;
    JPanel voltagePanel;
    JPanel voltageSub1Panel;
    JPanel voltageSub2Panel;
    JPanel controlPanel;
    JPanel color1Panel;
    JPanel color2Panel;
    JPanel color3Panel;
    JPanel color4Panel;
    JPanel color5Panel;
    JPanel color6Panel;
    JPanel color7Panel;
    JCheckBox standard1;
    JCheckBox standard2;
    JCheckBox standard3;
    JCheckBox standard4;
    JCheckBox standard5;
    JCheckBox standard6;
    JCheckBox standard7;
    Vector<Protein> proteins;

    /**
     * StandardsListListener, inner class  to handle events invoked by GUI components in parameters panel
     */
    class StandardsListListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            Object source = e.getItemSelectable();

            if (source == standard1) {
                stdProteinArray[std1Ref].selected = standard1.isSelected();
                if (stdProteinArray[std1Ref].selected)
                    parent.displayProtein(stdProteinArray[std1Ref]);
            }
            if (source == standard2) {
                stdProteinArray[std2Ref].selected = standard2.isSelected();
                if (stdProteinArray[std2Ref].selected)
                    parent.displayProtein(stdProteinArray[std2Ref]);
            }
            if (source == standard3) {
                stdProteinArray[std3Ref].selected = standard3.isSelected();
                if (stdProteinArray[std3Ref].selected)
                    parent.displayProtein(stdProteinArray[std3Ref]);
            }
            if (source == standard4) {
                stdProteinArray[std4Ref].selected = standard4.isSelected();
                if (stdProteinArray[std4Ref].selected)
                    parent.displayProtein(stdProteinArray[std4Ref]);
            }
            if (source == standard5) {
                stdProteinArray[std5Ref].selected = standard5.isSelected();
                if (stdProteinArray[std5Ref].selected)
                    parent.displayProtein(stdProteinArray[std5Ref]);
            }
            if (source == standard6) {
                stdProteinArray[std6Ref].selected = standard6.isSelected();
                if (stdProteinArray[std6Ref].selected)
                    parent.displayProtein(stdProteinArray[std6Ref]);
            }
            if (source == standard7) {
                stdProteinArray[std7Ref].selected = standard7.isSelected();
                if (stdProteinArray[std7Ref].selected)
                    parent.displayProtein(stdProteinArray[std7Ref]);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED) {

            }

        }

    }// end class StandardsListListener

    /**
     * UnknownListHandler, inner class  to handle events invoked by GUI components in parameters panel
     */
    class UnknownListHandler1 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ev) {
            // Object item = ev.getItem();
            @SuppressWarnings("unchecked")
			JComboBox<String> item = (JComboBox<String>) ev.getSource();
            
            switch ((String) item.getSelectedItem()) {
            	case "Unknown #1":
            		selectedSample1 = unknown1;
                    parent.displayProtein(unknown1);
            		break;
            	case "Unknown #2":
            		selectedSample1 = unknown2;
                    parent.displayProtein(unknown2);
            		break;
            	case "Unknown #3":
            		selectedSample1 = unknown3;
                    parent.displayProtein(unknown3);
            		break;
            	case "Unknown #4":
            		selectedSample1 = unknown4;
                    parent.displayProtein(unknown4);
            		break;
            	case "Unknown #5":
            		selectedSample1 = unknown5;
                    parent.displayProtein(unknown5);
            		break;
            	case "Unknown #6":
            		selectedSample1 = unknown6;
                    parent.displayProtein(unknown6);
            		break;
            	case "Unknown #7":
            		selectedSample1 = unknown7;
                    parent.displayProtein(unknown7);
            		break;
            	case "Unknown #8":
            		selectedSample1 = unknown8;
                    parent.displayProtein(unknown8);
            		break;
            	case "Unknown #9":
            		selectedSample1 = unknown9;
                    parent.displayProtein(unknown9);
            		break;
            	case "Unknown #10":
            		selectedSample1 = unknown10;
                    parent.displayProtein(unknown10);
            		break;
            }
        }

    }// end inner class UnknownListHandler
    
    class UnknownListHandler2 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ev) {
            // Object item = ev.getItem();
            @SuppressWarnings("unchecked")
			JComboBox<String> item = (JComboBox<String>) ev.getSource();
            
            switch ((String) item.getSelectedItem()) {
            	case "Unknown #1":
            		selectedSample2 = unknown1;
                    //parent.displayProtein(unknown1);
            		break;
            	case "Unknown #2":
            		selectedSample2 = unknown2;
                    //parent.displayProtein(unknown2);
            		break;
            	case "Unknown #3":
            		selectedSample2 = unknown3;
                    //parent.displayProtein(unknown3);
            		break;
            	case "Unknown #4":
            		selectedSample2 = unknown4;
                    //parent.displayProtein(unknown4);
            		break;
            	case "Unknown #5":
            		selectedSample2 = unknown5;
                    //parent.displayProtein(unknown5);
            		break;
            	case "Unknown #6":
            		selectedSample2 = unknown6;
                    //parent.displayProtein(unknown6);
            		break;
            	case "Unknown #7":
            		selectedSample2 = unknown7;
                    //parent.displayProtein(unknown7);
            		break;
            	case "Unknown #8":
            		selectedSample2 = unknown8;
                    //parent.displayProtein(unknown8);
            		break;
            	case "Unknown #9":
            		selectedSample2 = unknown9;
                    //parent.displayProtein(unknown9);
            		break;
            	case "Unknown #10":
            		selectedSample2 = unknown10;
                    //parent.displayProtein(unknown10);
            		break;
            }
        }

    }// end inner class UnknownListHandler
    
    /**
     * VoltageListHandler, inner class  to handle events invoked by GUI components in parameters panel
     */
    class VoltageListHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ev) {
            // Object item = ev.getItem();
            @SuppressWarnings("unchecked")
			JComboBox<String> item = (JComboBox<String>) ev.getSource();
            
            switch ((String) item.getSelectedItem()) {
            	case "50V":
            		selectedSpeed = low;
            		setSpeed(selectedSpeed);
            		break;
            	case "100V":
            		selectedSpeed = medium;
            		setSpeed(selectedSpeed);
            		break;
            	case "150V":
            		selectedSpeed = high;
            		setSpeed(selectedSpeed);
            		break;
            	case "200V":
            		selectedSpeed = highX2;
            		setSpeed(selectedSpeed);
            		break;
            	
            }
        }

    }// end inner class UnknownListHandler

    /**
     * gelPercentageHandler, inner class  to handle events invoked by GUI components in parameters panel
     */
    class GelPercentageHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            @SuppressWarnings("unchecked")
			JComboBox<String> item = (JComboBox<String>) e.getSource();
            String selectedName = (String) item.getSelectedItem();

            if (gel1.percentGel.equals(selectedName)) {
                selectedGel = gel1;
                parent.setAcrylamide(gel1);
                selectedGel.setSuppressor(gel1.getConc());
                setAcrylamideEffect();
            } else if (gel2.percentGel.equals(selectedName)) {
                selectedGel = gel2;
                parent.setAcrylamide(gel2);
                selectedGel.setSuppressor(gel2.getConc());
                setAcrylamideEffect();
            } else if (gel3.percentGel.equals(selectedName)) {
                selectedGel = gel3;
                parent.setAcrylamide(gel3);
                selectedGel.setSuppressor(gel3.getConc());
                setAcrylamideEffect();
            } else if (gel4.percentGel.equals(selectedName)) {
                selectedGel = gel4;
                parent.setAcrylamide(gel4);
                selectedGel.setSuppressor(gel4.getConc());
                setAcrylamideEffect();

            }
        }

    }// end inner class gelPercentageHandler

}