import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AddView extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JButton jbtAdd = new JButton("Add");
	
	JTextField jtfTitle;
	JLabel jlTitle;
	JTextField jtfSeriesTitle;
	JLabel jlSeriesTitle;
	JTextField jtfReleaseYear;
	JLabel jlReleaseYear;
	JTextField jtfReleaseFormat;
	JLabel jlReleaseFormat;
	
	JTextField jtfStartYear;
	JLabel jlStartYear;
	JTextField jtfEndYear;
	JLabel jlEndYear;
	
	JTextField jtfEpisodeNumber;
	JLabel jlEpisodeNumber;
	JTextField jtfSeriesNumber;
	JLabel jlSeriesNumber;
	JList<Series> seriesDisplay;
	JScrollPane seriesScroller;
	
	JTextField jtfName;
	JLabel jlName;
	
	JList<Movie> movieDisplay;
	JList<Episode> episodeDisplay;
	JScrollPane movieScroller;
	JScrollPane episodeScroller;
	
	/**
	 * This is the base constructor for AddView objects
	 */
	public AddView()
	{
		
	}
	
	/**
	 * This is the main constructor for AddView objects involving media
	 * (Movie, Series, Episode)
	 * 
	 * @param typeBeingAdded		int representing the type of object being added
	 */
	public AddView(int typeBeingAdded, ArrayList<Media> seriesToChoose)
	{
		//Movie add view
		if (typeBeingAdded == 1)
		{
			jtfTitle = new JTextField("", 20);
			jtfReleaseYear = new JTextField("", 20);
			jtfReleaseFormat = new JTextField("", 20);
			
			jlTitle = new JLabel("Title:", JLabel.RIGHT);
			jlReleaseYear = new JLabel("Release Year:", JLabel.RIGHT);
			jlReleaseFormat = new JLabel("Release Format:", JLabel.RIGHT);
			
			JPanel jplTitle = new JPanel(new GridLayout(1, 0, 5, 5));
			jplTitle.add(jlTitle);
			jplTitle.add(jtfTitle);
			
			JPanel jplReleaseYear = new JPanel(new GridLayout(1, 0, 5, 5));
			jplReleaseYear.add(jlReleaseYear);
			jplReleaseYear.add(jtfReleaseYear);
			
			JPanel jplReleaseFormat = new JPanel(new GridLayout(1, 0, 5, 5));
			jplReleaseFormat.add(jlReleaseFormat);
			jplReleaseFormat.add(jtfReleaseFormat);
			
			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtAdd);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			setTitle("Movie Add View");
			add(jplTitle);
			add(jplReleaseYear);
			add(jplReleaseFormat);
			add(jplButton);
			pack();
			setVisible(true);	
		}
		
		//Series add view
		else if (typeBeingAdded == 2)
		{
			jtfTitle = new JTextField("", 20);
			jtfStartYear = new JTextField("", 20);
			jtfEndYear = new JTextField("", 20);
			
			jlTitle = new JLabel("Title:", JLabel.RIGHT);
			jlStartYear = new JLabel("Start Year:", JLabel.RIGHT);
			jlEndYear = new JLabel("End Year:", JLabel.RIGHT);
			
			JPanel jplTitle = new JPanel(new GridLayout(1, 0, 5, 5));
			jplTitle.add(jlTitle);
			jplTitle.add(jtfTitle);
			
			JPanel jplStartYear = new JPanel(new GridLayout(1, 0, 5, 5));
			jplStartYear.add(jlStartYear);
			jplStartYear.add(jtfStartYear);
			
			JPanel jplEndYear = new JPanel(new GridLayout(1, 0, 5, 5));
			jplEndYear.add(jlEndYear);
			jplEndYear.add(jtfEndYear);

			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtAdd);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			setTitle("Series Add View");
			add(jplTitle);
			add(jplStartYear);
			add(jplEndYear);
			add(jplButton);
			pack();
			setVisible(true);
		}
		
		//Episode add view
		else if (typeBeingAdded == 3)
		{
			jtfTitle = new JTextField("", 20);
			jtfReleaseYear = new JTextField("", 20);
			jtfEpisodeNumber = new JTextField("", 20);
			
			jlTitle = new JLabel("Episode Title:", JLabel.RIGHT);
			jlReleaseYear = new JLabel("Release Year:", JLabel.RIGHT);
			jlEpisodeNumber = new JLabel("Episode Number:", JLabel.RIGHT);
			
			if (!seriesToChoose.isEmpty())
			{
				DefaultListModel<Series> seriesDisplayModel = new DefaultListModel<Series>();
				for (int i = 0; i < seriesToChoose.size(); ++i)
				{
					seriesDisplayModel.addElement((Series)seriesToChoose.get(i));
				}
				seriesDisplay = new JList<Series>(seriesDisplayModel);
				seriesDisplay.setLayoutOrientation(JList.VERTICAL);
				seriesDisplay.setVisibleRowCount(-1);
				seriesDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				seriesScroller = new JScrollPane(seriesDisplay);
			}
			
			JPanel jplTitle = new JPanel(new GridLayout(1, 0, 5, 5));
			jplTitle.add(jlTitle);
			jplTitle.add(jtfTitle);
			
			JPanel jplReleaseYear = new JPanel(new GridLayout(1, 0, 5, 5));
			jplReleaseYear.add(jlReleaseYear);
			jplReleaseYear.add(jtfReleaseYear);
			
			JPanel jplEpisodeNumber = new JPanel(new GridLayout(1, 0, 5, 5));
			jplEpisodeNumber.add(jlEpisodeNumber);
			jplEpisodeNumber.add(jtfEpisodeNumber);

			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtAdd);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			setTitle("Episode Add View");
			add(jplTitle);
			add(jplReleaseYear);
			add(jplEpisodeNumber);
			if (!seriesToChoose.isEmpty())
				add(seriesScroller);
			add(jplButton);
			pack();
			setVisible(true);
		}
	}
	
	
	/**
	 * This is the main constructor for AddView objects involving MediaMakers
	 * (Actor, Director, Producer)
	 * 
	 * @param type					int representing the type of MediaMaker object being added
	 * @param moviesToPick			List of movies to add to MediaMaker's media credits
	 * @param episodesToPick		Episodes to add to MediaMaker's media credits
	 */
	public AddView(int type, ArrayList<Media> moviesToPick, ArrayList<Media> episodesToPick) {
		jtfName = new JTextField("", 20);
		
		jlName = new JLabel("Name:", JLabel.RIGHT);
		
		if (moviesToPick != null)
		{
			DefaultListModel<Movie> movieDisplayModel = new DefaultListModel<Movie>();
			for (int i = 0; i < moviesToPick.size(); ++i)
			{
				movieDisplayModel.addElement((Movie)moviesToPick.get(i));
			}
			movieDisplay = new JList<Movie>(movieDisplayModel);
			movieDisplay.setLayoutOrientation(JList.VERTICAL);
			movieDisplay.setVisibleRowCount(-1);
			movieScroller = new JScrollPane(movieDisplay);
		}
		
		if (episodesToPick != null)
		{
			DefaultListModel<Episode> episodeDisplayModel = new DefaultListModel<Episode>();
			for (int i = 0; i < episodesToPick.size(); ++i)
			{
				episodeDisplayModel.addElement((Episode)episodesToPick.get(i));
			}
			episodeDisplay = new JList<Episode>(episodeDisplayModel);
			episodeDisplay.setLayoutOrientation(JList.VERTICAL);
			episodeDisplay.setVisibleRowCount(-1);
			episodeScroller = new JScrollPane(episodeDisplay);
		}
		
		JPanel jplName = new JPanel(new GridLayout(1, 0, 5, 5));
		jplName.add(jlName);
		jplName.add(jtfName);
		
		JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
		jplButton.add(jbtAdd);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(0, 1, 5, 5));
		setTitle("Media Maker Add View");
		add(jplName);
		if (moviesToPick != null)
			add(movieScroller);
		if (episodesToPick != null)
			add(episodeScroller);
		add(jplButton);
		pack();
		setVisible(true);
	}
	
	public void addAddButtonListener(ActionListener addAddListener) {
		jbtAdd.addActionListener(addAddListener);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
