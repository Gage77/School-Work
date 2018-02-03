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
	//**********************************************************************

	private static final Font	FONT =
		new Font(Font.SERIF, Font.ITALIC, 36);
	private static final Color	FILL_COLOR = Color.LIGHT_GRAY;
	private static final Color	EDGE_COLOR = Color.BLACK;

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private static String		message1;
	private static String		message2;
	private static String		message3;
	private static String		message4;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		// List of labels for panel wireframes
		message1 = "Logo";	// Will go in top left logo Panel
		message2 = "Buttons Panel"; // Will go in the top right buttons Panel
		message3 = "List"; // Will go in the left list Panel
		message4 = "Item Info Panel"; // Will go in the main Panel

		JFrame			frame = new JFrame("Stage1 - Fridg Trackr");
		JPanel			panel = new HelloPanel(message1);

		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}

	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************

	private static final class HelloPanel extends JPanel
	{
		private final String	message;

		public HelloPanel(String message)
		{
			this.message = ((message != null) ? message : "");
		}

		public HelloPanel()
		{
			this("");
		}

		public void	paintComponent(Graphics g)
		{
			FontMetrics	fm = g.getFontMetrics(FONT);
			int			fw = fm.stringWidth(message);
			int			fh = fm.getMaxAscent() + fm.getMaxDescent();
			int			x = (getWidth() - fw) / 2;
			int			y = (getHeight() - fh) / 2;
			Rectangle		r = new Rectangle(x, y, fw + 4, fh + 1);

			if (FILL_COLOR != null)
			{
				g.setColor(FILL_COLOR);
				g.fillRect(r.x, r.y, r.width - 1, r.height - 1);
			}

			if (EDGE_COLOR != null)
			{
				g.setColor(EDGE_COLOR);
				g.drawRect(r.x, r.y, r.width - 1, r.height - 1);

				g.setFont(FONT);
				g.drawString(message, r.x + 2, r.y + fm.getMaxAscent());
			}
		}
	}
}

//******************************************************************************
