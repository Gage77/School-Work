//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Mon Feb 29 23:46:15 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160225 [weaver]:	Original file.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.cg.interaction;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

//******************************************************************************

/**
 * The <CODE>MouseHandler</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class MouseHandler extends MouseAdapter
{
	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private final View	view;
	private Point2D.Double currentOrigin;
	private Point2D.Double currentMouse;
	private Point2D.Double oldMouse;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public MouseHandler(View view)
	{
		this.view = view;

		Component	component = view.getComponent();

		component.addMouseListener(this);
		component.addMouseMotionListener(this);
		component.addMouseWheelListener(this);
	}

	//**********************************************************************
	// Override Methods (MouseListener)
	//**********************************************************************

	public void		mouseClicked(MouseEvent e)
	{
		Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());

		if (Utilities.isShiftDown(e))
			view.setOrigin(v);
		else
			view.add(v);
	}

	public void		mouseEntered(MouseEvent e)
	{
		Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());

		view.setCursor(v);
	}

	public void		mouseExited(MouseEvent e)
	{
		view.setCursor(null);
	}

	// Record starting location of the origin and the mouse cursor
	public void		mousePressed(MouseEvent e)
	{
		oldMouse = calcCoordinatesInView(e.getX(), e.getY());
		currentOrigin = view.getOrigin();
	}

	// Stop moving the origin
	public void		mouseReleased(MouseEvent e)
	{
		view.setOrigin(currentMouse);
	}

	//**********************************************************************
	// Override Methods (MouseMotionListener)
	//**********************************************************************

	// Update the view origin
	public void		mouseDragged(MouseEvent e)
	{
		//Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());
		//
		// view.add(v);
		// view.setCursor(v);

		currentMouse = calcCoordinatesInView(e.getX(), e.getY());
		Point2D.Double moveMousePoint = new Point2D.Double
		//view.moveCamera(currentMouse);
	}

	public void		mouseMoved(MouseEvent e)
	{
		Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());
	}

	//**********************************************************************
	// Override Methods (MouseWheelListener)
	//**********************************************************************

	public void		mouseWheelMoved(MouseWheelEvent e)
	{
	}

	//**********************************************************************
	// Private Methods
	//**********************************************************************

	private Point2D.Double	calcCoordinatesInView(int sx, int sy)
	{
		int				w = view.getWidth();
		int				h = view.getHeight();
		Point2D.Double	p = view.getOrigin();
		double			vx = p.x + (sx * 2.0) / w - 1.0;
		double			vy = p.y - (sy * 2.0) / h + 1.0;

		return new Point2D.Double(vx, vy);
	}
}

//******************************************************************************
