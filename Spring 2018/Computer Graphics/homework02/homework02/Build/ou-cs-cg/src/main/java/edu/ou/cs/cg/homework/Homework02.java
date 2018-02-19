//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.homework;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

//******************************************************************************

/**
 * The <CODE>Homework02</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Homework02
	implements GLEventListener
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();
	public static final Random	RANDOM = new Random();

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private int				k = 0;		// Just an animation counter

	private int				w;			// Canvas width
	private int				h;			// Canvas height
	private TextRenderer	renderer;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		// Setup default settings for canvas frame
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);
		JFrame			frame = new JFrame("Homework02");

		canvas.setPreferredSize(new Dimension(1500, 950));

		frame.setBounds(50, 50, 1000, 1000);
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		canvas.addGLEventListener(new Homework02());

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		// Start program and start drawing
		animator.start();
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();

		renderer = new TextRenderer(new Font("Serif", Font.PLAIN, 18),
									true, true);

	}

	public void		dispose(GLAutoDrawable drawable)
	{
		renderer = null;
	}

	public void		display(GLAutoDrawable drawable)
	{
		update();
		render(drawable);
	}

	public void		reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
	}

	//**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

	private void	update()
	{
		k++;									// Counters are useful, right?
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer

		//setProjection(gl);						// Use a coordinate system

		drawBackground(gl);							// Draw background
		drawGalaxy(gl);									// Draw galaxy
		drawFences(gl);									// Draw all fences
		drawHouse(gl);									// Draw all houses
		drawKite(gl);										// Draw kite
		drawStars(gl);									// Draw 5 stars
		drawMoon(gl);										// Draw moon
	}

	//**********************************************************************
	// Private Methods (Coordinate System)
	//**********************************************************************

	private void	setProjection(GL2 gl)
	{
		GLU		glu = new GLU();

		gl.glMatrixMode(GL2.GL_PROJECTION);			// Prepare for matrix xform
		gl.glLoadIdentity();						// Set to identity matrix
		glu.gluOrtho2D(-1.0f, 1.0f, -1.0f, 1.0f);	// 2D translate and scale
	}

	//**********************************************************************
	// Private Methods (Scene)
	//**********************************************************************

	// This page is helpful (scroll down to "Drawing Lines and Polygons"):
	// http://www.linuxfocus.org/English/January1998/article17.html

	/****************************************
	*	Draw the background, including the sky,
	* grass, and sidewalk with the chalk Lines
	* and the hopscotch
	*
	* STATUS = DONE
	****************************************/
	private void	drawBackground(GL2 gl)
	{
		/*******************
		*	Draw the sky
		*******************/
		gl.glBegin(gl.GL_POLYGON);

		// Top of the sky
		gl.glColor3f(0.03921f, 0.05490f, 0.1f);
		gl.glVertex2d(-1.0, 1.0);
		gl.glVertex2d(1.0, 1.0);
		// Bottom of the sky
		// Setup gradient
		gl.glColor3f(0.57254f, 0.50980f, 0.38431f);
		gl.glVertex2d(1.0, -0.5);	// Note overlap of sky into grass
		gl.glVertex2d(-1.0, -0.5);	// ^

		gl.glEnd();

		/*******************
		*	Draw the grass
		*******************/
		gl.glBegin(gl.GL_POLYGON);

		// Top of Grass
		gl.glColor3f(0.22156f, 0.14705f, 0.24705f);
		gl.glVertex2d(-1.0, -0.1);
		gl.glVertex2d(1.0, -0.1);
		// Bottom of the Grass
		gl.glColor3f(0.38039f, 0.55686f, 0.21764f);
		gl.glVertex2d(1.0, -0.6);
		gl.glVertex2d(-1.0, -0.6);

		gl.glEnd();

		/*******************
		*	Draw the sidewalk concrete
		*******************/
		gl.glBegin(gl.GL_POLYGON);

		// Main Sidewalk
		gl.glColor3f(0.46862f, 0.46862f, 0.46862f);
		gl.glVertex2d(-1.0, -0.6);
		gl.glVertex2d(1.0, -0.6);
		gl.glVertex2d(1.0, -1.0);
		gl.glVertex2d(-1.0, -1.0);

		gl.glEnd();

		/*******************
		*	Draw the sidewalk chalk lines
		*******************/
		gl.glBegin(gl.GL_LINES);

		// Top and bottom lines
		// Top line
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glVertex2d(-1.0, -0.6);
		gl.glVertex2d(1.0, -0.6);
		// Bottom line
		gl.glVertex2d(-1.0, -0.999);
		gl.glVertex2d(1.0, -0.999);

		// Diagonal lines
		for (double i = -0.9; i <= 1.0; i = i + 0.15)
		{
			gl.glVertex2d(i, -0.6);
			gl.glVertex2d(i - 0.1, -0.999);
		}

		gl.glEnd();

		/*******************
		*	Draw the Hopscotch
		*******************/

		gl.glEnable(gl.GL_BLEND);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		// Inner colored blocks
		// Inner block 1
		gl.glBegin(gl.GL_POLYGON);

		gl.glColor4f(0.79607f, 0.78823f, 0.56862f, 0.5f);
		gl.glVertex2d(0.1, -0.7);	//a
		gl.glVertex2d(0.085, -0.75);	//b
		gl.glVertex2d(0.25, -0.75);	//c
		gl.glVertex2d(0.265, -0.7);	//d

		gl.glEnd();

		// Inner block 2
		gl.glBegin(gl.GL_POLYGON);

		gl.glColor4f(0.79607f, 0.78823f, 0.56862f, 0.5f);
		gl.glVertex2d(0.2425, -0.78);	//a
		gl.glVertex2d(0.2975, -0.78);	//b
		gl.glVertex2d(0.3275, -0.67);	//c
		gl.glVertex2d(0.2725, -0.67);	//d

		gl.glEnd();

		// Inner block 3
		gl.glBegin(gl.GL_POLYGON);

		gl.glColor4f(0.79607f, 0.78823f, 0.56862f, 0.5f);
		gl.glVertex2d(0.305, -0.75);	//a
		gl.glVertex2d(0.36, -0.75);	//b
		gl.glVertex2d(0.375, -0.7);	//c
		gl.glVertex2d(0.32, -0.7);	//d

		gl.glEnd();

		// Inner block 4
		gl.glBegin(gl.GL_POLYGON);

		gl.glColor4f(0.79607f, 0.78823f, 0.56862f, 0.5f);
		gl.glVertex2d(0.3525, -0.78);	//a
		gl.glVertex2d(0.4075, -0.78);	//b
		gl.glVertex2d(0.4375, -0.67);	//c
		gl.glVertex2d(0.3825, -0.67);	//d

		gl.glEnd();

		// Inner block 5
		gl.glBegin(gl.GL_POLYGON);

		gl.glColor4f(0.79607f, 0.78823f, 0.56862f, 0.5f);
		gl.glVertex2d(0.415, -0.75);	//a
		gl.glVertex2d(0.47, -0.75);	//b
		gl.glVertex2d(0.485, -0.7);	//c
		gl.glVertex2d(0.43, -0.7);	//d

		gl.glEnd();

		// Outline
		gl.glBegin(gl.GL_LINE_STRIP);

		gl.glColor3f(0.95f, 0.95f, 0.95f);

		// First box left
		gl.glVertex2d(0.1, -0.7);
		gl.glVertex2d(0.085, -0.75);
		gl.glVertex2d(0.14, -0.75);
		gl.glVertex2d(0.155, -0.7);
		gl.glVertex2d(0.1, -0.7);
		// Second box to right of above box
		gl.glVertex2d(0.21, -0.7);
		gl.glVertex2d(0.195, -0.75);
		gl.glVertex2d(0.14, -0.75);
		// Third box to right of above box
		gl.glVertex2d(0.25, -0.75);
		gl.glVertex2d(0.265, -0.7);
		gl.glVertex2d(0.21, -0.7);
		// First double box
		gl.glVertex2d(0.265, -0.7);
		gl.glVertex2d(0.2725, -0.67);
		gl.glVertex2d(0.3275, -0.67);
		gl.glVertex2d(0.2975, -0.78);
		gl.glVertex2d(0.2425, -0.78);
		gl.glVertex2d(0.2575, -0.72);
		gl.glVertex2d(0.3125, -0.72);
		// Next single box
		gl.glVertex2d(0.32, -0.7);
		gl.glVertex2d(0.375, -0.7);
		gl.glVertex2d(0.36, -0.75);
		gl.glVertex2d(0.305, -0.75);
		// Next double box
		gl.glVertex2d(0.36, -0.75);
		gl.glVertex2d(0.3825, -0.67);
		gl.glVertex2d(0.4375, -0.67);
		gl.glVertex2d(0.4075, -0.78);
		gl.glVertex2d(0.3525, -0.78);
		gl.glVertex2d(0.3675, -0.72);
		gl.glVertex2d(0.4225, -0.72);
		// Final box
		gl.glVertex2d(0.43, -0.7);
		gl.glVertex2d(0.485, -0.7);
		gl.glVertex2d(0.47, -0.75);
		gl.glVertex2d(0.415, -0.75);


		gl.glEnd();
	}

	/****************************************
	*	Call to draw both types of fences (double and single)
	*
	* STATUS = DONE
	****************************************/
	private void drawFences(GL2 gl)
	{
		double height = -0.59;
		// First single fences on left side of picture
		drawDatFence(gl, true, false, -0.99, height);
		drawDatFence(gl, true, false, -0.49, height);
		drawDatFence(gl, true, false, -0.32, height);

		// Now draw the double plank fences
		drawDatFence(gl, false, true, 0.30, height);
		drawDatFence(gl, false, true, 0.46, height);
		drawDatFence(gl, false, false, 0.91, height);
	}

	/****************************************
	*	Draw fence with given parameter
	* x = bottom left x value of beginning of fence
	* y = bottom left y value of beginning of fence
	* single = whether this is a single plank fence or not
	* twoPlanks = whether both double planked fence planks should be drawn
	* STATUS = DONE
	****************************************/
	private void drawDatFence(GL2 gl, boolean single, boolean twoPlanks, double x, double y)
	{
		// Single planked fence
		if(single)
		{
			// First plank
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.3);
			gl.glVertex2d(x + 0.04, y + 0.33);
			gl.glVertex2d(x + 0.04, y);
			gl.glEnd();

			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.3);
			gl.glVertex2d(x + 0.04, y + 0.33);
			gl.glVertex2d(x + 0.04, y);
			gl.glEnd();

			// Second plank
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
			gl.glVertex2d(x + 0.04, y);
			gl.glVertex2d(x + 0.04, y + 0.3);
			gl.glVertex2d(x + 0.08, y + 0.33);
			gl.glVertex2d(x + 0.08, y);
			gl.glEnd();

			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.04, y);
			gl.glVertex2d(x + 0.04, y + 0.3);
			gl.glVertex2d(x + 0.08, y + 0.33);
			gl.glVertex2d(x + 0.08, y);
			gl.glEnd();

			// Third planke
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
			gl.glVertex2d(x + 0.08, y);
			gl.glVertex2d(x + 0.08, y + 0.3);
			gl.glVertex2d(x + 0.12, y + 0.33);
			gl.glVertex2d(x + 0.12, y);
			gl.glEnd();

			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.08, y);
			gl.glVertex2d(x + 0.08, y + 0.3);
			gl.glVertex2d(x + 0.12, y + 0.33);
			gl.glVertex2d(x + 0.12, y);
			gl.glEnd();

			// Fourth plank
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
			gl.glVertex2d(x + 0.12, y);
			gl.glVertex2d(x + 0.12, y + 0.3);
			gl.glVertex2d(x + 0.16, y + 0.33);
			gl.glVertex2d(x + 0.16, y);
			gl.glEnd();

			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.12, y);
			gl.glVertex2d(x + 0.12, y + 0.3);
			gl.glVertex2d(x + 0.16, y + 0.33);
			gl.glVertex2d(x + 0.16, y);
			gl.glEnd();
		}
		// Double planked fence
		else
		{
			// First pair of double planks
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.3);
			gl.glVertex2d(x + 0.04, y + 0.33);
			gl.glVertex2d(x + 0.08, y + 0.3);
			gl.glVertex2d(x + 0.08, y);
			gl.glEnd();

			gl.glBegin(gl.GL_LINES);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.3);
			gl.glVertex2d(x + 0.04, y + 0.33);
			gl.glVertex2d(x + 0.08, y + 0.3);
			gl.glVertex2d(x + 0.08, y);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x + 0.04, y);
			gl.glVertex2d(x + 0.04, y + 0.33);
			gl.glEnd();

			if (twoPlanks)
			{
				// Second pair of double planks
				gl.glBegin(gl.GL_POLYGON);
				gl.glColor3f(0.79608f, 0.788235f, 0.56863f);
				gl.glVertex2d(x + 0.08, y);
				gl.glVertex2d(x + 0.08, y + 0.3);
				gl.glVertex2d(x + 0.12, y + 0.33);
				gl.glVertex2d(x + 0.16, y + 0.3);
				gl.glVertex2d(x + 0.16, y);
				gl.glEnd();

				gl.glBegin(gl.GL_LINES);
				gl.glColor3f(0.0f, 0.0f, 0.0f);
				gl.glVertex2d(x + 0.08, y);
				gl.glVertex2d(x + 0.08, y + 0.3);
				gl.glVertex2d(x + 0.12, y + 0.33);
				gl.glVertex2d(x + 0.16, y + 0.3);
				gl.glVertex2d(x + 0.16, y);
				gl.glVertex2d(x + 0.08, y);
				gl.glVertex2d(x + 0.12, y + 0.33);
				gl.glEnd();

				// Fix line not drawing on right double plank
				gl.glBegin(gl.GL_LINES);
				gl.glColor3f(0.0f, 0.0f, 0.0f);
				gl.glVertex2d(x + 0.16, y);
				gl.glVertex2d(x + 0.16, y + 0.3);
				gl.glVertex2d(x + 0.12, y + 0.33);
				gl.glVertex2d(x + 0.12, y);
				gl.glEnd();
			}
		}
	}

	/****************************************
	*	Draw all houses by calling a method so
	* as to allow for n number of houses to be
	* drawn.
	*
	* STATUS = STARTED
	****************************************/
	private void drawHouse(GL2 gl)
	{
		double height = -0.59;

		// Draw fancy house first
		drawDatHouse(gl, true, false, -0.82, height);
	}

	/****************************************
	*	Draw house with given parameters
	* x = Starting x coordinate of bottom left corner
	* y = starting y coordinate of bottom left corner
	* detailed = whether to draw fancy green house or not
	* hasStar = non-green house with star or non-green house w/out star
	* STATUS = STARTED
	****************************************/
	private void drawDatHouse(GL2 gl, boolean detailed, boolean hasStar,
			double x, double y)
	{
		// Doorknob stuff
		float theta = 2 * 3.1415926 / float(32);
		float tangential_factor = tanf(theta);
		float radial_factor = cosf(theta);
		float dx = 0.01;
		float dy = 0;

		// Fancy green house
		if (detailed)
		{
			// Draw bottom of house
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.31373f, 0.31373f, 0.0f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.45);
			gl.glVertex2d(x + 0.32, y + 0.45);
			gl.glVertex2d(x + 0.32, y);
			gl.glEnd();
			// Draw bottom lines
			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x, y);
			gl.glVertex2d(x, y + 0.45);
			gl.glVertex2d(x + 0.32, y + 0.45);
			gl.glVertex2d(x + 0.32, y);
			gl.glEnd();

			// Draw Chimney
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.57647f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.22, y + 0.45);
			gl.glVertex2d(x + 0.22, y + 0.675);
			gl.glVertex2d(x + 0.26, y + 0.675);
			gl.glVertex2d(x + 0.26, y + 0.45);
			gl.glEnd();
			// Draw chimney lines
			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.22, y+ 0.45);
			gl.glVertex2d(x + 0.22, y + 0.675);
			gl.glVertex2d(x + 0.26, y + 0.675);
			gl.glVertex2d(x + 0.26, y + 0.45);
			gl.glEnd();

			// Draw roof
			gl.glBegin(gl.GL_TRIANGLES);
			gl.glColor3f(0.31373f, 0.25098f, 0.12549f);
			gl.glVertex2d(x, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);
			gl.glVertex2d(x + 0.32, y + 0.45);
			gl.glEnd();
			// Draw roof lines
			gl.glBegin(gl.GL_LINES);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.16, y + 0.675);	// anchor
			gl.glVertex2d(x, y + 0.45);
			gl.glVertex2d(x + 0.0533, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);	// back to anchor
			gl.glVertex2d(x + 0.0533, y + 0.45);
			gl.glVertex2d(x + 0.1066, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);	// back to anchor
			gl.glVertex2d(x + 0.1066, y + 0.45);
			gl.glVertex2d(x + 0.2133, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);	// back to anchor
			gl.glVertex2d(x + 0.2133, y + 0.45);
			gl.glVertex2d(x + 0.2666, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);	// back to anchor
			gl.glVertex2d(x + 0.2666, y + 0.45);
			gl.glVertex2d(x + 0.32, y + 0.45);
			gl.glVertex2d(x + 0.16, y + 0.675);	// back to anchor
			gl.glVertex2d(x + 0.32, y + 0.45);
			gl.glVertex2d(x, y + 0.45);
			gl.glEnd();

			// Draw door
			gl.glBegin(gl.GL_POLYGON);
			gl.glColor3f(0.8f, 0.56828f, 0.0f);
			gl.glVertex2d(x + 0.08, y);
			gl.glVertex2d(x + 0.08, y + 0.23);
			gl.glVertex2d(x + 0.14, y + 0.23);
			gl.glVertex2d(x + 0.14, y);
			gl.glEnd();
			// Draw door lines
			gl.glBegin(gl.GL_LINE_LOOP);
			gl.glColor3f(0.0f, 0.0f, 0.0f);
			gl.glVertex2d(x + 0.08, y);
			gl.glVertex2d(x + 0.08, y + 0.23);
			gl.glVertex2d(x + 0.14, y + 0.23);
			gl.glVertex2d(x + 0.14, y);
			gl.glEnd();

			// Draw doorknob
			// http://slabode.exofire.net/circle_draw.shtml

		}
		else
		{
			// Draw star if house has star
			if (hasStar)
			{

			}
			// Otherwise draw circle on door
			else
			{

			}
		}
	}

	private void drawDoorKnob(double x, double y, double r, int num_segments)
	{
		double theta = 2 * 3.1415926 / double(num_segnments);
	}

	/****************************************
	*	Draw the kite and string
	*
	* STATUS = NOT STARTED
	****************************************/
	private void drawKite(GL2 gl)
	{

	}

	/****************************************
	*	Draw all five stars
	*
	* STATUS = NOT STARTED
	****************************************/
	private void drawStars(GL2 gl)
	{

	}

	/****************************************
	*	Draw the moooooooon
	*
	* STATUS = NOT STARTED
	****************************************/
	private void drawMoon(GL2 gl)
	{

	}

	/****************************************
	*	Draw the super crazy galaxy
	* Use this to help: https://en.wikipedia.org/wiki/Lorenz_system
	* Also this: http://www.algosome.com/articles/lorenz-attractor-programming-code.html
	* And this: https://en.wikipedia.org/wiki/Tinkerbell_map
	* STATUS = NOT STARTED
	****************************************/
	private void drawGalaxy(GL2 gl)
	{

	}

}

/*********************************************
* COLORS NEEDED:
* Sky: Top = (10,14,25), Bottom = (146, 130, 98)
* Grass: Top = (82, 63, 63) Bottom = (97, 142, 81)
* Sidewalk: Concrete = (145, 145, 145), Hopscotch = (203, 201, 145, w/ alpha), Chalk = (190, 190, 190)
* Fence: (203, 201, 145)
* Brown House: Walls = (143, 82, 10), Door = (204, 145, 0), Star = (255, 251, 0), Chimney = (147, 17, 0)
* 						 Window Triangle = (255, 252, 145), Window Backgroun = (231, 204, 230)
* 						 Doorknob = (189, 202, 203), Door Circle = (255, 252, 145)
* Green House: Walls = (80, 80, 0), Roof = (80, 64, 32), Smoke = 178, 174, 169)
* Moon: Light = (255, 255, 255), Dark = (79, 80, 98)
* Stars: (255, 251, 0)
* Kite = (50, 52, 61)
*********************************************/

//******************************************************************************
