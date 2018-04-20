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
		boolean shift = Utilities.isShiftDown(e);

		switch (e.getKeyCode())
		{
			case KeyEvent.VK_NUMPAD5:
				p.x = 0.0;	p.y = 0.0;	break;

			case KeyEvent.VK_NUMPAD4:
				p.x -= a;	p.y += 0.0;	break;

			case KeyEvent.VK_NUMPAD6:
				p.x += a;	p.y += 0.0;	break;

			case KeyEvent.VK_NUMPAD2:
				p.x += 0.0;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD8:
				p.x += 0.0;	p.y += a;	break;

			case KeyEvent.VK_NUMPAD1:
				p.x -= a;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD7:
				p.x -= a;	p.y += a;	break;

			case KeyEvent.VK_NUMPAD3:
				p.x += a;	p.y -= a;	break;

			case KeyEvent.VK_NUMPAD9:
				p.x += a;	p.y += a;	break;

			// Display next non-noded name
			case KeyEvent.VK_PERIOD:
				// Change what name is shown in corner
				if (shift) {
					System.out.println("> pressed");
					view.updateNetworkNameIndex(1);
				}
				// Change which node is selected
				else {
					System.out.println(". pressed");
					view.setSelectedNode(1);
				}
				break;

			// Display previous non-noded name
			case KeyEvent.VK_COMMA:
				// Change what name is shown in corner
				if (shift) {
					System.out.println("< pressed");
					view.updateNetworkNameIndex(-1);
				}
				// Change which node is selected
				else {
					System.out.println(", pressed");
					view.setSelectedNode(-1);
				}
				break;

			// Add the currently displayed name as a node
			case KeyEvent.VK_ENTER:
				System.out.println("Enter pressed");
				view.createNode();
				break;

			// Delete currently selected node
			case KeyEvent.VK_DELETE:
				System.out.println("Delete pressed");
				view.deleteSelectedNode();
				break;

			case KeyEvent.VK_UP:
				if (shift) {
					System.out.println("Shift up pressed");
					view.updateNodeScale(1);
				}
				else {
					System.out.println("up pressed");
					view.updateNodePos(1);
				}
				break;

			case KeyEvent.VK_DOWN:
				if (shift) {
					System.out.println("Shift down pressed");
					view.updateNodeScale(2);
				}
				else {
					System.out.println("down pressed");
					view.updateNodePos(2);
				}
				break;

			case KeyEvent.VK_RIGHT:
				if (shift) {
					System.out.println("Shift right pressed");
					view.updateNodeScale(1);
				}
				else {
					System.out.println("right pressed");
					view.updateNodePos(3);
				}
				break;

			case KeyEvent.VK_LEFT:
				if (shift) {
					System.out.println("Shift left pressed");
					view.updateNodeScale(2);
				}
				else {
					System.out.println("left pressed");
					view.updateNodePos(4);
				}
				break;

		}

		view.setOrigin(p);
	}
}

//******************************************************************************
