import java.awt.GridLayout;
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

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JButton jbtCommenceEdit = new JButton("Commence Edit");
	JButton jbtEditMM = new JButton("Commence Edit (Selected media will be deleted)");
	
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
	
	JTextField jtfName;
	JLabel jlName;
	
	JList<Media> displayCredits;
	JScrollPane displayScroller;
	
	public EditView() {
		
	}
	
	public EditView(int typeBeingEdited, Media selectedObject) {
		
		//Movie
		if (typeBeingEdited == 1)
		{
			Movie movie = (Movie)selectedObject;
			
			jtfTitle = new JTextField(movie.getTitle(), 20);
			jtfReleaseYear = new JTextField(movie.getReleaseYear().toString(), 20);
			jtfReleaseFormat = new JTextField(movie.getVenue(), 20);
			
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
			jplButton.add(jbtCommenceEdit);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplTitle);
			add(jplReleaseYear);
			add(jplReleaseFormat);
			add(jplButton);
			pack();
			setVisible(true);	
		}
		//Series
		else if (typeBeingEdited == 2)
		{
			Series series = (Series)selectedObject;
			
			jtfTitle = new JTextField(series.getTitle(), 20);
			jtfStartYear = new JTextField(series.getReleaseYear(), 20);
			jtfEndYear = new JTextField(series.getYearRange(), 20);
			
			jlTitle = new JLabel("Title:", JLabel.RIGHT);
			jlStartYear = new JLabel("Start Year:", JLabel.RIGHT);
			jlEndYear = new JLabel("Year Range:", JLabel.RIGHT);
			
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
			jplButton.add(jbtCommenceEdit);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplTitle);
			add(jplStartYear);
			add(jplEndYear);
			add(jplButton);
			pack();
			setVisible(true);
		}
		//Episode
		else if (typeBeingEdited == 3)
		{
			Episode episode = (Episode)selectedObject;
			
			jtfTitle = new JTextField(episode.getTitle(), 20);
			jtfSeriesTitle = new JTextField(episode.getSeriesName(), 20);
			jtfReleaseYear = new JTextField(episode.getReleaseYear().toString(), 20);
			jtfEpisodeNumber = new JTextField(episode.getEpisodeNumber().toString(), 20);
			
			jlTitle = new JLabel("Episode Title:", JLabel.RIGHT);
			jlSeriesTitle = new JLabel("Series Title:", JLabel.RIGHT);
			jlReleaseYear = new JLabel("Release Year:", JLabel.RIGHT);
			jlEpisodeNumber = new JLabel("Episode Number:", JLabel.RIGHT);
			
			JPanel jplTitle = new JPanel(new GridLayout(1, 0, 5, 5));
			jplTitle.add(jlTitle);
			jplTitle.add(jtfTitle);
			
			JPanel jplSeriesTitle = new JPanel(new GridLayout(1, 0, 5, 5));
			jplSeriesTitle.add(jlSeriesTitle);
			jplSeriesTitle.add(jtfSeriesTitle);
			
			JPanel jplReleaseYear = new JPanel(new GridLayout(1, 0, 5, 5));
			jplReleaseYear.add(jlReleaseYear);
			jplReleaseYear.add(jtfReleaseYear);
			
			JPanel jplEpisodeNumber = new JPanel(new GridLayout(1, 0, 5, 5));
			jplEpisodeNumber.add(jlEpisodeNumber);
			jplEpisodeNumber.add(jtfEpisodeNumber);

			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtCommenceEdit);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplTitle);
			add(jplSeriesTitle);
			add(jplReleaseYear);
			add(jplEpisodeNumber);
			add(jplButton);
			pack();
			setVisible(true);
		}
	}
	
	public EditView(int type, MediaMaker mediaMaker) {
		
		//Actor
		if (type == 1)
		{
			Actor actor = (Actor) mediaMaker;
			ArrayList<Media> credits = new ArrayList<Media>();
			credits.addAll(actor.getMovieCredits());
			credits.addAll(actor.getEpisodeCredits());
			
			if (!credits.isEmpty())
			{
				DefaultListModel<Media> creditsDisplay = new DefaultListModel<Media>();
				for (int i = 0; i < credits.size(); ++i)
				{
					creditsDisplay.addElement(credits.get(i));
				}
				displayCredits = new JList<Media>(creditsDisplay);
				displayCredits.setLayoutOrientation(JList.VERTICAL);
				displayCredits.setVisibleRowCount(-1);
				displayScroller = new JScrollPane(displayCredits);
			}
			
			jtfName = new JTextField(mediaMaker.getName(), 20);
			
			jlName = new JLabel("Name: ", JLabel.RIGHT);
			
			JPanel jplName = new JPanel(new GridLayout(1, 0, 5, 5));
			jplName.add(jlName);
			jplName.add(jtfName);
			
			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtEditMM);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplName);
			if (!credits.isEmpty())
				add(displayScroller);
			add(jplButton);
			pack();
			setVisible(true);
		}
		//Director
		else if (type == 2)
		{
			Director director = (Director) mediaMaker;
			ArrayList<Media> credits = new ArrayList<Media>();
			credits.addAll(director.getMovieCredits());
			credits.addAll(director.getEpisodeCredits());
			
			if (!credits.isEmpty())
			{
				DefaultListModel<Media> creditsDisplay = new DefaultListModel<Media>();
				for (int i = 0; i < credits.size(); ++i)
				{
					creditsDisplay.addElement(credits.get(i));
				}
				displayCredits = new JList<Media>(creditsDisplay);
				displayCredits.setLayoutOrientation(JList.VERTICAL);
				displayCredits.setVisibleRowCount(-1);
				displayScroller = new JScrollPane(displayCredits);
			}
						
			jtfName = new JTextField(mediaMaker.getName(), 20);
			
			jlName = new JLabel("Name: ", JLabel.RIGHT);
			
			JPanel jplName = new JPanel(new GridLayout(1, 0, 5, 5));
			jplName.add(jlName);
			jplName.add(jtfName);
			
			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtEditMM);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplName);
			if (!credits.isEmpty())
				add(displayScroller);
			add(jplButton);
			pack();
			setVisible(true);
		}
		//Producer
		else if (type ==3)
		{
			Producer producer = (Producer) mediaMaker;
			ArrayList<Media> credits = new ArrayList<Media>();
			credits.addAll(producer.getMovieCredits());
			credits.addAll(producer.getEpisodeCredits());
			
			if (!credits.isEmpty())
			{
				DefaultListModel<Media> creditsDisplay = new DefaultListModel<Media>();
				for (int i = 0; i < credits.size(); ++i)
				{
					creditsDisplay.addElement(credits.get(i));
				}
				displayCredits = new JList<Media>(creditsDisplay);
				displayCredits.setLayoutOrientation(JList.VERTICAL);
				displayCredits.setVisibleRowCount(-1);
				displayScroller = new JScrollPane(displayCredits);
			}
						
			jtfName = new JTextField(mediaMaker.getName(), 20);
			
			jlName = new JLabel("Name: ", JLabel.RIGHT);
			
			JPanel jplName = new JPanel(new GridLayout(1, 0, 5, 5));
			jplName.add(jlName);
			jplName.add(jtfName);
			
			JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
			jplButton.add(jbtEditMM);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new GridLayout(0, 1, 5, 5));
			add(jplName);
			if (!credits.isEmpty())
				add(displayScroller);
			add(jplButton);
			pack();
			setVisible(true);
		}
		
	}
	
	public void addEditButtonListener(ActionListener addEpisodeListener) {
		jbtCommenceEdit.addActionListener(addEpisodeListener);
	}
	public void addEditMMButtonListener(ActionListener addEditMMListener) {
		jbtEditMM.addActionListener(addEditMMListener);
	}

}
