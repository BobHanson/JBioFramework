/*
 * The main frame for the JBioFramework program
 * Adds Electro2D, Electrophoresis, and Ionex applications to itself
 * with a JTabbedPane.
 */

/**
 *
 * @author Amanda Fisher
 */
import javax.swing.*;
import java.awt.*;

/**
 * The type J bio framework swing version.
 */
public class JBioFrameworkSwingVersion extends JFrame {

    /**
     * The constant serialVersionUID.
     */
    public static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private Electro2D electro2D;
    private MainPanelGUI spectrometer;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JBioFrameworkSwingVersion jbfSwing = new JBioFrameworkSwingVersion();
    }

    /**
     * Instantiates a new J bio framework swing version.
     */
    public JBioFrameworkSwingVersion() {
        super();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        electro2D = new Electro2D();
        spectrometer = new MainPanelGUI();
        
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Electro2D", electro2D);
        tabbedPane.addTab("Mass Spectrometer", spectrometer);

        add(tabbedPane);

        /**
         * Use a toolit to find the screen size of the user's monitor and set
         * the window size to it.
         */

        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        this.pack();
    }    
}