import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

//Creates a pie-chart. The created PieChart must be added to a visible JFrame or comprable frame to be viewed.

/**
 * Constructs a pie chart that displays acting, directing, and producing credits
 * for one media maker.
 *
 */
public class PieChart extends JComponent {
	/**
	 * Stores the height of the pie chart.
	 */
	private static final Integer CHART_HEIGHT = 300;
	/**
	 * Stores the width of the pie chart.
	 */
	private static final Integer CHART_WIDTH = 300;
	/**
	 * Stores the x-coordinate of the pie chart.
	 */
	private static final Integer CHART_X = 110;
	/**
	 * Stores the y-coordinate of the pie chart.
	 */
	private static final Integer CHART_Y = 80;
	/**
	 * Stores the MediaMaker object to be used.
	 */
	private MediaMaker obj;
	/**
	 * Stores a list of all the MediaMaker's credits.
	 */
	private ArrayList<Media> makerCredits;

	/**
	 * A constructor method.
	 * 
	 * @param obj
	 *            The MediaMaker object whose information will be used to draw
	 *            the pie chart.
	 */
	public PieChart(String name) {

		this.obj = getObject(name);
		// All of the credits for one MediaMaker are stored.This will be used in
		// the 'addSlice' mehtod.
		makerCredits = new ArrayList<Media>();
		makerCredits.addAll(obj.getCredits());
		;

	}

	/**
	 * Adds a Slice to the pie chart.
	 * 
	 * @param obj
	 *            The graphics obj to which the slice will be added.
	 * @param list
	 *            The list of all the credits for one MediaMaker in one
	 *            category.
	 * @param totalCredits
	 *            The total number of credits recieved for one MediaMaker.
	 * @param startPoint
	 *            The point at which the slice will begin.
	 * @return The point at which the previously drawn slice ended. It will be
	 *         used if another slice is added in the future.
	 */

	private Integer addSlice(Graphics2D obj, List<Media> list, Integer totalCredits, Integer startPoint) {

		Integer sliceSize = Math.round((list.size() * 360) / totalCredits);
		obj.fillArc(CHART_X, CHART_Y, CHART_WIDTH, CHART_HEIGHT, startPoint, sliceSize);

		return sliceSize;
	}

	/**
	 * Overwritten method, allows the piechart to be drawn.
	 */
	public void paint(Graphics g) {

		Graphics2D graphicsObj = (Graphics2D) g;
		setFont(new Font("Sanserif", Font.BOLD, 14));
		// start at 0 and sweep 360 degrees
		Integer total = makerCredits.size();
		int startPoint = 0;
		int sliceSize;

		if (obj.getMoviesActed().size() > 0) {
			graphicsObj.setColor(Color.BLUE);
			sliceSize = addSlice(graphicsObj, obj.getMoviesActed(), total, startPoint);
			startPoint += sliceSize;
			graphicsObj.drawString("Movies Acted: " + Math.round(sliceSize / 3.6) + "%", 420, 60);
		}
		// else startPoint stays at 0, and the slice is never added
		if (obj.getMoviesDirected().size() > 0) {
			graphicsObj.setColor(Color.GRAY);
			sliceSize = addSlice(graphicsObj, obj.getMoviesDirected(), total, startPoint);
			startPoint += sliceSize;

			graphicsObj.drawString("Movies Directed: " + Math.round(sliceSize / 3.6) + "%", 420, 80);

		}
		if (obj.getMoviesProduced().size() > 0) {
			graphicsObj.setColor(Color.GREEN);
			sliceSize = addSlice(graphicsObj, obj.getMoviesProduced(), total, startPoint);
			startPoint += sliceSize;

			graphicsObj.drawString("Movies Produced: " + Math.round(sliceSize / 3.6) + "%", 420, 100);

		}
		if (obj.getEpisodesActed().size() > 0) {
			graphicsObj.setColor(Color.MAGENTA);
			sliceSize = addSlice(graphicsObj, obj.getEpisodesActed(), total, startPoint);
			startPoint += sliceSize;
			graphicsObj.drawString("Episodes Acted: " + Math.round(sliceSize / 3.6) + "%", 420, 120);

		}
		if (obj.getEpisodesDirected().size() > 0) {
			graphicsObj.setColor(Color.ORANGE);
			sliceSize = addSlice(graphicsObj, obj.getEpisodesDirected(), total, startPoint);
			startPoint += sliceSize;

			graphicsObj.drawString("Episodes Directed: " + Math.round(sliceSize / 3.6) + "%", 420, 140);

		}
		if (obj.getEpisodesProduced().size() > 0) {
			graphicsObj.setColor(Color.PINK);
			sliceSize = addSlice(graphicsObj, obj.getEpisodesProduced(), total, startPoint);
			startPoint += sliceSize;

			graphicsObj.drawString("Episodes Produced: " + Math.round(sliceSize / 3.6) + "%", 420, 160);

		}

	}

}

