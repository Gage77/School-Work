//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Mar  1 18:52:22 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.interaction;

import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;

//******************************************************************************

/**
 * The <CODE>Interaction</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class View
	implements GLEventListener
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static final int				DEFAULT_FRAMES_PER_SECOND = 60;
	private static final DecimalFormat	FORMAT = new DecimalFormat("0.000");
	public static final double DEG2RAD = 3.14159/180;

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private final GLJPanel			canvas;
	private int						w;				// Canvas width
	private int						h;				// Canvas height

	private final KeyHandler		keyHandler;
	private final MouseHandler		mouseHandler;

	private final FPSAnimator		animator;
	private int						counter = 0;	// Frame display counter

	private TextRenderer			renderer;

	private Point2D.Double				origin;		// Current origin coordinates
	private Point2D.Double				cursor;		// Current cursor coordinates
	private ArrayList<Point2D.Double>	points;		// User's polyline points

	//************************************
	// Current project variables
	//************************************

	// Holds the current bound type
	private int typeOfBound = 1;
	// Array to hold the oddly shaped bounds
	private Point2D.Double oddBounds[] = {
		new Point2D.Double(0.0, 0.9),
		new Point2D.Double(-0.4, 0.7),
		new Point2D.Double(-0.7, 0.4),
		new Point2D.Double(-0.9, 0.0),
		new Point2D.Double(-0.8, -0.4),
		new Point2D.Double(-0.6, -0.7),
		new Point2D.Double(0.7, -0.9),
		new Point2D.Double(0.9, -0.8),
		new Point2D.Double(0.9, -0.1),
		new Point2D.Double(0.3, 0.7)
	};

	// Arrays to hold coords of moving object
	private Point2D.Double pointObject = new Point2D.Double(0.0, 0.0);

	private Point2D.Double squareObject[] = {
		new Point2D.Double(0.1, 0.1),
		new Point2D.Double(0.1, -0.1),
		new Point2D.Double(-0.1, -0.1),
		new Point2D.Double(-0.1, 0.1)
	};

	private Point2D.Double[] octogonObject;

	private Point2D.Double oddObject[] = {
		new Point2D.Double(0.0, 0.08),
		new Point2D.Double(-0.08, 0.05),
		new Point2D.Double(-0.1, -0.03),
		new Point2D.Double(0.1, 0.0),
		new Point2D.Double(0.06, 0.06)
	};

	// Current shape of moving object
	private int movingObjectType = 1;

	// Value to hold the current speed of the point/object moving across the screen
	private Random rand;
	private  double[] objectVector = new double[2];

	// Value to hold the size adjustment factor of the moving object
	private double objectSizeAdjustmentFactor = 1;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public View(GLJPanel canvas)
	{
		this.canvas = canvas;

		// Initialize model
		origin = new Point2D.Double(0.0, 0.0);
		cursor = null;
		points = new ArrayList<Point2D.Double>();

		// Initialize rendering
		canvas.addGLEventListener(this);
		animator = new FPSAnimator(canvas, DEFAULT_FRAMES_PER_SECOND);
		animator.start();

		// Initialize interaction
		keyHandler = new KeyHandler(this);
		mouseHandler = new MouseHandler(this);

		// Initialize the moving object's vector with random direction
		generateVector();

		octogonObject = new Point2D.Double[8];
		for (int i = 0; i < 8; i++)
		{
			octogonObject[i] = new Point2D.Double(Math.sin(i/8.0*2*Math.PI) * 0.1, Math.cos(i/8.0*2*Math.PI) * 0.1);
		}
	}

	//**********************************************************************
	// Getters and Setters
	//**********************************************************************

	public int	getWidth()
	{
		return w;
	}

	public int	getHeight()
	{
		return h;
	}

	public Point2D.Double	getOrigin()
	{
		return new Point2D.Double(origin.x, origin.y);
	}

	public void		setOrigin(Point2D.Double origin)
	{
		this.origin.x = origin.x;
		this.origin.y = origin.y;
		canvas.repaint();
	}

	public Point2D.Double	getCursor()
	{
		return cursor;
	}

	public void		setCursor(Point2D.Double cursor)
	{
		this.cursor = cursor;
		canvas.repaint();
	}

	public void		clear()
	{
		points.clear();
		canvas.repaint();
	}

	public void		add(Point2D.Double p)
	{
		points.add(p);
		canvas.repaint();
	}

	public void setBoundType(int type)
	{
		typeOfBound = type;
		resetObjectCoords();
	}

	public void setMovingObject(int type)
	{
		movingObjectType = type;
		resetObjectCoords();
		generateVector();
	}

	public void resetObjectCoords()
	{
		pointObject = new Point2D.Double(0.0, 0.0);

		squareObject[0] = new Point2D.Double(0.1, 0.1);
		squareObject[1] = new Point2D.Double(0.1, -0.1);
		squareObject[2] = new Point2D.Double(-0.1, -0.1);
		squareObject[3] = new Point2D.Double(-0.1, 0.1);

		oddObject[0] = new Point2D.Double(0.0, 0.08);
		oddObject[1] = new Point2D.Double(-0.08, 0.05);
		oddObject[2] = new Point2D.Double(-0.1, -0.03);
		oddObject[3] = new Point2D.Double(0.1, 0.0);
		oddObject[4] = new Point2D.Double(0.06, 0.06);

		for (int i = 0; i < 8; i++)
		{
			octogonObject[i] = new Point2D.Double(Math.sin(i/8.0*2*Math.PI) * 0.1, Math.cos(i/8.0*2*Math.PI) * 0.1);
		}
	}

	public void adjustObjectSpeed(double adjustment)
	{
		objectVector[0] = objectVector[0]*adjustment;
		objectVector[1] = objectVector[1]*adjustment;
	}

	public void adjustObjectSize(double adjustment)
	{
		// Set the scaling factor (for if the current object is a point)
		objectSizeAdjustmentFactor = adjustment;
		// Square coord adjustments
		if (movingObjectType == 2)
		{
			for (int i = 0; i < squareObject.length; i++)
			{
				squareObject[i].x = squareObject[i].x * objectSizeAdjustmentFactor;
				squareObject[i].y = squareObject[i].y * objectSizeAdjustmentFactor;
			}
		}
		// Octogon coord adjustments
		else if (movingObjectType == 3)
		{
			for (int i = 0; i < octogonObject.length; i++)
			{
				octogonObject[i].x = octogonObject[i].x * objectSizeAdjustmentFactor;
				octogonObject[i].y = octogonObject[i].y * objectSizeAdjustmentFactor;
			}
		}
		// Odd shape coord adjustements
		else if (movingObjectType == 4)
		{
			for (int i = 0; i < oddObject.length; i++)
			{
				oddObject[i].x = oddObject[i].x * objectSizeAdjustmentFactor;
				oddObject[i].y = oddObject[i].y * objectSizeAdjustmentFactor;
			}
		}
	}

	//**********************************************************************
	// Public Methods
	//**********************************************************************

	public void generateVector()
	{
		double min = -0.01;
		double max = 0.01;
		rand = new Random();
		double dx = min + (max - min) * rand.nextDouble();
		double dy = min + (max - min) * rand.nextDouble();
		objectVector[0] = dx;
		objectVector[1] = dy;
	}

	public Component	getComponent()
	{
		return (Component)canvas;
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();

		renderer = new TextRenderer(new Font("Monospaced", Font.PLAIN, 12),
									true, true);
	}

	public void		dispose(GLAutoDrawable drawable)
	{
		renderer = null;
	}

	public void		display(GLAutoDrawable drawable)
	{
		updateProjection(drawable);

		update(drawable);
		render(drawable);
	}

	public void		reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
	}

	//**********************************************************************
	// Private Methods (Viewport)
	//**********************************************************************

	private void	updateProjection(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();
		GLU		glu = new GLU();

		float	xmin = (float)(origin.x - 1.0);
		float	xmax = (float)(origin.x + 1.0);
		float	ymin = (float)(origin.y - 1.0);
		float	ymax = (float)(origin.y + 1.0);

		gl.glMatrixMode(GL2.GL_PROJECTION);			// Prepare for matrix xform
		gl.glLoadIdentity();						// Set to identity matrix
		glu.gluOrtho2D(xmin, xmax, ymin, ymax);		// 2D translate and scale
	}

	//**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

	private void	update(GLAutoDrawable drawable)
	{
		// Checks to see if the object is inside the current bounds and adjusts
		// the objects vector accordingly
		checkIfInBounds();

		if (movingObjectType == 1)
		{
			pointObject.x = (pointObject.x + objectVector[0]);
			pointObject.y = pointObject.y + objectVector[1];
		}
		else if (movingObjectType == 2)
		{
			for (int i = 0; i < squareObject.length; i++)
			{
				squareObject[i].x = (squareObject[i].x + objectVector[0]);
				squareObject[i].y = (squareObject[i].y + objectVector[1]);
			}
		}
		else if (movingObjectType == 3)
		{
			for (int i = 0; i < octogonObject.length; i++)
			{
				octogonObject[i].x = (octogonObject[i].x + objectVector[0]);
				octogonObject[i].y = (octogonObject[i].y + objectVector[1]);
			}
		}
		else if (movingObjectType == 4)
		{
			for (int i = 0; i < oddObject.length; i++)
			{
				oddObject[i].x = (oddObject[i].x + objectVector[0]);
				oddObject[i].y = (oddObject[i].y + objectVector[1]);
			}
		}
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer
		drawBoundingBox(gl, typeOfBound);	// Draw the currently specified bounding box
		drawMovingObject(gl, movingObjectType);	// Draw the currently specified moving object
		drawCursor(gl);							// Crosshairs at mouse location
		drawCursorCoordinates(drawable);		// Draw some text
	}

	//**********************************************************************
	// Private Methods (Scene)
	//**********************************************************************

	// Draw the specified bounding box according to typeOfBound
	private void drawBoundingBox(GL2 gl, int typeOfBound)
	{
		gl.glColor3f(1.0f, 1.0f, 1.0f);

		// Square bounding box
		if (typeOfBound == 1)
		{
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glVertex2d(0.9, 0.9);
			gl.glVertex2d(0.9, -0.9);
			gl.glVertex2d(-0.9, -0.9);
			gl.glVertex2d(-0.9, 0.9);
			gl.glEnd();
		}
		// Hexagonal bounding box
		else if (typeOfBound == 2)
		{
			gl.glBegin(GL2.GL_LINE_LOOP);
			for (int i = 0; i < 6; i++)
			{
				gl.glVertex2d(Math.sin(i/6.0*2*Math.PI), Math.cos(i/6.0*2*Math.PI));
			}
			gl.glEnd();
		}
		// 32-ogon bounding box
		else if (typeOfBound == 3)
		{
			gl.glBegin(GL2.GL_LINE_LOOP);
			for (int i = 0; i < 32; i++)
			{
				gl.glVertex2d(Math.sin(i/32.0*2*Math.PI), Math.cos(i/32.0*2*Math.PI));
			}
			gl.glEnd();
		}
		// Odd shaped bounding box
		else if (typeOfBound == 4)
		{
			drawOddBounds(gl);
		}
		// Default to square if no type is specified for some reason
		else
		{
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glVertex2d(0.9, 0.9);
			gl.glVertex2d(0.9, -0.9);
			gl.glVertex2d(-0.9, -0.9);
			gl.glVertex2d(-0.9, 0.9);
			gl.glEnd();
		}
	}

	// Draw the specified moving object according to objectType
	private void drawMovingObject(GL2 gl, int objectType)
	{
		gl.glColor3f(0.0f, 1.0f, 1.0f);
		// Point
		if (objectType == 1)
		{
			gl.glBegin(GL2.GL_POINTS);
			gl.glPointSize(5.0f);
			gl.glVertex2d(pointObject.x, pointObject.y);
			gl.glEnd();
		}
		// Square
		else if (objectType == 2)
		{
			gl.glBegin(GL2.GL_POLYGON);
			for (int i = 0; i < squareObject.length; i++)
			{
				gl.glVertex2d(squareObject[i].x, squareObject[i].y);
			}
			gl.glEnd();
		}
		// Octogon
		else if (objectType == 3)
		{
			gl.glBegin(GL2.GL_POLYGON);
			for (int i = 0; i < octogonObject.length; i++)
			{
				gl.glVertex2d(octogonObject[i].x, octogonObject[i].y);
			}
			gl.glEnd();
		}
		// Odd shape
		else if (objectType == 4)
		{
			gl.glBegin(GL2.GL_POLYGON);
			for (int i = 0; i < oddObject.length; i++)
			{
				gl.glVertex2d(oddObject[i].x, oddObject[i].y);
			}
			gl.glEnd();
		}
		// Point by default
		else
		{
			gl.glBegin(GL2.GL_POINTS);
			gl.glPointSize(5.0f);
			gl.glVertex2d(pointObject.x, pointObject.y);
			gl.glEnd();
		}
	}

	private void	drawCursor(GL2 gl)
	{
		if (cursor == null)
			return;

		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glColor3f(0.5f, 0.5f, 0.5f);

		for (int i=0; i<32; i++)
		{
			double	theta = (2.0 * Math.PI) * (i / 32.0);

			gl.glVertex2d(cursor.x + 0.05 * Math.cos(theta),
						  cursor.y + 0.05 * Math.sin(theta));
		}

		gl.glEnd();
	}

	private void	drawCursorCoordinates(GLAutoDrawable drawable)
	{
		if (cursor == null)
			return;

		String	sx = FORMAT.format(new Double(cursor.x));
		String	sy = FORMAT.format(new Double(cursor.y));
		String	s = "(" + sx + "," + sy + ")";

		renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
		renderer.setColor(1.0f, 1.0f, 0, 1.0f);
		renderer.draw(s, 2, 2);
		renderer.endRendering();
	}

	private void drawOddBounds(GL2 gl)
	{
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		for (int i = 0; i < oddBounds.length; i++)
		{
			gl.glVertex2d(oddBounds[i].x, oddBounds[i].y);
		}
		gl.glEnd();
	}

	// Checks to see if the current moving object is within the currently selected
	// bounds, and adjusts the vector of the moving object accordingly if it is not
	private void checkIfInBounds()
	{

	}
}

//******************************************************************************
