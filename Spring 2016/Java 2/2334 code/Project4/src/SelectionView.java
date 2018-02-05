import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 * Project #4
 * CS 2334, Section 013 
 * 4/13/16  * <p>
 * Each object of this class represents a GUI input window (MVC view).
 * This class will be the main view window for this GUI
 * </p>
 * @version 1.0
 *
 */
public class SelectionView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	MediaModel model;
	
	/** Radio button for Media Objects */
	JRadioButton jrbMedia = new JRadioButton("Media");
	/** Radio button for Movie Objects */
	JRadioButton jrbMovies = new JRadioButton("Movies");
	/** Radio button for Series Objects */
	JRadioButton jrbSeries = new JRadioButton("Series");
	/** Radio button for Episode Objects */
	JRadioButton jrbEpisodes = new JRadioButton("Episodes");
	/** Radio button for MediaMaker Objects */
	JRadioButton jrbMakers = new JRadioButton("Makers");
	/** Radio button for Actor Objects */
	JRadioButton jrbActors = new JRadioButton("Actors");
	/** Radio button for Director Objects */
	JRadioButton jrbDirectors = new JRadioButton("Directors");
	/** Radio button for Producer Objects */
	JRadioButton jrbProducers = new JRadioButton("Producers");
	
	/** These are the JMenus that will be put into this views JMenuBar for user interaction */
	JMenu jmFile = new JMenu("File");
	JMenu jmEdit = new JMenu("Edit");
	JMenu jmDisplay = new JMenu("Display");
	
	/** These are the JMenuItem buttons for the JMenu jmFile */
	JMenuItem jmiLoad = new JMenuItem("Load");
	JMenuItem jmiSave = new JMenuItem("Save");
	JMenuItem jmiImport = new JMenuItem("Import");
	JMenuItem jmiExport = new JMenuItem("Export");
	
	/** These are the JMenuItem buttons for the JMenu jmEdit */
	JMenuItem jmiAdd = new JMenuItem("Add");
	JMenuItem jmiEdit = new JMenuItem("Edit");
	JMenuItem jmiDelete = new JMenuItem("Delete");
	JMenuItem jmiClear = new JMenuItem("Clear");
	JMenuItem jmiClearAll = new JMenuItem("Clear All");
	
	/** These are the JMenuItem buttons for the JMenu jmDisplay */
	JMenuItem jmiPiChart = new JMenuItem("Pi Chart");
	JMenuItem jmiHistogram = new JMenuItem("Histogram");
	
	/** This will hold all of the JMenu objects */
	JMenuBar menuBar = new JMenuBar();
	
	JPanel buttonPanel;
	
	DefaultListModel<Object> selectionModel = new DefaultListModel<Object>();
	JList<Object> display = new JList<Object>(selectionModel);
	
	JScrollPane scroller;

	public SelectionView() {
		
		buttonPanel = new JPanel(new GridLayout(0,1));
		// Add all of the radio buttons to a ButtonGroup which will handle one button being selected at a time
		ButtonGroup group = new ButtonGroup();
		group.add(jrbMedia);
		group.add(jrbMovies);
		group.add(jrbSeries);
		group.add(jrbEpisodes);
		group.add(jrbMakers);
		group.add(jrbActors);
		group.add(jrbDirectors);
		group.add(jrbProducers);
		
		// Add all radio buttons to the buttonPanel
		buttonPanel.add(jrbMedia);
		buttonPanel.add(jrbMovies);
		buttonPanel.add(jrbSeries);
		buttonPanel.add(jrbEpisodes);
		buttonPanel.add(jrbMakers);
		buttonPanel.add(jrbActors);
		buttonPanel.add(jrbDirectors);
		buttonPanel.add(jrbProducers);
		
		// Add all of the JMenuItems associated with the JMenu jmFile to jmFile 
		jmFile.add(jmiLoad);
		jmFile.add(jmiSave);
		jmFile.add(jmiImport);
		jmFile.add(jmiExport);
		
		// Add all of the JMenuItems associated with the JMenu jmEdit to jmEdit
		jmEdit.add(jmiAdd);
		jmEdit.add(jmiEdit);
		jmEdit.add(jmiDelete);
		jmEdit.add(jmiClear);
		jmEdit.add(jmiClearAll);
		
		// Add all of the JMenuItems associated with the JMenu jmDisplay to jmDisplay 
		jmDisplay.add(jmiPiChart);
		jmDisplay.add(jmiHistogram);
		
		// Add all JMenu Objects to the JMenuBar menuBar
		menuBar.add(jmFile);
		menuBar.add(jmEdit);
		menuBar.add(jmDisplay);
		
		//Create a test JList that will eventually display the information to the user
		Movie test = new Movie("William Shatner's Star Trek", new Integer(1995), "V");
		Movie test2 = new Movie("Star Trek Awesomeness", new Integer(2000), "TV");
		Movie test3 = new Movie("Kaosidng aosidhg", new Integer(2013), null);
		Movie[] list = {test, test2, test3};
		display = new JList<Object>(list);
		display.setLayoutOrientation(JList.VERTICAL);
		display.setVisibleRowCount(-1);
		
		//Put the JList display in JScrollPane scroller
		scroller = new JScrollPane(display);
			
		//Sets the JMenuBar menuBar as the menu bar for this JFrame
		this.setJMenuBar(menuBar);
		// Adds the JPanel containing all of the radio buttons to the JFrame
		add(buttonPanel, BorderLayout.LINE_START);
		add(scroller, BorderLayout.CENTER);
		
		//Set up content pane
		setSize(400, 400);
		setTitle("MDb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void addActionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("add movie")) {
			populateJList();
		}
	}
	
	public void populateJList() {
		if (this.jrbMovies.isSelected()) {
			List<Movie> displayUpdate = model.getMovieObjects();
			for (int i = 0; i < displayUpdate.size(); ++i)
			{
				this.selectionModel.addElement(displayUpdate.get(i));
			}
		}
	}
	
	public void setModel(MediaModel model)
	{
		this.model = model;
	}
	
	//Create addListener methods for all of the radio buttons
	public void addMediaButtonListener(ActionListener addMediaListener) {
		jrbMedia.addActionListener(addMediaListener);
	}
	
	public void addMoviesButtonListener(ActionListener addMoviesListener) {
		jrbMovies.addActionListener(addMoviesListener);
	}
	
	public void addSeriesButtonListener(ActionListener addSeriesListener) {
		jrbSeries.addActionListener(addSeriesListener);
	}
	
	public void addEpisodeButtonListener(ActionListener addEpisodesListener) {
		jrbEpisodes.addActionListener(addEpisodesListener);
	}
	
	public void addMakersButtonListener(ActionListener addMakersListener) {
		jrbMakers.addActionListener(addMakersListener);
	}
	
	public void addActorsButtonListener(ActionListener addActorsListener) {
		jrbActors.addActionListener(addActorsListener);
	}
	
	public void addDirectorsButtonListener(ActionListener addDirectorsListener) {
		jrbDirectors.addActionListener(addDirectorsListener);
	}
	
	public void addProducersButtonListener(ActionListener addProducersListener) {
		jrbProducers.addActionListener(addProducersListener);
	}
	
	//Create addListener methods for the menu items in jmFile
	public void addLoadButtonListener(ActionListener addLoadListener) {
		jmiLoad.addActionListener(addLoadListener);
	}
	
	public void addSaveButtonListener(ActionListener addSaveListener) {
		jmiSave.addActionListener(addSaveListener);
	}
	
	public void addImportButtonListener(ActionListener addImportListener) {
		jmiImport.addActionListener(addImportListener);
	}
	
	public void addExportButtonListener(ActionListener addExportListener) {
		jmiExport.addActionListener(addExportListener);
	}
	
	//Create addListener methods for the menu items in jmEdit
	public void addAddButtonListener(ActionListener addAddListener) {
		jmiAdd.addActionListener(addAddListener);
	}
	
	public void addEditButtonListener(ActionListener addEditListener) {
		jmiEdit.addActionListener(addEditListener);
	}
	
	public void addDeleteButtonListener(ActionListener addDeleteListener) {
		jmiDelete.addActionListener(addDeleteListener);
	}
	
	public void addClearButtonListener(ActionListener addClearListener) {
		jmiClear.addActionListener(addClearListener);
	}
	
	public void addClearAllButtonListener(ActionListener addClearAllListener) {
		jmiClearAll.addActionListener(addClearAllListener);
	}
	
	//Create addListener methods for the menu items in jmDisplay
	public void addPiChartButtonListener(ActionListener addPiChartListener) {
		jmiPiChart.addActionListener(addPiChartListener);
	}
	
	public void addHistogramButtonListener(ActionListener addHistogramListener) {
		jmiHistogram.addActionListener(addHistogramListener);
	}

}
