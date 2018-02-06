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
			drawBackground(g);	// Draw background
			drawText(g);	// Draw text "SO MUCH COLORRRR!"
			drawTree(g);	// Draw tree, probably an oak-pine
			drawClouds(g);	// Draw clouds, weather's a'changin
			drawBall(g);	// Draw ball
			drawCat(g);	// Draw cat, aka Oscar - Basketball Superstar
			drawFlowers(g);	// Draw flowers, one normal and one psychedelic

			Image image = fullyLoadImage("sun.png");	// Load in image of sun
			g.drawImage(image, 280, 0, null);	// Place sun in top right corner
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
			Stroke stroke = new BasicStroke(2.0f);

			/****************************
			* Draw "so"
			*****************************/
			Color textColor = new Color(0, 0, 0); // Set color to black
			g.setColor(textColor); // Set graphics color
			g.setFont(font); // Set graphics font
			g.drawString(so, 75, 100); // Draw "so" in relative position

			// Draw underline
			g.setStroke(stroke);
			g.draw(new Line2D.Double(75.0, 105.0, 100.0, 105.0));
			/****************************
			* Draw "much" and box
			*****************************/
			stroke = new BasicStroke(1.0f);	// Setup stroke for rectangle
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
		// Status = DONE
		private void drawTree(Graphics2D g)
		{
			/****************************
			* Setup params for tree
			*****************************/
			Color trunkcolor = new Color(134, 98, 13);	// Create trunk color
			Color treecolor = new Color(26, 250, 2, 90);	// Create tree leaves color
			Color treeoutline = new Color(0, 78, 0);	// Color of tree leaf outline

			/****************************
			* Draw tree trunk
			*****************************/
			Stroke stroke = new BasicStroke(1.5f);	// Setup stroke for tree trunk

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

			//Setup triangle path
			Path2D.Double trianglepath = new Path2D.Double();
			trianglepath.moveTo(45.0, 70.0);	// Start path here
			trianglepath.lineTo(20.0, 200.0);
			trianglepath.lineTo(70.0, 200.0);
			trianglepath.lineTo(45.0, 70.0);
			g.draw(trianglepath);

			g.setColor(treecolor);
			g.fill(trianglepath);
		}

		// Draw all attributes related to the clouds
		// Status = DONE
		private void drawClouds(Graphics2D g)
		{
			/****************************
			* Setup params for cloud
			*****************************/
			Color cloudColor = new Color(183, 208, 229, 80);	// Set color of clouds with alpha
			Color cloudOutline = new Color(161, 181, 198);
			Stroke stroke = new BasicStroke(1.0f);	// Set stroke for cloud outline
			double w = 60.0;	// Width of the Ellipse2D used to define the cloud
			double h = 32.0; 	// Height of the Ellipse2D used to define the cloud
			g.setStroke(stroke);	// Set stroke for cloud outline

			/****************************
			* Draw alllll the clouds
			*****************************/
			drawDemClouds(g, 65.0, 30.0, w, h, cloudColor, cloudOutline);
			drawDemClouds(g, 55.0, 45.0, w, h, cloudColor, cloudOutline);
			drawDemClouds(g, 45.0, 20.0, w, h, cloudColor, cloudOutline);
			drawDemClouds(g, 80.0, 25.0, w, h, cloudColor, cloudOutline);
			drawDemClouds(g, 90.0, 25.0, w, h, cloudColor, cloudOutline);
			drawDemClouds(g, 85.0, 45.0, w, h, cloudColor, cloudOutline);
		}

		// Draw clouds given parameters to prevent clutter
		private void drawDemClouds(Graphics2D g, double x, double y, double w, double h, Color in, Color out)
		{
			/****************************
			* Draw cloud given passed parameters
			*****************************/
			Ellipse2D.Double cloud = new Ellipse2D.Double(x, y, w, h);
			g.setColor(out);
			g.draw(cloud);
			g.setColor(in);
			g.fill(cloud);
		}

		// Draw all attributes related to the ball
		// Status = DONE
		private void drawBall(Graphics2D g)
		{
			/****************************
			* Setup ball and draw ball
			*****************************/
			double h = 40.0;	// Height of bounding rectangle
			double w = 40.0;	// Width of bounding rectangle
			Color red = new Color(252, 54, 37);	// Lower left color
			Color salmon = new Color(233, 142, 145); // Upper right color
			Ellipse2D.Double ball = new Ellipse2D.Double(110.0, 225.0, w, h);	// Create ball
			GradientPaint ballpaint = new GradientPaint(105, 230, red, 130, 210, salmon);	// Set gradient paint
			g.setPaint(ballpaint);	// Set graphics gradient paint
			g.fill(ball);	// Draw and fill ball

			/****************************
			* Setup ball shadow and draw ball shadow
			*****************************/
			h = 20.0;
			w = 40.0;
			Color ballshadow = new Color(117, 86, 18, 85);	// Set color for shadow with alpha
			Ellipse2D.Double shadow = new Ellipse2D.Double(90.0, 265.0, w, h);	// Create shadow
			g.setColor(ballshadow);
			g.fill(shadow);
		}

		// Draw all attributes related to the cat
		// Status = DONE
		private void drawCat(Graphics2D g)
		{
			/****************************
			* Setup colors/stroke for Cat body and legs
			*****************************/
			Color catbod = new Color(197, 230, 223);	// Set color for body of cat
			Color cateye = new Color(0, 144, 146);	// Set color for eye of catch
			Color outline = new Color(0, 0, 0);	// Black outline
			Stroke stroke = new BasicStroke(1.5f);	// Stroke for outline of cat/whiskers
			double h = 30.0;	// Height for ellipse, can be adjusted and reused
			double w = 40.0;	// Width for ellipse, can be adjusted and reused

			/****************************
			* Setup and draw cat head, ears, eyes, and whiskers
			*****************************/
			// Back ear
			Path2D.Double ear = new Path2D.Double();

			ear.moveTo(195.0, 165.0);
			ear.lineTo(187.0, 175.0);
			ear.lineTo(200.0, 175.0);
			ear.lineTo(195.0, 165.0);

			g.setStroke(stroke);
			g.setColor(outline);
			g.draw(ear);
			g.setColor(catbod);
			g.fill(ear);

			// Head
			Ellipse2D.Double cathead = new Ellipse2D.Double(165.0, 170.0, w, h);
			g.setColor(outline);
			g.draw(cathead);
			g.setColor(catbod);
			g.fill(cathead);

			// Eye
			h = 5.0;
			w = 6.0;
			cathead = new Ellipse2D.Double(175.0, 175.0, w, h);
			g.setColor(outline);
			g.draw(cathead);
			g.setColor(new Color(255, 255, 255));
			g.fill(cathead);

			// Iris
			h = 4.0;
			w = 4.0;
			cathead = new Ellipse2D.Double(175.0, 175.8, w, h);
			g.setColor(outline);
			g.draw(cathead);
			g.setColor(cateye);
			g.fill(cathead);

			// Front ear
			ear = new Path2D.Double();

			ear.moveTo(190.0, 165.0);
			ear.lineTo(186.0, 175.0);
			ear.lineTo(194.0, 175.0);
			ear.lineTo(190.0, 165.0);

			g.setStroke(stroke);
			g.setColor(outline);
			g.draw(ear);
			g.setColor(catbod);
			g.fill(ear);

			// Whiskers
			h = 30.0;	// For Arc2D
			w = 35.0;	// For Arc2D
			stroke = new BasicStroke(1.0f);
			g.setStroke(stroke);
			g.setColor(outline);
			g.draw(new Arc2D.Double(150.0, 185.0, w, h, 90.0, 75.0, Arc2D.OPEN));
			g.draw(new Arc2D.Double(155.0, 185.0, w, h, 90.0, 75.0, Arc2D.OPEN));
			g.draw(new Arc2D.Double(160.0, 185.0, w, h, 90.0, 75.0, Arc2D.OPEN));

			/****************************
			* Setup and draw cat bod and legs
			*****************************/
			// Legs
			stroke = new BasicStroke(5.0f);
			g.setStroke(stroke);
			g.setColor(outline);
			g.draw(new Line2D.Double(210.0, 220.0, 165.0, 247.0));	// front legs
			g.draw(new Line2D.Double(215.0, 220.0, 215.0, 257.0));
			g.draw(new Line2D.Double(245.0, 218.0, 248.0, 258.0)); // back legs
			g.draw(new Line2D.Double(254.0, 215.0, 260.0, 256.0));

			// Paws
			w = 15.0;	// height and width for paws are same to get circle
			h = 15.0;
			g.fill(new Ellipse2D.Double(159.0, 239.0, w, h));
			g.fill(new Ellipse2D.Double(208.0, 255.0, w, h));
			g.fill(new Ellipse2D.Double(240.0, 253.0, w, h));
			g.fill(new Ellipse2D.Double(253.0, 253.0, w, h));

			// Body
			stroke = new BasicStroke(1.5f);
			g.setStroke(stroke);
			w = 80.0;
			h = 55.0;
			Ellipse2D.Double catbody = new Ellipse2D.Double(190.0, 180.0, w, h);
			g.setColor(outline);
			g.draw(catbody);
			g.setColor(catbod);
			g.fill(catbody);

		}

		// Draw all attributes related to the flowers
		// and draw physcho flower
		// Status = NOT STARTED
		private void drawFlowers(Graphics2D g)
		{
			/****************************
			* Setup and call drawDemFlowers
			*****************************/
			double w = 6.0;	// Adjustable width for stem
			double h = 50.0;	// Adjustable height for stem
			drawDemFlowers(g, 305.0, 155.0, w, h);
			drawDemFlowers(g, 320.0, 147.0, w, h);
			drawDemFlowers(g, 335.0, 158.0, w, h);
			drawDemFlowers(g, 345.0, 167.0, w, h);

			// Special flower boi
			drawDemFlowers(g, 312.0, 175.0, w, h);
		}

		// Draw normal flowers given paremters to prevent clutter
		private void drawDemFlowers(Graphics2D g, double x, double y,
			double w, double h)
		{
			/****************************
			* Draw allllll the flowers
			*****************************/
			Color stem = new Color(255, 201, 0);	// Color of stem of flower
			Color flower = new Color(146, 151, 255);	// Color of petals
			Stroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 0, new float[] {3, 2}, 0);	// Set dashed stroke

			// Draw stem
			g.setColor(stem);
			g.fill(new Rectangle2D.Double(x, y, w, h));

			// Draw petals
			g.setColor(flower);
			Path2D.Double petal = new Path2D.Double();
			petal.moveTo(x + w/2.0, y - h/2.0);	// Top of petal
			petal.lineTo(x - h/4.0, y - h/2.0 + h/4.0); // Left
			petal.lineTo(x + w/2.0, y);	// Bottom
			petal.lineTo(x + + 2*(w/2.0) + h/4.0, y - h/2.0 + h/4.0);	// Right
			petal.lineTo(x + w/2.0, y - h/2.0);	// Back to top

			g.setColor(flower);
			g.fill(petal);
			g.setStroke(stroke);
			g.setColor(new Color(0, 0, 0));
			g.draw(petal);
		}

		// Draw all attributes related to the background
		// including the sky, ground, road, and grass
		// Status = NOT STARTED
		private void drawBackground(Graphics2D g)
		{
			/****************************
			* Setup background colors and thangs
			*****************************/
			// Setup all colors that will be used in background
			Color skytop = new Color(133, 191, 238); // Top of sky gradient
			Color skybottom = new Color(228, 242, 255); // Bottom of sky gradient
			Color dirtbrown = new Color(147, 129, 8); // Brown of dirt gradient
			Color dirtpink = new Color(177, 84, 81); // Pink of dirt gradient
			Color road = new Color(145, 145, 145); // Color of road
			Color arrow = new Color(225, 251, 0); // Color of arrows on road
			Color grass = new Color(0, 142, 0);	// Color of the diagonal grass
			Stroke stroke = new BasicStroke(1.5f);	// Outline stroke for arrows

			/****************************
			* Setup and draw sky
			*****************************/
			GradientPaint sky = new GradientPaint(200, 0, skytop, 200, 175, skybottom);
			Rectangle2D.Double skybox = new Rectangle2D.Double(0.0, 0.0, 400.0, 175.0);
			g.setPaint(sky);
			g.fill(skybox);

			/****************************
			* Setup and draw dirt
			*****************************/
			GradientPaint dirt = new GradientPaint(0, 200, dirtbrown, 65, 200, dirtpink, true);
			Rectangle2D.Double dirtbox = new Rectangle2D.Double(0.0, 175.0, 400.0, 150.0);
			g.setPaint(dirt);
			g.fill(dirtbox);

			/****************************
			* Setup and draw grass
			*****************************/
			stroke = new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
			g.setStroke(stroke);
			g.setColor(grass);
			for (double i = 0.0; i < 400.0; i = i + 5.0)
			{
				g.draw(new Line2D.Double(i, 280, i - 5.0, 325));
			}
			/****************************
			* Setup and draw roads
			*****************************/
			Rectangle2D.Double roadbox = new Rectangle2D.Double(0.0, 325.0, 400.0, 95.0);
			g.setColor(road);
			g.fill(roadbox);

			/****************************
			* Setup and draw arrows on road
			*****************************/
			// Gonna be one big path
			stroke = new BasicStroke(1.5f);	// Outline stroke for arrows
			Path2D.Double arrowpath = new Path2D.Double();
			// Left arrow
			arrowpath.moveTo(100.0, 335.0);	// Top left
			arrowpath.lineTo(80.0, 345.0);	// Left point
			arrowpath.lineTo(100.0, 355.0);	// Bottom left
			arrowpath.lineTo(100.0, 350.0);	// Bottom left middle
			arrowpath.lineTo(120.0, 350.0);	// Bottom right middle
			arrowpath.lineTo(120.0, 355.0);	// Bottom right
			arrowpath.lineTo(140.0, 345.0);	// Right point
			arrowpath.lineTo(120.0, 335.0);	// Top right
			arrowpath.lineTo(120.0, 340.0);	// Top right middle
			arrowpath.lineTo(100.0, 340.0);	// Top left middle
			arrowpath.lineTo(100.0, 335.0);	// Back to top left

			// Middle arrow
			double m = 100.0;	// Move over the next arrow by this much
			arrowpath.moveTo(100.0 + m, 335.0);	// Top left
			arrowpath.lineTo(80.0 + m, 345.0);	// Left point
			arrowpath.lineTo(100.0 + m, 355.0);	// Bottom left
			arrowpath.lineTo(100.0 + m, 350.0);	// Bottom left middle
			arrowpath.lineTo(120.0 + m, 350.0);	// Bottom right middle
			arrowpath.lineTo(120.0 + m, 355.0);	// Bottom right
			arrowpath.lineTo(140.0 + m, 345.0);	// Right point
			arrowpath.lineTo(120.0 + m, 335.0);	// Top right
			arrowpath.lineTo(120.0 + m, 340.0);	// Top right middle
			arrowpath.lineTo(100.0 + m, 340.0);	// Top left middle
			arrowpath.lineTo(100.0 + m, 335.0);	// Back to top left

			// Right arrow
			m = 200.0;	// Move over the next arrow by this much
			arrowpath.moveTo(100.0 + m, 335.0);	// Top left
			arrowpath.lineTo(80.0 + m, 345.0);	// Left point
			arrowpath.lineTo(100.0 + m, 355.0);	// Bottom left
			arrowpath.lineTo(100.0 + m, 350.0);	// Bottom left middle
			arrowpath.lineTo(120.0 + m, 350.0);	// Bottom right middle
			arrowpath.lineTo(120.0 + m, 355.0);	// Bottom right
			arrowpath.lineTo(140.0 + m, 345.0);	// Right point
			arrowpath.lineTo(120.0 + m, 335.0);	// Top right
			arrowpath.lineTo(120.0 + m, 340.0);	// Top right middle
			arrowpath.lineTo(100.0 + m, 340.0);	// Top left middle
			arrowpath.lineTo(100.0 + m, 335.0);	// Back to top left

			// Draw arrows
			g.setColor(new Color(0, 0, 0));
			g.setStroke(stroke);
			g.draw(arrowpath);
			g.setColor(arrow);
			g.fill(arrowpath);
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
