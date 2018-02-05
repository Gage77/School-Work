import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	MediaModel model;
	
	SelectionView slView;
	
	JButton jbtCommenceEdit = new JButton("Commence Edit");
	
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
	
	public EditView() {
		
	}
	
	public EditView(int typeBeingEdited, MediaModel model, SelectionView slView) {
		this.model = model;
		this.slView = slView;
		
		if (typeBeingEdited == 1)
		{
			Movie movie = (Movie)slView.display.getSelectedValue();
			
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
		else if (typeBeingEdited == 2)
		{
			Series series = (Series) slView.display.getSelectedValue();
			
			jtfTitle = new JTextField(series.getTitle(), 20);
			jtfStartYear = new JTextField(series.getReleaseYear().toString(), 20);
			jtfEndYear = new JTextField(series.getEndYear().toString(), 20);
			
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
		else if (typeBeingEdited == 3)
		{
			Episode episode = (Episode) slView.display.getSelectedValue();
			
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
	
	public void addEditButtonListener(ActionListener addEpisodeListener) {
		jbtCommenceEdit.addActionListener(addEpisodeListener);
	}

}
