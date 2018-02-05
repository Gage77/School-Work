import java.awt.event.*;

/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE
 * <P>
 * This class represents the controller for the MVC lab.
 * </P>
 * 
 * @version 1.0
 */

public class Controller {
	private Model model;
	private StateView stateView;

	/** Creates new Controller */
	Controller() {
		// empty
	}

	private class SelectPlaceButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			// No model associated. Can't do anything.
			if (model == null)
				return;

			// Get the index of the selected item.
			int index = stateView.getSelectedStateIndex();

			// Update the sublist, add the selected contact to the sublist.
			if (index > -1)
				model.addToSelectedList(index);
		}
	}

	public void setModel(Model model) { 
		this.model = model;
	}

	public void setView(StateView masterView) {
		// Set the master view.
		this.stateView = masterView;

		// Register listener 
		stateView.registerSelectPlaceButtonListener(new SelectPlaceButtonListener());
	}
}
