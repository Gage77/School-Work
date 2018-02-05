import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Project #4
 * CS 2334, Section 013 
 * 4/13/16
 * <p>
 * This class will satisfy the controller portion of the MVC design plan
 * </p>
 * @version 1.0
 *
 */
public class MediaController {
	
	/** This will be the model that this controller is connected to */
	MediaModel model;
	/** This will be the selection view that this controller listens to and interacts with */
	SelectionView slView;
	/** This will be the add view that this controller listens to and interacts with. Currently,
	 * only on add view can be listened to at one time */
	AddView addView;
	/** This will be the editView that this controller listens to and interacts with. */
	EditView editView;
	
	/**
	 * The base constructor for this object
	 */
	public MediaController() {
		
	}
	
	//Create private class listeners for the radio buttons
	private class AddMediaListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Media");
		}		
	}
	
	private class AddMoviesListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Movies");
		}		
	}
	
	private class AddSeriesListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Series");
		}		
	}
	
	private class AddEpisodesListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Episodes");
		}		
	}
	
	private class AddMakersListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Makers");
		}		
	}
	
	private class AddActorsListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Actors");
		}		
	}
	
	private class AddDirectorsListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Directors");
		}		
	}
	
	private class AddProducersListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Producers");
		}		
	}
	
	//Create private classes for the JMenu jmFile
	private class AddSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File chosenFile;
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showSaveDialog(fc);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				chosenFile = fc.getSelectedFile();
				try {
					model.saveTxt(chosenFile);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error in saving to specified file, please try again");
					e1.printStackTrace();
				}
			}
						
			System.out.println("Save");
		}
	}
	
	private class AddLoadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File chosenFile;
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showOpenDialog(fc);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				chosenFile = fc.getSelectedFile();
				try {
					MediaModel newModel = MediaModel.readModel(chosenFile);
					
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Error reading from binary file. Please try again or choose a different file.");
					e1.printStackTrace();
				}
			}
			
			System.out.println("Load");
			
		}		
	}
	
	// Need separate stuff for reading binary file
	private class AddImportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] options = {"Movies", "Series/Episode", "Actors", "Directors", "Producers"};
			String s = (String)JOptionPane.showInputDialog(null, "Choose the type of objects represented in the selected file",
					"File type", JOptionPane.QUESTION_MESSAGE, null, options, "Movies");
			File chosenFile;
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showOpenDialog(fc);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				chosenFile = fc.getSelectedFile();
				model.parseSelectedFile(chosenFile, s);
			}
			System.out.println("Import");
		}		
	}
	
	private class AddExportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			File chosenFile;
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showOpenDialog(fc);
			if (returnValue == JFileChooser.APPROVE_OPTION);
			{
				chosenFile = fc.getSelectedFile();
			}
			System.out.println("Export");
		}		
	}
	
	//Create the private class listeners for the JMenu jmEdit
	private class AddAddListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (slView.jrbMovies.isSelected())
			{
				AddView movieAddView = new AddView(1);
				setAddView(movieAddView);			
			}
			else if (slView.jrbSeries.isSelected())
			{
				AddView seriesAddView = new AddView(2);
				setAddView(seriesAddView);
			}
			else if (slView.jrbEpisodes.isSelected())
			{
				AddView episodeAddView = new AddView(3);
				setAddView(episodeAddView);
			}
			else if (slView.jrbActors.isSelected())
			{
				AddView actorsAddView = new AddView(4, model.getMovieObjectsArray(), model.getEpisodeObjectsArray());
				setAddView(actorsAddView);
			}
			else if (slView.jrbDirectors.isSelected())
			{
				AddView directorsAddView = new AddView(4, model.getMovieObjectsArray(), model.getEpisodeObjectsArray());
				setAddView(directorsAddView);
			}
			else if (slView.jrbProducers.isSelected())
			{
				AddView producersAddView = new AddView(4, model.getMovieObjectsArray(), model.getEpisodeObjectsArray());
				setAddView(producersAddView);
			}
			
			System.out.println("Add");
		}		
	}
	
	private class AddEditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (slView.display.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "No object from list selected. Please select an object and try again.");
			}
			else {
			if (slView.jrbMovies.isSelected())
			{
				EditView moviesEditView = new EditView(1, model, slView);
				setEditView(moviesEditView);
			}
			else if (slView.jrbSeries.isSelected())
			{
				EditView seriesEditView = new EditView(2, model, slView);
				setEditView(seriesEditView);
			}
			else if (slView.jrbEpisodes.isSelected())
			{
				EditView episodesEditView = new EditView(3, model, slView);
				setEditView(episodesEditView);
			}
			else if (slView.jrbActors.isSelected())
			{
				EditView actorsEditView = new EditView(4, model, slView);
				setEditView(actorsEditView);
			}
			else if (slView.jrbDirectors.isSelected())
			{
				EditView directorsEditView = new EditView(4, model, slView);
				setEditView(directorsEditView);
			}
			else if (slView.jrbProducers.isSelected())
			{
				EditView producersEditView = new EditView(4, model, slView);
				setEditView(producersEditView);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please click the button of the specific object you would like to edit.");
			}
			}
			
			System.out.println("Edit");
		}		
	}
	
	private class AddDeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Delete");
		}		
	}
	
	private class AddClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clear");
		}		
	}
	
	private class AddClearAllListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clear All");
		}		
	}
	
	//Create private class listeners for the JMenu jmDisplay
	private class AddPiChartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("PiChart");
		}		
	}
	
	private class AddHistogramListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Histogram");
		}		
	}
	
	
	//Create classes for the AddView's add button
	private class AddAddviewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean flag = false; //This will indicate whether the object already exists in the model
			//First if statement will deal with Movies being dealt with
			if (slView.jrbMovies.isSelected())
			{
				String title = addView.jtfTitle.getText();
				Integer releaseYear = Integer.valueOf(addView.jtfReleaseYear.getText());
				String releaseFormat = addView.jtfReleaseFormat.getText();
				
				Movie movie = new Movie(title, releaseYear, releaseFormat);
				ArrayList<Movie> movieCheck = model.getMovieObjects();
				if (movieCheck.contains(movie)) {
					//Movie already in model
					flag = true;
						String[] buttons = {"Replace Movie", "Cancel"};
						String message = "That movie already exists in this database";
						int response = JOptionPane.showOptionDialog(null, message, message, JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
						
						if (response == 1) 
						{
							// option 1 is cancel so do nothing
						}
						else if (response == 0) 
						{
							model.replaceMovie(movie, movieCheck.indexOf(movie));
						}
				}
				if (flag == false)
				{
					model.addMovie(movie);
				}
			}
			//Second if statement deals with Series being dealt with
			else if (slView.jrbSeries.isSelected())
			{
				String title = addView.jtfTitle.getText();
				Integer startYear = Integer.valueOf(addView.jtfStartYear.getText());
				Integer endYear = Integer.valueOf(addView.jtfEndYear.getText());
				
				Series series = new Series(title, startYear, endYear);
				ArrayList<Series> seriesCheck = model.getSeriesObjects();
				if (seriesCheck.contains(series)) {
						flag = true;
						String[] buttons = {"Replace Series", "Cancel"};
						String message = "That series already exists in this database";
						int response = JOptionPane.showOptionDialog(null, message, message, JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
						
						if (response == 1) 
						{
							// option 1 is cancel so do nothing
						}
						else if (response == 0) 
						{
							model.replaceSeries(series, seriesCheck.indexOf(series));
						}
				}
				if (flag == false)
				{
					model.addSeries(series);
				}
			}
			else if (slView.jrbEpisodes.isSelected())
			{
				String episodeTitle = addView.jtfTitle.getText();
				Integer episodeNumber = Integer.valueOf(addView.jtfEpisodeNumber.getText());
				Integer releaseYear = Integer.valueOf(addView.jtfReleaseYear.getText());
				String seriesTitle = addView.jtfSeriesTitle.getText();
				
				Episode episode = new Episode(episodeTitle, releaseYear, seriesTitle, episodeNumber);
				ArrayList<Episode> episodeCheck = model.getEpisodeObjects();
				if (episodeCheck.contains(episode)) 
				{
						flag = true;
						String[] buttons = {"Replace Episode", "Cancel"};
						String message = "That series already exists in this database";
						int response = JOptionPane.showOptionDialog(null, message, message, JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
						
						if (response == 1) 
						{
							// option 1 is cancel so do nothing
						}
						else if (response == 0) 
						{
							model.replaceEpisode(episode, episodeCheck.indexOf(episode));
						}
				}
				else 
				{
					model.addEpisode(episode);
				}
			}
			System.out.println("AddView button");
		}
		
	}
	
	private class AddEditviewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (slView.jrbMovies.isSelected())
			{
				String title = editView.jtfTitle.getText();
				Integer releaseYear = Integer.valueOf(editView.jtfReleaseYear.getText());
				String venue = editView.jtfReleaseFormat.getText();
				
				Movie movieToEdit = (Movie)slView.display.getSelectedValue();
				
				model.editMovie(movieToEdit, title, releaseYear, venue);
			}
			else if (slView.jrbSeries.isSelected())
			{
				String title = editView.jtfTitle.getText();
				Integer startYear = Integer.valueOf(editView.jtfReleaseYear.getText());
				Integer endYear = Integer.valueOf(editView.jtfEndYear.getText());
				
				Series seriesToEdit = (Series)slView.display.getSelectedValue();
				
				model.editSeries(seriesToEdit, title, startYear, endYear);
				
			}
			else if (slView.jrbEpisodes.isSelected())
			{
				String episodeTitle = editView.jtfTitle.getText();
				String seriesTitle = editView.jtfSeriesTitle.getText();
				Integer episodeNumber = Integer.valueOf(editView.jtfEpisodeNumber.getText());
				Integer releaseYear = Integer.valueOf(editView.jtfReleaseYear.getText());
				
				Episode episodeToEdit = (Episode)slView.display.getSelectedValue();
				
				model.editEpisode(episodeToEdit, episodeTitle, seriesTitle, episodeNumber, releaseYear);
			}
			else if (slView.jrbActors.isSelected())
			{
				
			}
			else if (slView.jrbDirectors.isSelected())
			{
				
			}
			else if (slView.jrbProducers.isSelected())
			{
				
			}
			System.out.println("Editview");
		}
		
	}
	
	/**
	 * This method will set the MediaModel for this controller
	 * 
	 * @param newModel	The MediaModel to be assigned to this controller	
	 */
	public void setModel(MediaModel newModel) {
		this.model = newModel;
	}
	
	/**
	 * This method will return the MediaModel associated with this controller
	 * 
	 * @return			The MediaModel associated with this controller
	 */
	public MediaModel getModel() {
		return this.model;
	}
	
	/**
	 * This method will set the SelectionView for this controller,
	 * as well as register all listeners associated with that SelectionView
	 * 
	 * @param view		The SelectionView to be assigned to the controller
	 */
	public void setSelectionView(SelectionView view) {
		this.slView = view;
		
		//Register listeners for the radio buttons in the SelectionView slView
		slView.addMediaButtonListener(new AddMediaListener());
		slView.addMoviesButtonListener(new AddMoviesListener());
		slView.addSeriesButtonListener(new AddSeriesListener());
		slView.addEpisodeButtonListener(new AddEpisodesListener());
		slView.addMakersButtonListener(new AddMakersListener());
		slView.addActorsButtonListener(new AddActorsListener());
		slView.addDirectorsButtonListener(new AddDirectorsListener());
		slView.addProducersButtonListener(new AddProducersListener());
		
		//Register listeners for the JMenu jmFile in the SelectionView slView
		slView.addSaveButtonListener(new AddSaveListener());
		slView.addLoadButtonListener(new AddLoadListener());
		slView.addImportButtonListener(new AddImportListener());
		slView.addExportButtonListener(new AddExportListener());
		
		//Register listeners for the JMenu jmEdit in the SelectionView slView
		slView.addAddButtonListener(new AddAddListener());
		slView.addEditButtonListener(new AddEditListener());
		slView.addDeleteButtonListener(new AddDeleteListener());
		slView.addClearButtonListener(new AddClearListener());
		slView.addClearAllButtonListener(new AddClearAllListener());
		
		//Register listeners for the JMenu jmDisplay in the SelectionView slView
		slView.addPiChartButtonListener(new AddPiChartListener());
		slView.addHistogramButtonListener(new AddHistogramListener());
	}
	
	public void setAddView(AddView view) {
		this.addView = view;
		
		//Register add button listener for the AddView's JButton jbtAdd
		addView.addAddButtonListener(new AddAddviewListener());
	}
	
	public void setEditView(EditView view) {
		this.editView = view;
		
		editView.addEditButtonListener(new AddEditviewListener());
	}
	
	/**
	 * This method will return the SelectionView associated with this controller
	 * 
	 * @return		The SelectionView associated with this controller
	 */
	public SelectionView getSelectionView() {
		return this.slView;
	}

}
