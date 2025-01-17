package main.java.Electro2D;
/**
 * This class constructs a window to display two different protein lists and
 * allows the manipulation of those lists to alter the protein sequences run on
 * the Electro2D.GelCanvas.
 *
 * @author Amanda Fisher
 */

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.util.Vector;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

/**
 * The type Protein list frame.
 */
@SuppressWarnings("serial")
public class ProteinListFrame extends JFrame {

    /**
     * The Electro 2 d.
     */
    Electro2D electro2D;
    /**
     * The Sequence one list.
     */
    JList<String> sequenceOneList;
    /**
     * The Sequence two list.
     */
    JList<String> sequenceTwoList;
    /**
     * The Sequence titles one.
     */
    Vector<String> sequenceTitlesOne;
    /**
     * The Sequence titles two.
     */
    Vector<String> sequenceTitlesTwo;
    /**
     * The Positions one.
     */
    Vector<Integer> positionsOne;
    /**
     * The Positions two.
     */
    Vector<Integer> positionsTwo;
    /**
     * The Copy sequence one.
     */
    Vector<String> copySequenceOne;
    /**
     * The Copy sequence two.
     */
    Vector<String> copySequenceTwo;

    /**
     * Constructor for the window; sets up the instance variables and builds
     * the GUI.
     *
     * @param param     The title of the window.
     * @param electro2D Reference to the electro2D where the protein lists are.
     */
    @SuppressWarnings("unchecked")
	public ProteinListFrame(String param, Electro2D electro2D) {
        super(param);
        this.electro2D = electro2D;
        setLayout(new GridBagLayout());
        JLabel sequenceOneLabel = new JLabel("Sequence One");
        JLabel sequenceTwoLabel = new JLabel("Sequence Two");
        sequenceOneList = new JList<>();
        sequenceTwoList = new JList<>();
        sequenceTitlesOne = new Vector<>();
        sequenceTitlesTwo = new Vector<>();
        copySequenceOne = (Vector<String>) sequenceTitlesOne.clone();
        copySequenceTwo = (Vector<String>) sequenceTitlesTwo.clone();
        positionsOne = new Vector<Integer>();
        positionsTwo = new Vector<Integer>();
        JScrollPane sequenceOneScroll = new JScrollPane(sequenceOneList);
        JScrollPane sequenceTwoScroll = new JScrollPane(sequenceTwoList);
        JLabel removeLabel = new JLabel("Select proteins to remove: ");
        JButton sequenceOneButton = new JButton("Sequence One");
        JButton sequenceTwoButton = new JButton("Sequence Two");
        JButton commonButton = new JButton("Common");
        JButton selectedButton = new JButton("Selected");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = (new Insets(5, 5, 5, 5));
        c.gridx = 0;
        c.gridy = 0;
        add(sequenceOneLabel, c);

        c.gridx = 1;
        add(sequenceTwoLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(sequenceOneScroll, c);

        c.gridx = 1;
        add(sequenceTwoScroll, c);

        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(removeLabel, c);

        c.gridy = 3;
        c.gridwidth = 1;
        add(sequenceOneButton, c);

        c.gridx = 1;
        add(sequenceTwoButton, c);

        c.gridx = 0;
        c.gridy = 4;
        add(commonButton, c);

        c.gridx = 1;
        add(selectedButton, c);

        sequenceOneButton.addActionListener(new OneListener());
        sequenceTwoButton.addActionListener(new TwoListener());
        commonButton.addActionListener(new CommonListener());
        selectedButton.addActionListener(new SelectedListener());

        pack();
        setVisible(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    /**
     * This method is called by electro2D's refreshProteinList and
     * refreshProteinList2 method when a new protein sequence is loaded.
     * updateSequences copies the protein sequence titles into its own lists
     * for display.
     *
     * @param pL1 the p l 1
     * @param pL2 the p l 2
     */
    @SuppressWarnings("unchecked")
	public void updateSequences(Vector<String> pL1, Vector<String> pL2) {
        sequenceOneList.setListData(pL1);
        sequenceTwoList.setListData(pL2);
        sequenceTitlesOne = new Vector<>(pL1);
        sequenceTitlesTwo = new Vector<>(pL2);
        copySequenceOne = (Vector<String>) sequenceTitlesOne.clone();
        copySequenceTwo = (Vector<String>) sequenceTitlesTwo.clone();
        positionsOne = new Vector<Integer>();
        positionsTwo = new Vector<Integer>();
    }

    /**
     * This method is called to collect the positions of the elements still within
     * the manipulated sequence into vectors for use with synchronizing Electro2D.Electro2D
     * with the user's manipulations.
     */
    public void updatePositions() {
        positionsOne = new Vector<Integer>();
        positionsTwo = new Vector<Integer>();
        for (int x = 0; x < copySequenceOne.size(); x++) {
            if (sequenceTitlesOne.contains(copySequenceOne.get(x))) {
                positionsOne.add(x);
            }
        }
        for (int x = 0; x < copySequenceTwo.size(); x++) {
            if (sequenceTitlesTwo.contains(copySequenceTwo.get(x))) {
                positionsTwo.add(x);
            }
        }
        if (positionsOne.size() == 0) {
            positionsOne.add(-1);
        }
        if (positionsTwo.size() == 0) {
            positionsTwo.add(-1);
        }

        if (positionsOne.size() > 0) {
            electro2D.setSequencesReady(true);
        }

        if (positionsTwo.size() > 0) {
            electro2D.setSequencesReady(true);
        }

        if (positionsOne.get(0) < 0 && positionsTwo.get(0) < 0) {
            electro2D.setSequencesReady(false);
        }
    }

    /**
     * Listener for the Sequence One button. Removes all proteins in sequence
     * one and all sequence one proteins in sequence two.
     */
    private class OneListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int x = 0; x < sequenceTitlesOne.size(); x++) {
                for (int y = 0; y < sequenceTitlesTwo.size(); y++) {
                    if (sequenceTitlesOne.get(x).equals(sequenceTitlesTwo.get(y))) {
                        sequenceTitlesTwo.remove(y);
                    }
                }
            }
            sequenceTitlesOne.clear();
            sequenceOneList.setListData(sequenceTitlesOne);
            sequenceTwoList.setListData(sequenceTitlesTwo);
            sequenceOneList.validate();
            sequenceTwoList.validate();
            updatePositions();
            //          positionsOne = new Vector<Integer>();
        }
    }

