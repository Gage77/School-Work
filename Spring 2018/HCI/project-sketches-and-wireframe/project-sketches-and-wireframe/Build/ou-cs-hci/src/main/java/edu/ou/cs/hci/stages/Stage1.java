//******************************************************************************
// Copyright (C) 2016-2018 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file (for CS 4053/5053 homeworks).
// 20180123 [weaver]:	Modified for use in CS 3053 team projects.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//******************************************************************************

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Stage1
{
	//**********************************************************************
	// Public Class Members
	//*********************************************************

	//**********************************************************************
	// Private Members
	//**********************************************************************

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		// Setup the frame/main window with title and specified dimensions
		JFrame frame = new JFrame("Fridg Trackr");
		frame.setBounds(50, 50, 700, 600);
		frame.getContentPane().setLayout(new BorderLayout());

		// This panel will hold the list of items and the main item info view
		JPanel lowerPanel = new JPanel();
		// Add details to lowerPanel
		JTextArea textAreaList = new JTextArea("List of Items", 30, 10);
		JTextArea textAreaItems = new JTextArea("Item Information", 30, 50);
		JScrollPane scrollPaneList = new JScrollPane(textAreaList);
		JScrollPane scrollPaneItems = new JScrollPane(textAreaItems);
		JSplitPane splitpane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneList,
			scrollPaneItems);
		lowerPanel.add(splitpane1);

		// This panel will hold the logo and the various buttons
		JPanel upperPanel = new JPanel();
		// Add details to upperPanel
		JLabel logo = new JLabel("Fridg Trackr");
		JButton button1 = new JButton("Example Button 1");
		JButton button2 = new JButton("Example Button 2");
		JRadioButton button3 = new JRadioButton("Example Radio Button");
		JPanel innerPanel = new JPanel();		// This will hold the buttons listed above
		innerPanel.add(button1);
		innerPanel.add(button2);
		innerPanel.add(button3);
		JSplitPane splitpane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, logo,
			innerPanel);
		upperPanel.add(splitpane2);

		// Add the upperPanel and lowerPanel to the frame
		frame.getContentPane().add(upperPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(lowerPanel, BorderLayout.CENTER);

		// Make the frame visible and set close close operation
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}
}

//******************************************************************************
