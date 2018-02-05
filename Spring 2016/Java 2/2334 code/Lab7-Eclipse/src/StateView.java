import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE
 * <P>
 * This class represents a view of all the places for one state (just the name
 * of each place) for the MVC lab.
 * </P>
 * 
 * @version 1.0
 */
public class StateView extends JFrame implements ActionListener {
	/** Needs a serialVersionUID because the class is Serializable */
	private static final long serialVersionUID = 1L;

	// The model for this view.
	private Model model;

	// setup GUI for this view.
	private JButton jbtSelectPlace = new JButton("Add place to selected place list");
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> jlStatePlaceList = new JList<String>(listModel);
	private JScrollPane scrollPane = new JScrollPane(jlStatePlaceList);

	/** The default constructor */
	StateView() {
		setTitle("List of Places in State");
		jlStatePlaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scrollPane);
		add(jbtSelectPlace, BorderLayout.EAST);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * This method sets the model for this view and registers the view as a
	 * listener with the model.
	 * 
	 * @param model
	 *            The model to be set.
	 */
	public void setModel(Model model) {
		this.model = model;
		populateJList();
		if (this.model != null) {
			// Register the view as listener for the model
			model.addActionListener(this);
		}
	}

	/**
	 * This method pulls the names of the places from the state list and stores
	 * them in the JList of selected places for detailed display.
	 */
	private void populateJList() {
		List<Place> statePlaceList = model.getStatePlaceList();
		if (statePlaceList != null)
			listModel.clear();
		for (int i = 0; i < statePlaceList.size(); i++) {
			listModel.addElement((model.statePlaceList.get(i).getName()));
		}
	}

	/**
	 * ActionListener for the method.
	 * <P>
	 * 
	 * @param actionEvent
	 *            an event
	 * 
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("State Place List")) {
			populateJList();
		}
	}

	/**
	 * @return the index of the state that is selected on the state list
	 */
	public int getSelectedStateIndex() {
		return jlStatePlaceList.getSelectedIndex();
	}

	/**
	 * @param selectPlaceButtonListener
	 *            the listener to register with the select place button
	 */
	public void registerSelectPlaceButtonListener(ActionListener selectPlaceButtonListener) {
		jbtSelectPlace.addActionListener(selectPlaceButtonListener);
	}
}