    /**
     * Listener for the Sequence Two button. Removes all proteins in sequence
     * two and all sequence two proteins in sequence one.
     */
    private class TwoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int x = 0; x < sequenceTitlesTwo.size(); x++) {
                for (int y = 0; y < sequenceTitlesOne.size(); y++) {
                    if (sequenceTitlesTwo.get(x).equals(sequenceTitlesOne.get(y))) {
                        sequenceTitlesOne.remove(y);
                    }
                }
            }
            sequenceTitlesTwo.clear();
            sequenceOneList.setListData(sequenceTitlesOne);
            sequenceTwoList.setListData(sequenceTitlesTwo);
            sequenceOneList.validate();
            sequenceTwoList.validate();
            updatePositions();
//            positionsTwo = new Vector<Integer>();
        }
    }

    /**
     * Listener for the Common button. Removes all proteins in sequence one and
     * sequence two that are shared between sequence one and sequence two.
     */
    private class CommonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked")
			Vector<String> copySequenceTwo = (Vector<String>) sequenceTitlesTwo.clone();
            sequenceTitlesTwo.removeAll(sequenceTitlesOne);
            sequenceTitlesOne.removeAll(copySequenceTwo);

            sequenceOneList.setListData(sequenceTitlesOne);
            sequenceTwoList.setListData(sequenceTitlesTwo);
            sequenceOneList.validate();
            sequenceTwoList.validate();
            updatePositions();
        }
    }

    /**
     * Listener for the Selected button. Removes all proteins that are selected.
     */
    private class SelectedListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int[] oneIndexes = sequenceOneList.getSelectedIndices();
            int[] twoIndexes = sequenceTwoList.getSelectedIndices();
            Vector<String> oneProteins = new Vector<>();
            Vector<String> twoProteins = new Vector<>();
            for (int x = 0; x < oneIndexes.length; x++) {
                oneProteins.add(sequenceTitlesOne.get(oneIndexes[x]));
            }
            for (int y = 0; y < twoIndexes.length; y++) {
                twoProteins.add(sequenceTitlesTwo.get(twoIndexes[y]));
            }
            sequenceTitlesOne.removeAll(oneProteins);
            sequenceTitlesTwo.removeAll(twoProteins);

            sequenceOneList.setListData(sequenceTitlesOne);
            sequenceTwoList.setListData(sequenceTitlesTwo);
            sequenceOneList.validate();
            sequenceTwoList.validate();
            updatePositions();
        }
    }

    /**
     * The following two methods are accessors for the position vectors.
     * Electro2D will call them in its get methods for the vectors used in
     * gel filtration in order to carry over the manipulations done by the user.
     *
     * @return the positions of each element still in the sequence.
     */
    public Vector<Integer> getPositionsOne() {
        return positionsOne;
    }

    /**
     * Gets positions two.
     *
     * @return the positions two
     */
    public Vector<Integer> getPositionsTwo() {
        return positionsTwo;
    }

}
