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

import java.lang.*;
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

	private String[] networkNames = Network.getAllNames();	// Fetch all network names
	private ArrayList<String> nodeNames = new ArrayList<String>();	// All network names currently displayed as nodes
	private int networkNameIndex = 0;	// Index of the currently displayed network name
	private int numNames;
	private String noNewNamesMessage = "---- No new names ----";	// Message to display when all names have been created as nodes

	private ArrayList<Node> allNodes = new ArrayList<Node>();	// Arraylist of all Nodes
	private int nodeIndex = 0;	// Index of the currently selected node
	private int numNodes = 0;	// Total number of nodes active in the network
	private Node node;

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
		//animator.start();

		// Initialize interaction
		keyHandler = new KeyHandler(this);
		mouseHandler = new MouseHandler(this);

		numNames = networkNames.length;
		System.out.println("Total number of people potentially in the network: " + numNames);
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

	// Change the index of the currently displayed name in the network
	public void updateNetworkNameIndex(int dif)
	{
		if (numNames > 0) {
			int indexUp = networkNameIndex + 1;
			int indexDown = networkNameIndex -1;

			// Moving forward in the array of names
			if (dif > 0) {
				// Check to see if going to the next index would go out of bounds
				if (indexUp != networkNames.length) {
					networkNameIndex++;
				}
				else {
					networkNameIndex = 0;
				}
			}

			// Moving backwards in the array of names
			else {
				// Check to see if going to the next index would go out of bounds
				if (indexDown != -1) {
					networkNameIndex--;
				}
				else {
					networkNameIndex = networkNames.length - 1;
				}
			}
		}
	}

	public void createNode()
	{
		if (numNames > 0 && !(networkNames[networkNameIndex].equals("XXXXX"))) {
			nodeNames.add(networkNames[networkNameIndex]);

			Color c = Network.getColor(networkNames[networkNameIndex]);
			int s = Network.getSides(networkNames[networkNameIndex]);

			node = new Node(networkNames[networkNameIndex], s, 0.1, c);

			if (numNodes == 0) {
				node.setSelected(true);
			}
			else {
				node.setSelected(false);
			}

			// Change current network name index to display next name that is not a node
			networkNames[networkNameIndex] = "XXXXX";
			updateNetworkNameIndex(1);

			// Add new node to global array list of nodes
			allNodes.add(node);

			// Change number of names and number of nodes
			numNames--;
			numNodes++;

			for (int i = 0; i < allNodes.size(); i++) {
				System.out.println(allNodes.get(i).getName() + " SELECTED: " + allNodes.get(i).isSelected());
			}
		}
		else {
			System.out.println("No new names to add / cannot add name already in network");
		}
	}

	public void setSelectedNode(int dif)
	{
		System.out.println("change selected node");

		if (dif == 200)
		{
			nodeIndex = 0;
		}

		if (numNodes > 0) {

			// change forward
			if (dif > 0) {
				int difup = nodeIndex + 1;
				if (difup <= numNodes - 1) {
					allNodes.get(nodeIndex).setSelected(false);
					nodeIndex = difup;
					allNodes.get(nodeIndex).setSelected(true);
				}
				else {
					allNodes.get(nodeIndex).setSelected(false);
					nodeIndex = 0;
					allNodes.get(nodeIndex).setSelected(true);
				}
			}

			// change backward
			else {
				int difdown = nodeIndex -1;
				if (difdown >= 0) {
					allNodes.get(nodeIndex).setSelected(false);
					nodeIndex = difdown;
					allNodes.get(nodeIndex).setSelected(true);
				}
				else {
					allNodes.get(nodeIndex).setSelected(false);
					nodeIndex = numNodes - 1;
					allNodes.get(nodeIndex).setSelected(true);
				}
			}
		}
		else {
			System.out.println("No nodes in the network");
		}
	}

	public void deleteSelectedNode()
	{
		System.out.println("Delete node entered");
		if (numNodes == 0) {
			System.out.println("No nodes to delete");
		}
		else {
			allNodes.remove(nodeIndex);
			numNodes--;
			setSelectedNode(200);
		}
	}

	public void updateNodePos(int dir)
	{
		System.out.println("update node pos entered");

		if (numNodes != 0)
		{
			double curX = allNodes.get(nodeIndex).getPosX();
			double curY = allNodes.get(nodeIndex).getPosY();
			double offset = allNodes.get(nodeIndex).getRadius() * 0.1;
			// Move up
			if (dir == 1)
			{
				allNodes.get(nodeIndex).setPosY(curY + offset);
			}
			// Move down
			else if (dir == 2)
			{
				allNodes.get(nodeIndex).setPosY(curY - offset);
			}
			// Move right
			else if (dir == 3)
			{
				allNodes.get(nodeIndex).setPosX(curX + offset);
			}
			// Move left
			else if (dir == 4)
			{
				allNodes.get(nodeIndex).setPosX(curX - offset);
			}
		}
		else
		{
			System.out.println("No nodes selected");
		}
	}

	public void updateNodeScale(int dir)
	{
		System.out.println("update node scale entered");

		if (numNodes != 0)
		{
			// Increase size
			if (dir == 1)
			{
				double offset = allNodes.get(nodeIndex).getRadius() * 1.1;
				allNodes.get(nodeIndex).setRadius(offset);
			}
			// Decrease size
			else if (dir == 2)
			{
				double offset = allNodes.get(nodeIndex).getRadius() * 0.9;
				allNodes.get(nodeIndex).setRadius(offset);
			}
		}
		else
		{
			System.out.println("No nodes selected");
		}
	}

	public void checkAndSelectNode(Point2D.Double v)
	{
		// Check to see if nodes exist to select
		if (numNodes != 0)
		{
			Node testerNode;
			double curX;
			double curY;
			double curR;
			double res1;
			double res2;
			// Run through all nodes and check their pos in relation to mouse
			for (int i = 0; i < allNodes.size(); i++)
			{
				testerNode = allNodes.get(i);
				curX = testerNode.getPosX();
				curY = testerNode.getPosY();
				curR = testerNode.getRadius();

				res1 = Math.sqrt(Math.pow((v.x - curX), 2) + Math.pow((v.y - curY), 2));
				res2 = Math.sqrt(Math.pow((curR - curX), 2) + Math.pow((curR - curY), 2));

				if (res1 <= res2)
				{
					System.out.println("mouse clicked inside node");
				}
			}
		}
	}

	//**********************************************************************
	// Public Methods
	//**********************************************************************

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
		counter++;								// Counters are useful, right?
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer
		drawBounds(gl);							// Unit bounding box
		drawAxes(gl);							// X and Y axes
		drawCursor(gl);							// Crosshairs at mouse location
		drawCursorCoordinates(drawable);		// Draw some text
		drawCurrentNetworkName(drawable);	// Draw the current network name that is not displayed as a node
		drawNodes(gl);	// Draw all current nodes
		drawPolyline(gl);						// Draw the user's sketch
	}

	//**********************************************************************
	// Private Methods (Scene)
	//**********************************************************************

	private void	drawBounds(GL2 gl)
	{
		gl.glColor3f(0.1f, 0.1f, 0.1f);
		gl.glBegin(GL.GL_LINE_LOOP);

		gl.glVertex2d(1.0, 1.0);
		gl.glVertex2d(-1.0, 1.0);
		gl.glVertex2d(-1.0, -1.0);
		gl.glVertex2d(1.0, -1.0);

		gl.glEnd();
	}

	private void	drawAxes(GL2 gl)
	{
		gl.glBegin(GL.GL_LINES);

		gl.glColor3f(0.25f, 0.25f, 0.25f);
		gl.glVertex2d(-10.0, 0.0);
		gl.glVertex2d(10.0, 0.0);

		gl.glVertex2d(0.0, -10.0);
		gl.glVertex2d(0.0, 10.0);

		gl.glEnd();
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
		renderer.draw(s, 5, drawable.getHeight() - 15);
		renderer.endRendering();
	}

	private void drawCurrentNetworkName(GLAutoDrawable drawable)
	{
		String currentName = networkNames[networkNameIndex];

		renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
		renderer.setColor(1.0f, 1.0f, 0.0f, 1.0f);
		if (numNames > 0) {
			renderer.draw(currentName, 5, 5);
		}
		else {
			renderer.draw(noNewNamesMessage, 5, 5);
		}
		renderer.endRendering();
	}

	private void drawNodes(GL2 gl)
	{
		if (numNodes > 0) {
			for (int i = 0; i < numNodes; i++) {
				drawNode(gl, allNodes.get(i));
			}
		}
	}

	private void	drawPolyline(GL2 gl)
	{
		gl.glColor3f(1.0f, 0.0f, 0.0f);

		for (Point2D.Double p : points)
		{
			gl.glBegin(GL2.GL_POLYGON);

			gl.glVertex2d(p.x - 0.01, p.y - 0.01);
			gl.glVertex2d(p.x - 0.01, p.y + 0.01);
			gl.glVertex2d(p.x + 0.01, p.y + 0.01);
			gl.glVertex2d(p.x + 0.01, p.y - 0.01);

			gl.glEnd();
		}

		gl.glColor3f(1.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINE_STRIP);

		for (Point2D.Double p : points)
			gl.glVertex2d(p.x, p.y);

		gl.glEnd();
	}

	private void drawNode(GL2 gl, Node node)
	{
		// Grab necessary variables from node
		double sides = (double)node.getSides();
		double r = (double)node.getRadius();
		double xoffset = node.getPosX();
		double yoffset = node.getPosY();
		Color c = node.getColor();

		// Get float values of RGB from Java color
		float cr = (float)c.getRed() / 255;
		float cg = (float)c.getGreen() / 255;
		float cb = (float)c.getBlue() / 255;

		// Draw the polygon
		gl.glBegin(GL2.GL_POLYGON);
		gl.glColor3f(cr, cg, cb);
		for (double i = 1.0; i < sides + 1; i++) {
			gl.glVertex2d(((r * Math.cos(2*Math.PI*(i/sides))) + xoffset), ((r * Math.sin(2*Math.PI*(i/sides))) + yoffset));
		}
		gl.glEnd();

		// Draw the outline of the polygon
		if (node.isSelected()) {
			gl.glColor3f(1.0f, 1.0f, 1.0f);
			gl.glLineWidth(3.0f);
		}
		else {
			gl.glColor3f(0.7f, 0.7f, 0.7f);
			gl.glLineWidth(1.0f);
		}
		gl.glBegin(GL2.GL_LINE_LOOP);
		for (double i = 1.0; i < sides + 1; i++) {
			gl.glVertex2d(((r * Math.cos(2*Math.PI*(i/sides))) + xoffset), ((r * Math.sin(2*Math.PI*(i/sides))) + yoffset));
		}
		gl.glEnd();
		gl.glLineWidth(1.0f);
	}
}

//******************************************************************************
