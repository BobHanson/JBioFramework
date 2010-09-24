/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amanda Fisher
 */
import javax.swing.*;
import java.awt.event.*;

public class ColorKeyButtonSwingVersion extends JButton implements ActionListener {
    ColorFrame key;

    public ColorKeyButtonSwingVersion () {

        super("Color Key");
        addActionListener(this);
        key = new ColorFrame();
}

    public void actionPerformed (ActionEvent e) {

        key.showKey();

    }
}
