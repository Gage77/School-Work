// I�m using the * notation here for imports since you may use many classes from
// these packages
import  javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ExperimentWithGraphics extends JPanel
{
	private static final int SIZE = 300;
	public static void main(String[] args) 
	{
		// You may change the parameters in the code below, but proceed with extreme caution
		JFrame frame = new JFrame("Line");
		JPanel panel = new ExperimentWithGraphics();
		frame.setSize(240, 440);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setVisible(true);
	}
	
	// Do not change this  method name or parameters
	public void paintComponent(Graphics g)
	{
		// Do not change the next two lines of code
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// This is where your code should go
		Polygon potBase = new Polygon();
		potBase.addPoint(70,400);
		potBase.addPoint(170,400);
		potBase.addPoint(220,320);
		potBase.addPoint(20, 320);
		g2d.setColor(Color.RED);
		g2d.fill(potBase);
		
		Rectangle2D.Double potrim = new Rectangle2D.Double(20,300,200, 20);
		g2d.fill(potrim);
		
		g2d.setColor(Color.GREEN);
		Rectangle2D.Double stem = new Rectangle2D.Double(115,150, 10, 150);
		g2d.fill(stem);
		
		g2d.setColor(Color.YELLOW);
		Ellipse2D.Double petal = new Ellipse2D.Double(120,120, 15, 100);
		
		for (int i=0; i<24; ++i)
		{
			g2d.rotate(15*Math.PI/180.0, 120, 120);
			g2d.fill(petal);			
		}
		
		g2d.setColor(Color.ORANGE);
		Ellipse2D.Double center = new Ellipse2D.Double(65, 65, 110, 110);
		g2d.fill(center);
		
	}

}
