import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Project12 extends JPanel
{
	private static final int SIZE = 400;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Line");
		JPanel panel = new Project12();
		frame.setSize(SIZE, SIZE);
		frame.getContentPane().add(panel,  BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//Change this stuff
		//Going to try to make a deadpool face because ya
		
		//Outline of head
		Ellipse2D.Double blackback = new Ellipse2D.Double(0, 0, SIZE, SIZE);
		g2d.setColor(Color.RED);
		g2d.fill(blackback);
		g2d.draw(blackback);
		
		//Red main face
		Ellipse2D.Double background = new Ellipse2D.Double(0, 0, SIZE, SIZE);
		g2d.scale(.9, .9);
		g2d.translate(blackback.getWidth() / 17.5, blackback.getHeight() / 17.5);
		g2d.setColor(Color.BLACK);
		g2d.fill(background);
		g2d.draw(background);
		
		//Left eye
		Polygon leftEye = new Polygon();
		leftEye.addPoint(100, 100);
		leftEye.addPoint(150, 100);
		leftEye.addPoint(150, 120);
		leftEye.addPoint(100, 120);
		g2d.setColor(Color.WHITE);
		g2d.fill(leftEye);
		g2d.draw(leftEye);
		
		//right eye
		Polygon rightEye = new Polygon();
		rightEye.addPoint(250, 100);
		rightEye.addPoint(300, 100);
		rightEye.addPoint(300, 120);
		rightEye.addPoint(250, 120);
		g2d.setColor(Color.WHITE);
		g2d.fill(rightEye);
		g2d.draw(rightEye);
		
		//red line down the center
		Rectangle centerLine = new Rectangle(190, 1, 25, SIZE);
		g2d.setColor(Color.RED);
		g2d.fill(centerLine);
		g2d.draw(centerLine);
		//print out coordinates of rectangle
		String coords = centerLine.toString();
		System.out.println(coords);
	}

}
