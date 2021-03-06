//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Mon Feb 29 23:36:04 2016 by Chris Weaver
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
 * The <CODE>KeyHandler</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class KeyHandler extends KeyAdapter
{
	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private final View	view;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public KeyHandler(View view)
	{
		this.view = view;

		Component	component = view.getComponent();

		component.addKeyListener(this);
	}

	//**********************************************************************
	// Override Methods (KeyListener)
	//**********************************************************************

	public void		keyPressed(KeyEvent e)
	{
		Point2D.Double	p = view.getOrigin();
		double			a = (Utilities.isShiftDown(e) ? 0.01 : 0.1);

		switch (e.getKeyCode())
		{
			case KeyEvent.VK_DELETE:
				view.clear();
				return;

			case KeyEvent.VK_NUMPAD1:
			case KeyEvent.VK_1:
				view.setBoundType(1); break;

			case KeyEvent.VK_NUMPAD2:
			case KeyEvent.VK_2:
				view.setBoundType(2); break;

			case KeyEvent.VK_NUMPAD3:
			case KeyEvent.VK_3:
				view.setBoundType(3); break;

			case KeyEvent.VK_NUMPAD4:
			case KeyEvent.VK_4:
				view.setBoundType(4); break;

			case KeyEvent.VK_NUMPAD6:
			case KeyEvent.VK_6:
				view.setMovingObject(1); break;

			case KeyEvent.VK_NUMPAD7:
			case KeyEvent.VK_7:
				view.setMovingObject(2); break;

			case KeyEvent.VK_NUMPAD8:
			case KeyEvent.VK_8:
				view.setMovingObject(3); break;

			case KeyEvent.VK_NUMPAD9:
			case KeyEvent.VK_9:
				view.setMovingObject(4); break;

			case KeyEvent.VK_RIGHT:
				view.adjustObjectSpeed(1.1); break;

			case KeyEvent.VK_LEFT:
				view.adjustObjectSpeed(0.9); break;

			case KeyEvent.VK_UP:
				view.adjustObjectSize(1.1); break;

			case KeyEvent.VK_DOWN:
				view.adjustObjectSize(0.9); break;
		}

		view.setOrigin(p);
	}
}

//******************************************************************************
