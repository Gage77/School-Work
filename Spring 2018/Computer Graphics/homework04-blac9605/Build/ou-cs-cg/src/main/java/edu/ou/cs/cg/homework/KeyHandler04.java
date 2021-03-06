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
 * The <CODE>KeyHandler04</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class KeyHandler04 extends KeyAdapter
{
	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private final View04	view;

	//**********************************************************************
	// Constructors and Finalizer
	//**********************************************************************

	public KeyHandler04(View04 view)
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
			case KeyEvent.VK_NUMPAD5:
				p.x = 0.0;	p.y = 0.0;	break;

			case KeyEvent.VK_NUMPAD4:
			case KeyEvent.VK_LEFT:
				p.x -= a;	p.y += 0.0;	break;

			case KeyEvent.VK_NUMPAD6:
			case KeyEvent.VK_RIGHT:
				p.x += a;	p.y += 0.0;	break;

			case KeyEvent.VK_NUMPAD2:
			case KeyEvent.VK_DOWN:
				p.x += 0.0;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD8:
			case KeyEvent.VK_UP:
				p.x += 0.0;	p.y += a;	break;

			case KeyEvent.VK_NUMPAD1:
				p.x -= a;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD7:
				p.x -= a;	p.y += a;	break;

			case KeyEvent.VK_NUMPAD3:
				p.x += a;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD9:
				p.x += a;	p.y += a;	break;

			case KeyEvent.VK_DELETE:
				view.clear();
				return;
		}

		view.setOrigin(p);
	}
}

//******************************************************************************
