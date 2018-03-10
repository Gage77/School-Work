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
  *
  * If mouse is clicked, move currently selected star
  * to the point, but only if the click point is above
  * the horizon
  ************************************************/
  // Move selected star to mouse location if location is above
  // horizon
  public void mouseClicked(MouseEvent e)
  {
    Point2D.Double v = calcCoordinatesInView(e.getX(), e.getY());
    System.out.println("Mouse clicked");
    view.setSelectedStarLocation(v);
  }

  public void mouseEntered(MouseEvent e)
  {
    System.out.println("Mouse entered");

    Point2D.Double v = calcCoordinatesInView(e.getX(), e.getY());

    view.setCursor(v);
  }

  // Set mouse cursor position to null
  public void mouseExited(MouseEvent e)
  {
    System.out.println("Mouse exited");
    view.setCursor(null);
  }

  public void mousePressed(MouseEvent e)
  {
    System.out.println("Mouse pressed");
  }

  public void mouseReleased(MouseEvent e)
  {
    System.out.println("Mouse released");
  }
  /************************************************
  * Override Method(s) (MouseMotionListener)
  *
  * Dragging the mouse draws a polyline from the top of
  * the fence to the center of the kite. Mouse dragging
  * events proceed like this:
  *     <press>
  *     <drag>
  *     ...
  *     <drag>
  *     <release>
  *
  * When there's a <press>, add a point to the end of the
  * polyline from the top of the fence to the press point.
  * For each subsequent <drag>, add a point to the end of
  * the polyline. Draw the kite centered at the final point
  * in the final point in the line. Draw the kite translucently
  * during a drag and normally afterwards.
  ************************************************/
  public void mouseDragged(MouseEvent e)
  {
    Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());

    view.add(v);
    view.setCursor(v);
  }

  // Find and set the new cursor position after it has moved
  public void mouseMoved(MouseEvent e)
  {
    Point2D.Double	v = calcCoordinatesInView(e.getX(), e.getY());

		view.setCursor(v);
  }

  /************************************************
  * Override Method(s) (MouseWheelListener)
  ************************************************/

  public void mouseWheelMoved(MouseWheelEvent e)
  {

  }

  /************************************************
  * Private Methods
  ************************************************/
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
