package main.java.MassSpec;
/**
 * Copyright (C) 2013 Rochester Institute of Technology
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 */

/**
 * @author Amanda Fisher
 */

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;


/**
 * TControls the graph for the Mass Spectrometer simulation
 */
public class OutputGraphGUI extends JPanel implements MouseListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3291515557857954427L;
	private ArrayList<Ion> peakLines;
    private MassSpecMain mainPanel;
    double mostHits;
    int width;
    int height;
    int xAxisWidth;
    int yAxisHeight;
    int xAxisStartingPoint;
    int yAxisStartingPoint;
    int halfHashMarkLength;
    int numericalDifference = 200;
    int startingPoint = 0;

    /**
     * The constructor's only purpose is to add itself as its own mouse
     * listener, so the user's clicks can be registered.
     *
     * @param mP the m p
     */
    public OutputGraphGUI(MassSpecMain mP) {
        super();
        addMouseListener(this);
        mainPanel = mP;
    }


    /**
     * setPeaks is called by the MassSpec.Spectrometer class to tell MassSpec.OutputGraphGUI where
     * to draw the lines that represent ion peaks. setPeaks also uses the range
     * entered by the user to select which peaks are displayed.
     *
     * @param pL ArrayList of two element double arrays where each array's first entry is the peak's mass charge ratio, and the second entry is the peak's intensity.
     * @param mH Spectrometer gives the int number of the most hits for a specific ion occuring so intensity of each peak can be calculated.
     */
    public void setPeaks(ArrayList<Ion> pL, double mH) {
        double lowerLimit = mainPanel.getLowerLimit();
        double upperLimit = mainPanel.getUpperLimit();

        peakLines = new ArrayList<Ion>();

        for (Ion ion : pL) {
            if (ion.getMassChargeRatio() >= lowerLimit &&
                    ion.getMassChargeRatio() <= upperLimit) {
                peakLines.add(ion);
            }
        }

        if (peakLines.isEmpty()) {
            numericalDifference = (int) ((upperLimit - lowerLimit) / 15.00) + 1;
            startingPoint = (int) lowerLimit;
        } else {
            mostHits = mH;
            resizeXAxis();
        }
        repaint();
    }

    /**
     * paintComponent is overridden from the JComponent class to allow
     * MassSpec.OutputGraphGUI to paint the graph the appropriate size whenever the user
     * resizes the window. All positions in it are relative to its size.
     * (paintComponent is only called by the JVM itself; never the coder- we
     * can just call repaint)
     *
     * @param g The graphics object, supplied by the internal workings of Java.
     */
    protected void paintComponent(Graphics g) {

	//set variables to be used
        width = getWidth();
        height = getHeight();
        xAxisWidth = width - width * 3 / 20;
        yAxisHeight = height - height * 1 / 4;
        xAxisStartingPoint = width / 10;
        yAxisStartingPoint = height / 20;
        halfHashMarkLength = 5;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        //draw horizontal axis
        g.drawLine(xAxisStartingPoint, yAxisStartingPoint + yAxisHeight,
                xAxisStartingPoint + xAxisWidth, yAxisStartingPoint + yAxisHeight);
        //draw verticle axis
        g.drawLine(xAxisStartingPoint, yAxisStartingPoint,
                xAxisStartingPoint, yAxisStartingPoint + yAxisHeight);

        //draw labels
        g.drawString("I", width / 40, height / 2);
        g.drawString("m/e", width / 2, height * 54 / 55);

        //draw horizontal axis hash marks and numbers
        int yStart = yAxisStartingPoint + yAxisHeight - halfHashMarkLength;
        int yEnd = yAxisStartingPoint + yAxisHeight + halfHashMarkLength;
        for (int i = 0; i < 16; i++) {
            int xPos = xAxisStartingPoint + i * xAxisWidth / 15;
            g.drawLine(xPos, yStart, xPos, yEnd);
            String markNumber = String.valueOf(i * numericalDifference
                    + startingPoint);
            g.drawString(markNumber, xPos - 10, yEnd + 11);
        }

        //draw vertical axis hash marks and numbers
        int xStart = xAxisStartingPoint - halfHashMarkLength;
        int xEnd = xAxisStartingPoint + halfHashMarkLength;
        for (int i = 0; i < 3; i++) {
            int yPos = yAxisStartingPoint + i * yAxisHeight / 2;
            g.drawLine(xStart, yPos, xEnd, yPos);
            String markNumber = String.valueOf(100 - i * 50);
            g.drawString(markNumber, xStart - 25, yPos + 5);
        }

        //call drawPeaks so it will take care of any mass spec lines
        drawPeaks(g);
    }

    /**
     * setPeaks calls this method right before it calls repaint so that
     * MassSpec.OutputGraphGUI can resize its x-axis to start at the smallest m/e ratio
     * and end at the largest m/e ratio.
     *
     */
    public void resizeXAxis() {
        // Determine the smallest m/e ratio and the biggest m/e ratio
        // among the ions present.
        double smallestme = peakLines.get(0).getMassChargeRatio();
        double biggestme = peakLines.get(0).getMassChargeRatio();
        for (int i = 1; i < peakLines.size(); i++) {
            if (peakLines.get(i).getMassChargeRatio() < smallestme) {
                smallestme = peakLines.get(i).getMassChargeRatio();
            } else if (peakLines.get(i).getMassChargeRatio() > biggestme) {
                biggestme = peakLines.get(i).getMassChargeRatio();
            }
        }

        // Determine the numerical separation between the hash marks
        startingPoint = (int) smallestme - 1;
        startingPoint = startingPoint - (startingPoint % 5) - 10;
        int end = (int) biggestme + 1;
        int deltame = end - startingPoint;
        // Make sure the numerical separation is always at least 1.
        if (deltame > 15) {
            int remainder = deltame % 15;
            numericalDifference = (deltame + (15 - remainder)) / 15;
        } else {
            numericalDifference = 1;
        }

    }

    /**
     * drawPeaks will draw the lines that represent output from the MassSpec.Spectrometer.
     *
     * @param g Used like a paintbrush to draw the lines.
     */
    public void drawPeaks(Graphics g) {
        g.setColor(Color.BLACK);
        int xPos;
        int yPos;
        if (peakLines != null) {
            for (Ion ion : peakLines) {
                xPos = (int) (xAxisStartingPoint + xAxisWidth *
                        ((ion.getMassChargeRatio() - startingPoint)
                                / (numericalDifference * 15)));
                yPos = (int) (yAxisStartingPoint + yAxisHeight - yAxisHeight *
                        ion.getHits() / mostHits);
                ion.setXCoordinate(xPos);
                g.drawLine(xPos, yAxisStartingPoint + yAxisHeight, xPos, yPos);
            }
        }
    }

    /**
     * mouseClicked is invoked whenever the user clicks on the outputGraphGUI.
     *
     * @param e The event created by the user's click; can be used to find the
     *          x and y coordinates of the click for use with peak picking.
     */
    public void mouseClicked(MouseEvent e) {
        pickPeak(e.getX());
    }

    /**
     * peakPick is called by the mouseClicked method to identify which peak, if
     * any, the user has clicked on (or around).
     *
     * Right now also displays the peak's m/e on standard output.
     *
     * @param x The x coordinate of the click.
     */
    private void pickPeak(int x) {
        if (peakLines != null) {
            for (Ion ion : peakLines) {
                if (ion.getXCoordinate() + 2 > x && ion.getXCoordinate() - 2 < x) {
                    mainPanel.runTandem(ion);
                  //display popup from ion here
                    boolean printed = false;
                    if (!printed){
		        ion.displaySequence();

		    printed = true;
                    }
                }
            }
        }
    }

    /**
     * The following four methods are stubbed out so the MouseListener class
     * can be implemented and mouseClicked can be declared.
     *
     * @param e unused.
     */
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
