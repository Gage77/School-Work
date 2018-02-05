import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	public PieChart(String appTitle, String chartTitle)
	{
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset, chartTitle);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		setContentPane(chartPanel);
	}
	
	private PieDataset createDataset() 
	{
		DefaultPieDataset result = new DefaultPieDataset();
		result.setValue("Windows", 150);
		result.setValue("Linux", 73);
		result.setValue("Mac", 97);
		result.setValue("OS", 0);
		
		return result;
	}
	
	private JFreeChart createChart(PieDataset dataset, String title)
	{
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		
		return chart;
	}
}