//******************************************************************************
// Last modified: Fri Jan 29 17:30:31 2016 by Chris Weaver
//******************************************************************************
//
// Skeleton for experimenting with Java2D.
//
//******************************************************************************

//package graphics.foo;

// Only these imports are allowed in homework #1!
//import java.lang.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

//******************************************************************************

/**
 * The <CODE>Drawing</CODE> class. It draws stuff. Something fishy's going on.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Drawing
{
	private static final Rectangle	BOUNDS = new Rectangle(50, 50, 400, 420);

	public static void	main(String[] argv)
	{
		JFrame	frame = new JFrame("Drawing");
		JPanel	panel = new Panel();

		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.setBounds(BOUNDS);
		frame.validate();

		panel.revalidate();
		panel.repaint();
	}

	private static final class Panel extends JPanel
	{
		// For homework 1, only change what's in here. (You can add methods to
		// call from here if you want.) Document your code thoroughly! Graphics
		// code is usually easy to understand syntactically but hard to imagine
		// what will happen at each point when it runs.
		public void		paintComponent(Graphics graphics)
		{
			//You complete me.

			//(You also compile, test, and document me thoroughly.)
		}

		// Use this to load images (and make sure they're done loading). The
		// filename must be relative to the directory containing this .java file.
		// It's easiest to just put them in the same directory as this file.
		private Image	fullyLoadImage(String filename)
		{
			Image	image = null;

			// We're going to do IO and thread stuff, so catch some exceptions
			try
			{
				// Load an image file into an image object
				image = Toolkit.getDefaultToolkit().createImage(filename);

				// This class watches for media loads & calculations to finish
				MediaTracker	tracker = new MediaTracker(this);

				tracker.addImage(image, 0);		// Track loading of the image
				tracker.waitForAll();			// Block until it's fully loaded
			}
			catch (Exception ex)
			{
				System.err.println("that wasn't supposed to happen");
				ex.printStackTrace();
				System.exit(1);
			}

			return image;
		}
	}
}

//******************************************************************************
