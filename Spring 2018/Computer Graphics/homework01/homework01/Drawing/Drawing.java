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
			// Where to send drawing commands for rendering
			Graphics2D g = (Graphics2D)graphics;
			Stroke stroke = g.getStroke();

			/****************************
			* The drawing of each element of the proposed drawing
			* has been broken down into associated groups, with each
			* group taking up a method below. The groups are as follows:
			* Text, Tree, Clouds, Cat-Ball, Flowers, Background
			*****************************/

			drawText(g);	// Draw text "SO MUCH COLORRRR!"
			drawTree(g);	// Draw tree, probably an oak-pine
			drawClouds(g);	// Draw clouds, weather's a'changin
			drawCatBall(g);	// Draw cat and ball, aka Oscar - Basketball Superstar
			drawFlowers(g);	// Draw flowers, one normal and one psychadelic
			drawBackground(g);	// Draw background, rest of the owl

			//(You also compile, test, and document me thoroughly.)
		}

		// This method will draw all text in the drawing, including any graphics
		// related to said text (rectangles, colors, etc.)
		// Status = DONE
		private void drawText(Graphics2D g)
		{
			// Setup required things for drawing text //
			String so = "so";
			String much = "much";
			String colortext = "color!";
	 		Font font = new Font("Serif", Font.PLAIN, 30); // Setup font

			/****************************
			* Draw "so"
			*****************************/
			Color textColor = new Color(0, 0, 0); // Set color to black
			g.setColor(textColor); // Set graphics color
			g.setFont(font); // Set graphics font
			g.drawString(so, 75, 100); // Draw "so" in relative position

			/****************************
			* Draw "much" and box
			*****************************/
			Stroke stroke = new BasicStroke(1.0f);	// Setup stroke for rectangle
			FontMetrics fm = g.getFontMetrics(font);	// Get font measurements

			// Get minimal bounding box of "much", as if it were drawn at the origin
			Rectangle2D rm = fm.getStringBounds(much, g);
			double x = rm.getX();
			double y = rm.getY();
			double w = rm.getWidth();
			double h = rm.getHeight();
			// Shift rectangle to where it should be
			Rectangle2D.Double r = new Rectangle2D.Double(x + 120.0, y + 115.0, w, h);
			g.setStroke(stroke);	// Set graphics stroke
			g.draw(r);

			textColor = new Color(255, 0, 0);	// Set color to red
			g.setColor(textColor);	// Set graphics color
			g.setFont(font);	// Set graphics font
			g.drawString(much, 120, 115);	// Draw "much"

			/****************************
			* Draw "color!" and soft rectangle
			*****************************/

			font = new Font("Serif", Font.ITALIC, 30);
			fm = g.getFontMetrics(font);
			Color fillColor = new Color(0, 144, 146);	// Set fill color for rounded rectangle

			// Get minimal bounding box of "color!", as if it were drawn at the origin
			Rectangle2D rrm = fm.getStringBounds(colortext, g);
			x = rrm.getX();
			y = rrm.getY();
			w = rrm.getWidth();
			h = rrm.getHeight();
			// Shift rounded rectangle to where it should be
			RoundRectangle2D.Double rr =
				new RoundRectangle2D.Double(x + 200.0, y + 140.0, w, h, 30.0, 30.0);

			stroke = new BasicStroke(2.0f);	// Set thicker stroke
			g.setStroke(stroke);	// Set graphics stroke to new, thicker stroke
			g.setColor(fillColor);	// Set color to fill color
			g.fill(rr);	// Fill in the rounded rectangle
			g.setColor(new Color(0, 0, 0));	// Reset color to black
			g.draw(rr);	// Draw the rounded rectangle

			textColor = new Color(255, 255, 255);	// Set color to white
			g.setColor(textColor);	// Set graphics color
			g.setFont(font);	// Set graphics font
			g.drawString(colortext, 200, 140);
		}

		// Draw all attributes related to the tree
		// Status = NOT STARTED
		private void drawTree(Graphics2D g)
		{
			Color trunkcolor = new Color(134, 98, 13);	// Create trunk color
			Color treecolor = new Color(26, 150, 2);	// Create tree leaves color
			Color treeoutline = new Color(0, 78, 0);	// Color of tree leaf outline

			/****************************
			* Draw tree trunk
			*****************************/
			Stroke stroke = new BasicStroke(1.0f);	// Setup stroke for tree trunk

			Rectangle2D.Double treetrunk =
				new Rectangle2D.Double(40.0, 140.0, 10.0, 175.0);
			g.setColor(new Color(0, 0, 0));	// Set color to black for outline
			g.setStroke(stroke);	// Set graphics stroke
			g.draw(treetrunk);

			g.setColor(trunkcolor);	// Set color to fill trunk
			g.fill(treetrunk);	// Fill in the tree trunk with brown

			/****************************
			* Draw tree leaves using Path2D
			*****************************/
			stroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);	// Thicken and round stroke
			g.setStroke(stroke);	// Set new graphics stroke
			g.setColor(treeoutline);	// Set color to tree leaf outline

			Path2D.Double trianglepath = new Path2D.Double(); //Setup trianble path
			trianglepath.moveTo(45.0, 70.0);	// Start path here
			trianglepath.lineTo(20.0, 200.0);
			trianglepath.lineTo(70.0, 200.0);
			trianglepath.lineTo(45.0, 70.0);

			g.fill(trianglepath);
		}

		// Draw all attributes related to the clouds
		// Status = NOT STARTED
		private void drawClouds(Graphics2D g)
		{

		}

		// Draw all attributes related to the cat and ball
		// Status = NOT STARTED
		private void drawCatBall(Graphics2D g)
		{

		}

		// Draw all attributes related to the flowers
		// Status = NOT STARTED
		private void drawFlowers(Graphics2D g)
		{

		}

		// Draw all attributes related to the background
		// including the sky, ground, road, and grass
		// Status = NOT STARTED
		private void drawBackground(Graphics2D g)
		{

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
