package main.java.Electro2D;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Removes highlighted proteins from the protein list.
 *
 * @author Jill Zapoticznyj
 *         <p>
 *         Much of this code is based off of the RemoveProteinButton class
 * @author Adam Bazinet
 */
//public class Electro2D.SearchProteinButton extends Canvas implements MouseListener {
public class SearchProteinButton extends JButton implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4938288615110791181L;
	private Electro2D electro2D;
    private String pro_id = "";  //the ID of the protein

    /**
     * Constructor, performs some perfunctory tasks.
     *
     * @param e  - a reference to Electro2D.Electro2D
     * @param id - the string being searched for
     */
    public SearchProteinButton(Electro2D e, String id, String text) {
        super(text);
        electro2D = e; //give the button a reference to Electro2D.Electro2D
        pro_id = id; //set the search value to the string passed to the method
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        electro2D.showSearchPage(pro_id);
    }
}