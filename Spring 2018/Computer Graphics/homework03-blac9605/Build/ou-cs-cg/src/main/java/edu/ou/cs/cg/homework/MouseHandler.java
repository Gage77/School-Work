/************************************************
* Mouse input handler to handle events
* triggered by the mouse
************************************************/

/************************************************
* Import necessary packages
************************************************/
package edu.ou.cs.cg.homework;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/************************************************
* Meta information
*
* @author Hunter Black
* @version %I%, %G%
************************************************/
public final class MouseHandler extends MouseAdapter
{
  /************************************************
  * Private data
  ************************************************/
  private final View view;

  /************************************************
  * Constructor(s)
  ************************************************/
  public MouseHandler(View view)
  {
    this.view = view;

    Component	component = view.getComponent();

    component.addMouseListener(this);
    component.addMouseMotionListener(this);
    component.addMouseWheelListener(this);
  }

  /************************************************
  * Override Method(s) (MouseListener)
  ************************************************/

  /************************************************
  * Override Method(s) (MouseMotionListener)
  ************************************************/

  /************************************************
  * Override Method(s) (MouseWheelListener)
  ************************************************/

  /************************************************
  * Private Methods
  ************************************************/

}
