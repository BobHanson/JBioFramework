package main.java.Electro2D;

import javajs.async.SwingJSUtils;
import javajs.async.SwingJSUtils.StateHelper;
import javajs.async.SwingJSUtils.StateMachine;

/*
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
 * Electro2D.IEFThread is the thread controlling the IEF phase of the 2DGE
 * animation.
 *
 * @author Jill Zapoticznyj
 * @author Adam Bazinet
 */


public class IEFThread extends Thread {

    private boolean go = false; //determines whether or not the user has
    //selected the IEF animation
    private GelCanvas gel;      //a reference to Electro2D.GelCanvas
    private Electro2D electro2D;

    /**
     * Constructor
     *
     * @param g a reference to the Electro2D.GelCanvas
     */

    public IEFThread(GelCanvas g, Electro2D e) {
        //assigns gel the value that was passed as a parameter to the method
        gel = g;
        electro2D = e;
    }

    /**
     * This method is called when the play button is pressed for
     * the IEF animation.  Allows the thread to draw the IEFProteins
     * to the Electro2D.GelCanvas' image
     */

    public void setIEF() {
        go = true;
    }

    /**
     * This method is called after the IEFProteins were drawn to the
     * image in Electro2D.GelCanvas.
     */

    public void resetIEF() {
        go = false;
    }

    /**
     * The run method for the thread that draws the IEFProteins
     * to the Electro2D.GelCanvas image.  If the proteins have not yet been
     * drawn, it calls the drawIEF method in Electro2D.GelCanvas to draw
     * the IEFProteins.
     */

	private StateHelper stateHelper;
	protected int delay = /** @j2sNative 1 ? 50 : */250 ; // was 250

	public void run() {

        // if the user has selected IEF animation
        if (go == true) {

            //get the current and final widths of the IEFProteins
            // BH not used double width = IEFProtein.returnTempWidth();
            double finalWidth = IEFProtein.returnWidth();
            if (IEFProtein.returnTempWidth() >= finalWidth) {
                gel.drawIEF();
            }
            
//            //while the current width and the values of the background colors
//            // are less than their final values, call the animateIEF method
//            while (IEFProtein.returnTempWidth() <= finalWidth && GelCanvas.getBlue() >= 0 &&
//                    GelCanvas.getGreen() >= 0 && GelCanvas.getRed() >=
//                    0) {
//                gel.animateIEF();
//                try {
//                    sleep((long) 250);
//                } catch (Exception e) {
//                    System.err.println("the error was " + e.getMessage());
//                }
//            }
//            //display the pH markers along the gel
//            gel.setreLine();
//            gel.repaint();
//            //change the value selected in the animationChooser
//            electro2D.setSDS();

            stateHelper = new SwingJSUtils.StateHelper(new StateMachine() {

            	@Override
            	public boolean stateLoop() {
            		if (stateHelper.isAlive()) {
            			// while the current width and the values of the background colors
            			// are less than their final values, call the animateIEF method
            			if (IEFProtein.returnTempWidth() <= finalWidth && GelCanvas.getBlue() >= 0 && GelCanvas.getGreen() >= 0
            					&& GelCanvas.getRed() >= 0) {
            				gel.animateIEF();
            				stateHelper.sleep(delay );
            			} else {
            				// display the pH markers along the gel
            				gel.setRedrawPHLines(true);
            				gel.repaint();
            				// change the value selected in the animationChooser
            				electro2D.setSDS();
            			}
            		}
            		return false;
            	}
            	
            });
            stateHelper.next(0);
        }
    }

} //Electro2D.IEFThread
