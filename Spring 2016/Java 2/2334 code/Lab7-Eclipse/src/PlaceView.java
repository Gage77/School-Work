import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE 
 * <P>
 * This class represents a view of places with their population records for the MVC lab.
 * </P>
 * 
 * @version 1.0
 */

public class PlaceView extends JFrame implements ActionListener {
	/** Needs a serialVersionUID because the class is Serializable */
	private static final long serialVersionUID = 1L;

	//The model for this view.
	private Model model;
	
	//The GUI elements for this view.
	private DefaultListModel<Place> listModel = new DefaultListModel<Place>();
	private JList<Place> jlSelectedPlaces = new JList<Place>(listModel);
	private JScrollPane jspSelectedPlaces = new JScrollPane(jlSelectedPlaces);
	
	/** The default constructor*/
	PlaceView() {
		setTitle("Selected Places");
		jlSelectedPlaces.setSelectionBackground(jlSelectedPlaces.getBackground());
		add(jspSelectedPlaces, BorderLayout.CENTER);
	}
	
	public void setModel(Model model) {
		/** Set a model */
		this.model = model;
		if (this.model != null){
			// Register the view as listener for the model
			model.addActionListener(this);
		}
	}
	
	/**
	 * ActionListener for the method.
	 * <P>
	 * @param      actionEvent     an event				
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("Addition to Selected Places")) {
			listModel.addElement(model.getSelectedPlace());
			pack();
			setVisible(true);
		}
	}
}