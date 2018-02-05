import java.awt.event.*;

/**
 * Lab #5
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
		// intentionally empty, do not change
	}

	/**
	 * AddEpisodeListener provides a method to create a new episode. The episode
	 * should then be added to the episode list of the model. The values for the
	 * new episode come from the fields of the inputView using getText(). If the
	 * model is null when the method is called, it returns without taking any action.
	 */
	private class AddEpisodeListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Episode episode = new Episode(inputView.jtfTitle.getText(),	inputView.jtfSeasonNumber.getText(), 
					inputView.jtfEpisodeNumber.getText(), inputView.jtfYear.getText());
			model.addEpisode(episode);
			if (model == null)
				return;
		}
	}

	/**
	 * ClearEpisodeListener provides a method to clear the episode list of the
	 * model. If the model is null when the method is called, it returns without
	 * taking any action.
	 */
	private class ClearEpisodeListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			model.clearEpisodes();
			if (model == null)
				return;			
		}
		// TODO: add method here
	}

	/**
	 * ClearInputFieldsListener provides a method to clear the input fields of
	 * the inputView.
	 */
	private class ClearInputFieldsListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			inputView.clearInputFields();			
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
		
		inputView.addClearInputFieldsListener(new ClearInputFieldsListener());

		// TODO: The first action listener has been added above. You need to add
		// the others below. There needs to be at least one action listener for
		// each component on which actions can occur.	
		inputView.addAddEpisodeButtonListener(new AddEpisodeListener());
		inputView.addClearEpisodesListener(new ClearEpisodeListener());
	
	}
}