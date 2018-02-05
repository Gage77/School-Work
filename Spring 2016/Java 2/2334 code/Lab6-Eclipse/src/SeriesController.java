import java.awt.event.*;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Lab #6
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * The MVC controller for the program.
 * </p>
 * 
 * @version 1.0
 * 
 */
public class SeriesController {
	private SeriesModel model;
	private SeriesInputWindow inputView;

	/**
	 * Creates new SeriesController
	 */
	public SeriesController() {
		// intentionally empty
	}

	/**
	 * AddEpisodeListener provides a method to create a new episode. The episode
	 * should then be added to the episode list of the model. The values for the
	 * new episode come from the fields of the inputView. If the model is null
	 * when the method is called, it returns without taking any action.
	 */
	private class AddEpisodeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (model == null) return;	// No model associated.  Can't do anything.
			
			String title = inputView.jtfTitle.getText();
			String seasonNumber = inputView.jtfSeasonNumber.getText();
			String episodeNumber = inputView.jtfEpisodeNumber.getText();
			String year = inputView.jtfYear.getText();
			
			Episode episode = new Episode(title, seasonNumber, episodeNumber, year);

			if (model.contains(title)) {  // Episode already in collection so ask what to do
				String[] buttons = {"Replace Episode", "Cancel"};
				String message = "An Episode entitled " + title + " already exists.";
				// TODO: Use a JOptionPane here to ask what to do 
				
				int response;  // assign returned value of JOptionPane to response
				
				response = JOptionPane.showOptionDialog(null, message, message, JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);

				if (response == 1) 
				{
					// option 1 is cancel so do nothing
				}
				else if (response == 0) 
				{
					model.replaceEpisode(episode);
				}
				else {
					// Should handle error case here (we won't for this lab).
				}
			}
			else 
			{  
				// Episode not in collection, so add
				model.addEpisode(episode);
			}
		}
	}

	/**
	 * ClearEpisodeListener provides a method to clear the episode list of the
	 * model. If the model is null when the method is called, it returns without
	 * taking any action.
	 */
	private class ClearEpisodeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (model == null) return;
			model.clearEpisodes();
		}
	}

	/**
	 * ClearInputFieldsListener provides a method to clear the input fields of
	 * the inputView.
	 */
	private class ClearInputFieldsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (model == null) return;
			inputView.clearInputFields();
		}
	}
	
	private class LoadButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			try {
				model.load();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class SaveButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	private class ExitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	// TODO:  Create additional listener classes for menu and toolbar.
	private class PrintButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			model.print();
		}
	}

	/**
	 * @param model
	 * 			to set as the model
	 */
	public void setModel(SeriesModel model) {
		this.model = model;
	}

	/**
	 * @return SeriesModel the model
	 */
	public SeriesModel getModel() { // future use
		return model;
	}

	/**
	 * @param theView
	 *            on which the listeners should be set
	 */
	public void setInputWindow(SeriesInputWindow theView) {
		inputView = theView;

		// Register listeners
		inputView.addAddEpisodeButtonListener(new AddEpisodeListener());		 
		inputView.addClearEpisodesButtonListener(new ClearEpisodeListener());
		inputView.addClearInputButtonListener(new ClearInputFieldsListener());

		// TODO: Register additional listeners for menu and toolbar.
		inputView.addLoadButtonListener(new LoadButtonListener());
		inputView.addSaveButtonListener(new SaveButtonListener());
		inputView.addExitButtonListener(new ExitButtonListener());

		System.out.println("Fluorine, Uranium, Carbon, Potassium, Bismuth, Technetium, Einsteinium,"
				+ "Germanium, Thulium, Oxygen, Neon, Yttrium");
	}
	
	/**
	 * @return the window (in case it needs to be sent messages from elsewhere)
	 */
	public SeriesInputWindow getInputWindow() { // future use
		return inputView;
	}
}