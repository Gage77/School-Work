import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Lab #5
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * Each object of this class represents a GUI display window (MVC view).
 * </p>
 * @version 1.0
 *
 */
public class SeriesDisplayWindow extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = -7544472247240102278L;
	
	private JList<Episode> displayEpisodeList;
	private SeriesModel model;
	
	public SeriesDisplayWindow() {
		// intentionally empty, do not change
	}

	public void actionPerformed(ActionEvent e) 
	{
		displayEpisodeList.setListData(model.getEpisodeArray());
		pack();
	}
	
	public void setModel(SeriesModel model) 
	{
		this.model = model;
		
		if (model != null) 
		{
			model.addActionListener(this);
			setTitle("Episodes in " + model.getTitle() + " (" + model.getStartYear() + ")");
			add(new JLabel("Episode Title (Season Number, Episode Number) Year"), BorderLayout.NORTH);
			displayEpisodeList = new JList<Episode>(model.getEpisodeArray());
			add(displayEpisodeList, BorderLayout.CENTER);
			pack();
			setVisible(true);
		}
	}

	public SeriesModel getModel() 
	{
		return model;
	}
}