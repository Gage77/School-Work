//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Apr 26 20:30:38 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160418 [weaver]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.example;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.gl2.GLUT;

//******************************************************************************

/**
 * The <CODE>Wireframe</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Wireframe
	implements GLEventListener
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private int		w;				// Canvas width
	private int		h;				// Canvas height

	private int		m = 1;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);
		JFrame			frame = new JFrame("Wireframe");

		frame.setBounds(50, 50, 1024, 768);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		canvas.addGLEventListener(new Wireframe());

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		animator.start();
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getSurfaceWidth();
		h = drawable.getSurfaceHeight();
	}

	public void		dispose(GLAutoDrawable drawable)
	{
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

		// ****************************************
		// Zeroth step: Determine which part of the screen/window to draw into
		// ****************************************

		// Let JOGL take care of this (usually)
		//gl.glViewport(0, 0, w, h);

		// Use to adjust the frustum (clipped volume) relative to viewport
		float	aspect = (float)w/(float)h;	// Aspect ratio of viewport

		// ****************************************
		// First step: Position and orient the default camera
		// ****************************************

		gl.glMatrixMode(gl.GL_PROJECTION);
		gl.glLoadIdentity();

		// v1: parallel projection (scaled by >sqrt(3) so cube always fits)
		gl.glOrtho(-1.75 * aspect, 1.75 * aspect, -1.75, 1.75, 0.1, 100);

		// v2: perspective projection with 45 degree field-of-view
		// closest z shown is 0.1, farthest z shown is 10.0
		//glu.gluPerspective(45.0f, aspect, 0.1f, 10.0f);

		// ****************************************
		// Second step: Position and orient the actual camera
		// https://www.opengl.org/archives/resources/faq/technical/viewing.htm
		// ****************************************

		gl.glMatrixMode(gl.GL_MODELVIEW);
		gl.glLoadIdentity();

		// v1: camera at X=Y=Z=1.5, looking at origin
		glu.gluLookAt(1.5, 1.5, 1.5, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

		// v2: camera at X=Y=Z=3.0, looking at center of cube
		//glu.gluLookAt(3.0, 3.0, 3.0, 0.5, 0.5, 0.5, 0.0, 1.0, 0.0);

		// ****************************************
		// Third step: position and orient the scene
		// ****************************************

		// v1: rotate x, y, z at independent rates over time
		gl.glRotated((m / 5.0), (m / 3.0), (m / 2.0), 0);

		// v2: scale x, y, z so that cube pulses over time
		double	f = Math.cos(2.0 * Math.PI * (m % 120) / 120.0);

		//gl.glScaled(f * 0.1 + 1.0, f * 0.2 + 1.0, f * 0.4 + 1.0);

		// ****************************************
		// Fourth step: position and orient objects in the scene
		// ****************************************

		// This all happens later in render()
	}

	//**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

	private void	update(GLAutoDrawable drawable)
	{
		m++;
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);	// White background
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);			// Clear the buffer
		draw(gl);									// Draw
		gl.glFlush();								// Finish and display
	}

	//**********************************************************************
	// Private Methods (Scene)
	//**********************************************************************

	private void	draw(GL2 gl)
	{
		// Draw axes
		gl.glColor3d(0, 0, 0);
		drawAxis(gl, 0.5);
		gl.glPushMatrix();
		gl.glRotated(90.0, 0.0, 1.0, 0);
		drawAxis(gl, 0.5);
		gl.glRotated(-90.0, 1.0, 0.0, 0);
		drawAxis(gl, 0.5);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(0.5, 0.5, 0.5);
		GLUT.glutWireCube(1.0f);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(1.0, 1.0, 0.0);
		GLUT.glutWireSphere(0.25, 10, 8);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(1.0, 0.0, 1.0);
		GLUT.glutWireCone(0.2, 0.5, 10, 8);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(1.0, 1.0, 1.0);
		gl.glRotated((m / 2.0), (m / 3.0), (m / 5.0), 0);
		GLUT.glutWireTeapot(0.2);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(0.0, 1.0, 0.0);
		gl.glRotated(90.0, 1.0, 0.0, 0);
		GLUT.glutWireTorus(0.1, 0.3, 10, 10);
		////GLUT.glutWireTorus(0.1, 0.3, 5 + (m % 123), 5 + (m % 123));
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(1.0, 0.0, 0.0);
		gl.glScaled(0.15, 0.15, 0.15);
		GLUT.glutWireDodecahedron();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslated(0.0, 1.0, 1.0);
		GLUT.glutWireCube(0.25f);
		gl.glPopMatrix();

		GLUquadric	qobj = GLU.gluNewQuadric();

		GLU.gluQuadricDrawStyle(qobj, GLU.GLU_LINE);
		gl.glPushMatrix();
		gl.glTranslated(0.0, 0.0, 1.0);
		GLU.gluCylinder(qobj, 0.2, 0.2, 0.4, 8, 8);
		gl.glPopMatrix();
	}

	private void	drawAxis(GL2 gl, double length)
	{
		gl.glPushMatrix();

		gl.glBegin(GL.GL_LINES);
		gl.glVertex3d(0.0, 0.0, 0.0);
		gl.glVertex3d(0.0, 0.0, length);
		gl.glEnd();

		gl.glTranslated(0.0, 0.0, length - 0.2);
		GLUT.glutWireCone(0.04, 0.2, 12, 9);

		gl.glPopMatrix();
	}
}

//******************************************************************************
