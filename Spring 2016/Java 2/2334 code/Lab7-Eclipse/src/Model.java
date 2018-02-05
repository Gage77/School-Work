import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Lab #7 CS 2334, Section 013 4/7/16
 * Hunter Black, Andrew Easley
 * <P>
 * This class represents the Model for the MVC lab. It contains the data that
 * will be shared among various views and the method to manipulate the data.
 * </P>
 * 
 * @version 1.0
 */

public class Model {
	// The list of action listeners
	ArrayList<ActionListener> actionListenerList;
	// The list of places in a state (created from the file)
	ArrayList<Place> statePlaceList;
	// The list of places selected for more detailed display
	ArrayList<Place> selectedPlaceList;
	// The most recently selected place
	Place selectedPlace = null;

	/** The default (no-arg) constructor */
	Model() {
		statePlaceList = null;
		selectedPlaceList = new ArrayList<Place>();
	}

	/**
	 * The constructor for the class.
	 * <P>
	 * 
	 * @param statePlaceList
	 *            a list of Place objects for a state
	 */
	Model(List<Place> statePlaceList) {
		// set the list of places for the state
		this.statePlaceList = (ArrayList<Place>)statePlaceList;

		// create the sublist of selected places to display in more detail
		this.selectedPlaceList = new ArrayList<Place>();
	}

	/**
	 * @return a copy of the list of places in the state.
	 */
	public List<Place> getStatePlaceList() {
		return new ArrayList<Place>(statePlaceList);
	}

	/**
	 * @return Returns the most recently selected places.
	 */
	public Place getSelectedPlace() {
		return new Place(selectedPlace);
	}

	/**
	 * Method to set the subList.
	 * <P>
	 * 
	 * @param index
	 *            index of the place selected in the list
	 */
	public void addToSelectedList(int index) {
		selectedPlace = statePlaceList.get(index);

		// If place has already been selected, don't add it again.
		if (!selectedPlaceList.contains(selectedPlace)) {
			selectedPlaceList.add(selectedPlace);
			
			// Notify the listener of the change in subList
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Addition to Selected Places"));
		}
	}

	/** Register an action event listener */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null) {
			actionListenerList = new ArrayList<ActionListener>();
		}
		actionListenerList.add(l);
	}

	/** Remove an action event listener */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l)) {
			actionListenerList.remove(l);
		}
	}

	/** Fire Event */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;
		synchronized (this) {
			if (actionListenerList == null)
				return;
			// Do not worry about the cast warning here.
			list = (ArrayList<ActionListener>) actionListenerList.clone();
		}
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}

	/**
	 * This method reads in a specified comma delimited file. The file is read
	 * one line at a time. The read line is then split and a Place object is
	 * created using the parsed line. The Place object is then added to a List,
	 * which is returned when the entire file has been processed.
	 * <P>
	 * 
	 * @param filename
	 *            String name of the file to be read
	 * @return Returns a List of Place objects
	 */

	public void readFile(String filename) {
		// Create a file object
		try {
			File file = new File(filename);
	
			// Check if a specified file exists, if not then exit the program
			if (!file.exists()) {
				System.out.println("ERROR:: File not found.");
				System.exit(0);
			}
	
			
			// Create file reader and buffered reader to read the file
			FileReader fileReader = new FileReader(filename);
			BufferedReader br = new BufferedReader(fileReader);
	
			List<Place> statePlaceList = new ArrayList<Place>();
	
			try {
			// read a line from the file, if the first is empty exit the program
			String line = br.readLine();
			if (line.length() <= 0) {
				System.out.println("ERROR:: There is no data in file.");
				System.exit(0);
			}
	
			// read file one line at a time, until end of file
			do {
				Place place = new Place(line.split(","));
				statePlaceList.add(place);
				line = br.readLine();
			} while (line != null);
	
			br.close();
			}
			
			catch(IOException e){
				System.out.println("There is no data in the specified file");
			}
	
			// Notify the listener of the change in list of places for the state
			this.statePlaceList = (ArrayList<Place>)statePlaceList;
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "State Place List"));
		}
		
		catch (FileNotFoundException e){
			System.out.println("I'm sorry Dave, I can't do that... (File you are trying to access doesn't exist");
			System.out.println("Please go to the command line arguments to insert the correct file");
		}
	}
}