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
    Point2D.Double p = view.getOrigin();

    switch (e.getKeyCode())
    {
      /************************************************
      * Increase and decrease height of fences
      ************************************************/
      // Increase height of fence
      case KeyEvent.VK_PAGE_UP:
        System.out.println("Page up pressed");
        view.setFenceIncreaseFactor(1);
        break;
      // Decraese height of fence
      case KeyEvent.VK_PAGE_DOWN:
        System.out.println("Page down pressed");
        view.setFenceIncreaseFactor(-1);
        break;
      /************************************************
      * Toggles whether window shades are open or closed
      ************************************************/
      case KeyEvent.VK_W:
        System.out.println("W key pressed");
        view.setWindowShades();
        break;
      /************************************************
      * Changes number of panels on each side of kite,
      * but does not change the total angle (~100) for
      * each have
      ************************************************/
      // One panel
      case KeyEvent.VK_1:
        System.out.println("1 pressed");
        view.setNumKites(1);
        break;
      // Two panels
      case KeyEvent.VK_2:
        System.out.println("2 pressed");
        view.setNumKites(2);
        break;
      // Three panels
      case KeyEvent.VK_3:
        System.out.println("3 pressed");
        view.setNumKites(3);
        break;
      // Four panels
      case KeyEvent.VK_4:
        System.out.println("4 pressed");
        view.setNumKites(4);
        break;
      // Five panels
      case KeyEvent.VK_5:
        System.out.println("5 pressed");
        view.setNumKites(5);
        break;
      // Six panels
      case KeyEvent.VK_6:
        System.out.println("6 pressed");
        view.setNumKites(6);
        break;
      // Seven panels
      case KeyEvent.VK_7:
        System.out.println("7 pressed");
        view.setNumKites(7);
        break;
      // Eight panels
      case KeyEvent.VK_8:
        System.out.println("8 pressed");
        view.setNumKites(8);
        break;
      // Nine panels
      case KeyEvent.VK_9:
        System.out.println("9 pressed");
        view.setNumKites(9);
        break;
      /************************************************
      * Moves hopscotch squares towards and away from the
      * houses by 0.1 sidwalk slap, but always staying
      * inside the sidewalk
      ************************************************/
      // Moves hopscotch towards the houses
      case KeyEvent.VK_UP:
        System.out.println("Up arrow pressed");
        view.setHopscotchVerticalFactor(5);
        break;
      // Moves hopscotch away from the houses
      case KeyEvent.VK_DOWN:
        System.out.println("Down arrow pressed");
        view.setHopscotchVerticalFactor(-5);
        break;
      /************************************************
      * Moves hopscotch squares left and right by 1.0
      * sidewalk slab if <shift> is down, 0.1 sidwalk
      * slab otherwise
      ************************************************/
      // Move hopscotch left
      case KeyEvent.VK_LEFT:
        System.out.println("Left arrow pressed");
        if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0)
          view.setHopscotchHorizontalFactor(-80);
        else
          view.setHopscotchHorizontalFactor(-8);
        break;
      // Move hopscotch right
      case KeyEvent.VK_RIGHT:
        System.out.println("right arrow pressed");
        if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0)
          view.setHopscotchHorizontalFactor(80);
        else
          view.setHopscotchHorizontalFactor(8);
        break;
      /************************************************
      * Selects one of the stars. Starting with no star
      * selected, <tab> cycles through the stars one at
      * a time, then returns to no star selected. The
      * selected star is filled in orange instead of
      * yellow
      ************************************************/
      case KeyEvent.VK_TAB:
        System.out.println("Tab key pressed");
        view.setSelectedStar();
        break;
    }
  }

}
