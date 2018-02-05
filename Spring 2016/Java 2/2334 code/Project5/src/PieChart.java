import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 * Constructs a pie chart that displays acting, directing, and producing credits
 * for one media maker.
 * This class implements and OUTSIDE LIBRARY (jcommon-1.0.23) and (jfreechart-1.0.19)
 */
public class PieChart extends JFrame 
{
	/** Default serial version UID for Serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the main constructor for PieChart objects, for the general case of MediaMakers
	 * 
	 * @param appTitle		Title of the pop-up window
	 * @param chartTitle	Title of the PieChart created
	 * @param nam			Number of Acting Movie credits
	 * @param nae			Number of Acting Episode credits
	 * @param ndm			Number of Directing Movie credits
	 * @param nde			Number of Directing Episode credits
	 * @param npm			Number of Producing Movie credits
	 * @param npe			Number of Producing Episode credits
	 */
	public PieChart(String appTitle, String chartTitle, int nam, int nae, int ndm, int nde, 
			int npm, int npe)
	{
		PieDataset dataset = createDataset(nam, nae, ndm, nde, npm, npe);
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		setContentPane(chartPanel);
	}
	
	/**
	 * This is an overloaded constructor for Actor, Director, and Producer objects
	 * 
	 * @param appTitle		Title of the pop-up window
	 * @param chartTitle	Title of the PieChart created
	 * @param nadpm			Number of Acting Movie credits
	 * @param nadpe			Number of Acting Episode credits
	 */
	public PieChart(String appTitle, String chartTitle, int nadpm, int nadpe)
	{
		PieDataset dataset = createDataset(nadpm, nadpe);
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		setContentPane(chartPanel);
	}
	
	/**
	 * This method creates the required PieDataset for a MediaMaker object
	 * 
	 * @param nam			Number of Acting Movie credits
	 * @param nae			Number of Acting Episode credits
	 * @param ndm			Number of Directing Movie credits
	 * @param nde			Number of Directing Episode credits
	 * @param npm			Number of Producing Movie credits
	 * @param npe			Number of Producing Episode credits
	 * 
	 * @return				Created PieDataset
	 */
	private PieDataset createDataset(int nam, int nae, int ndm, int nde, int npm, int npe) 
	{
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Acting Movie Credits", nam);
		result.setValue("Acting Episode Credits", nae);
		result.setValue("Directing Movie Credits", ndm);
		result.setValue("Directing Episode Credits", nde);
		result.setValue("Producing Movie Credits", npm);
		result.setValue("Producing Episode Credits", npe);
		
		return result;
	}
	
	/**
	 * This overloaded method creates the required PieDataset for Actor, 
	 * Director, and Producer objects
	 * 
	 * @param nadpm			Number of Acting Movie credits
	 * @param nadpe			Number of Acting Episode credits
	 * 
	 * @return				Created PieDataset
	 */
	private PieDataset createDataset(int nadpm, int nadpe)
	{
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Movie Credits", nadpm);
		result.setValue("Episode Credits", nadpe);
		
		return result;
	}
	
	private JFreeChart createChart(PieDataset dataset, String title)
	{
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D)chart.getPlot();
		plot.setStartAngle(0);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		
		return chart;
	}

}

