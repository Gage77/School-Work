/************************************************
* Keyboard input handler to handle events
* triggered by the keyboard presses
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
public final class KeyHandler extends KeyAdapter
{
  /************************************************
  * Private data
  ************************************************/
  private final View view;

  /************************************************
  * Constructor(s)
  ************************************************/
  public KeyHandler (View view)
  {
    this.view = view;

    Component component = view.getComponent();

    component.addKeyListener(this);
  }
  /************************************************
  * Override Method(s) (KeyListener)
  ************************************************/

  public void keyPressed(KeyEvent e)
  {

  }

}
