import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Lab #5
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * Each object of this class represents a GUI input window (MVC view).
 * </p>
 * @version 1.0
 *
 */
public class SeriesInputWindow extends JFrame {
	private static final long serialVersionUID = 21305503976948004L;

	/** Button to click to add new episode to the episode list based on filled text fields. */
	JButton jbtAddEpisode = new JButton("Add Episode");

	/** Button to clear text fields of episode info. */
	JButton jbtClearInput = new JButton("Clear Input Fields");

	/** Button to clear episode list. */
	JButton jbtClearEpisodes = new JButton("Clear Episode List");

	/** TextField to input new episode title. */
	JTextField jtfTitle = new JTextField(20);
	JLabel lblTitle = new JLabel("Episode Title", JLabel.RIGHT);

	/** TextField to input episode's season number. */
	JTextField jtfSeasonNumber = new JTextField(20);
	JLabel lblSeasonNumber = new JLabel("Season Number", JLabel.RIGHT);

	/** TextField to input episode's episode number. */
	JTextField jtfEpisodeNumber = new JTextField(20);
	JLabel lblEpisodeNumber = new JLabel("Episode Number", JLabel.RIGHT);
	
	/** TextField to input episode's year. */
	JTextField jtfYear = new JTextField(20);
	JLabel lblYear = new JLabel("Year", JLabel.RIGHT);

	SeriesInputWindow() {
		setTitle("Add Episodes to Series");
		
		// Create panels for name, season number, episode number, and command buttons		
		JPanel jplTitle = new JPanel(new GridLayout(1, 0, 5, 5));
		jplTitle.add(lblTitle);
		jplTitle.add(jtfTitle);

		JPanel jplSeasonNumber = new JPanel(new GridLayout(1, 0, 5, 5));
		jplSeasonNumber.add(lblSeasonNumber);
		jplSeasonNumber.add(jtfSeasonNumber);

		JPanel jplEpisodeNumber = new JPanel(new GridLayout(1, 0, 5, 5));
		jplEpisodeNumber.add(lblEpisodeNumber);
		jplEpisodeNumber.add(jtfEpisodeNumber);
		
		JPanel jplYear = new JPanel(new GridLayout(1, 0, 5, 5));
		jplYear.add(lblYear);
		jplYear.add(jtfYear);

		JPanel jplButton = new JPanel(new GridLayout(1, 0, 5, 5));
		jplButton.add(jbtAddEpisode);
		jplButton.add(jbtClearInput);
		jplButton.add(jbtClearEpisodes);

		// Set up the content pane and add all the panels to it.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 1, 5, 5));
		add(jplTitle);
		add(jplSeasonNumber);
		add(jplEpisodeNumber);
		add(jplYear);
		add(jplButton);
		pack();
		setVisible(true);
	}

	public void clearInputFields() {
		jtfTitle.setText("");
		jtfSeasonNumber.setText("");
		jtfEpisodeNumber.setText("");
		jtfYear.setText("");
	}

	public void addAddEpisodeButtonListener(ActionListener addEpisodeListener) 
	{
		jbtAddEpisode.addActionListener(addEpisodeListener);
	}
	
	// TODO: The first method for adding an action listener has been added
	// above. You need to add the others below. There needs to be at least one
	// method for adding an action listener for each component on which actions
	// can occur
	
	public void addClearInputFieldsListener(ActionListener addEpisodeListener)
	{
		jbtClearInput.addActionListener(addEpisodeListener);
	}
	
	public void addClearEpisodesListener(ActionListener addEpisodeListener)
	{
		jbtClearEpisodes.addActionListener(addEpisodeListener);
	}

}