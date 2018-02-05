import java.awt.event.*;
import java.util.*;

/**
 * Lab #5
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * Each object of this class represents a GUI enhanced model of a series.
 * The model of an MVC GUI.
 * </p>
 * @version 1.0
 *
 */
public class SeriesModel extends Series {

	/**
	 * List to keep track of who is registered to listen for events from the SeriesModel.
	 */
	private ArrayList<ActionListener> actionListenerList;
	
	/**
	 * @param title
	 */
	public SeriesModel(String title, int startYear, int endYear) {
		super(title, startYear, endYear);
	}

	/**
	 * @param title
	 * @param episodes
	 */
	public SeriesModel(String title, LinkedHashMap<String, Episode> episodes) {
		super(title, episodes);
	}
	
	/**
	 * @param episode
	 */
	public void addEpisode(Episode episode) {
		super.addEpisode(episode);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add episode"));
	}
	
	
	public void clearEpisodes() {
		super.clearEpisodes();
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clear episode list"));
	}
	
	/**
	 * Register an action event listener.
	 */
	public synchronized void addActionListener(ActionListener l) {
		if (actionListenerList == null)
			actionListenerList = new ArrayList<ActionListener>();
		actionListenerList.add(l);
	}
	
	/**
	 * Remove an action event listener.
	 */
	public synchronized void removeActionListener(ActionListener l) {
		if (actionListenerList != null && actionListenerList.contains(l))
			actionListenerList.remove(l);
	}
	
	/**
	 * Fire event.
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<?> list;
		
		synchronized (this) 
		{
			if (actionListenerList == null) return;
			list = (ArrayList<?>)actionListenerList.clone();
		}
		
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = (ActionListener)list.get(i);
			listener.actionPerformed(e);
		}
	}
}