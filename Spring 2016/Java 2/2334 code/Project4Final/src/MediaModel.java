import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JOptionPane;

public class MediaModel {
	/** This is the ArrayList that will contain all ActionListeners associated with this model */
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
	/** This is the LinkedHashMap that contains all MediaMakers */
	private LinkedHashMap<String, MediaMaker> people = new LinkedHashMap<String, MediaMaker>();
	/** This is the ArrayList that contains all Media objects */
	private ArrayList<Media> media = new ArrayList<Media>();
	/** This is the ArrayList that contains all Movie objects */
	private ArrayList<Media> movies = new ArrayList<Media>();
	/** This is the ArrayList that contains all Series objects */
	private ArrayList<Media> series = new ArrayList<Media>();
	/** This is the ArrayList that contains all Episode objects */
	private ArrayList<Media> episodes = new ArrayList<Media>();
	/** This is the ArrayList that contains all Actor objects */
	private ArrayList<MediaMaker> actors = new ArrayList<MediaMaker>();
	/** This is the ArrayList that contains all Director objects */
	private ArrayList<MediaMaker> directors = new ArrayList<MediaMaker>();
	/** This is the ArrayList that contains all Producer objects */
	private ArrayList<MediaMaker> producers = new ArrayList<MediaMaker>();

	/**
	 * This method takes in a File from the MediaController and sends it to the ParseFile class
	 * to be parsed
	 * 	
	 * @param file		File to be parsed
	 * @param type		Type of objects in the file
	 */
	public void parseSelectedFile(File file, String type) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";

			if (type.equals("Movies")) {
				while ((line = br.readLine()) != null) {
					addObjectMedia(ParseFile.parseMovie(line));
					addObject(ParseFile.parseMovie(line));
				}
			} else if (type.equals("Series/Episode")) {
				while ((line = br.readLine()) != null) {
					addObjectMedia(ParseFile.parseSeries(line));
					addObject(ParseFile.parseSeries(line));
				}
			} else if (type.equals("Actors")) {
				while ((line = br.readLine()) != null) {
					MediaMaker mm = ParseFile.parseCredits(line);
					addObject(mm);
					addObject((Actor)mm);
					
				}
			} else if (type.equals("Directors")) {
				while ((line = br.readLine()) != null) {
					MediaMaker mm = ParseFile.parseCredits(line);
					addObject(mm);
					addObject((Director)mm);
				}
			} else if (type.equals("Producers")) {
				while ((line = br.readLine()) != null) {
					MediaMaker mm = ParseFile.parseCredits(line);
					addObject(mm);
					addObject((Producer)mm);
				}
			}

			br.close();

		}

		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "That file does not exist!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error reading the file");
		}
	}

	//Create methods to add objects to the Model's various class variables
	public void addObjectMedia(Media obj) {
		media.add(obj);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));

	}
	public void addObject(Movie m) {
		media.add(m);
		movies.add(m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}

	public void addObject(Series s) {
		media.add(s);
		series.add(s);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}

	public void addObject(Episode e) {
		media.add(e);
		episodes.add(e);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}
	
	public void addObject(MediaMaker obj) {
		people.put(obj.getName(), obj);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}
	public void addObject(Actor a) {
		people.put(a.getName(), a);
		actors.add(a);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	public void addObject(Director d) {
		people.put(d.getName(), d);
		directors.add(d);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	public void addObject(Producer p) {
		people.put(p.getName(), p);
		producers.add(p);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}
	
	//Create methods to replace objects in the Model
	public void replaceObject(Movie m, Movie old) {
		Collections.replaceAll(media, old, m);
		Collections.replaceAll(movies, old, m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}
	
	public void replaceObject(Series s, Series old) {
		Collections.replaceAll(media, old, s);
		Collections.replaceAll(series, old, s);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}
	
	public void replaceObject(Episode e, Episode old) {
		Collections.replaceAll(media, old, e);
		Collections.replaceAll(episodes, old, e);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}
	
	/**
	 * This method clears all data from the model, setting all class
	 * lists to null/empty, and notifying listeners
	 */
	public void clearAll() {
		people.clear();
		media.clear();
		movies.clear();
		series.clear();
		episodes.clear();
		actors.clear();
		directors.clear();
		producers.clear();
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "All Cleared"));
	}

	/**
	 * This method clears the specified categories list in the model, and notifying listeners
	 * 
	 * @param category		Type of list to cleared
	 */
	public void clear(String category) {

		switch (category) {
		case "Media":
			media.clear();
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Cleared"));
			break;
		case "Movies":
			media.removeAll(this.movies);
			movies.clear();

			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Cleared"));
			break;
		case "Series":
			series.clear();
			media.removeAll(this.series);
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Cleared"));

			break;
		case "Episodes":
			episodes.clear();
			media.removeAll(this.episodes);
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Cleared"));

			break;
		case "Makers":
			people.clear();
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Cleared"));

			break;
		case "Actors":
			for (MediaMaker a : people.values()) {
				if (a.getMakerType() == "ACTOR") {
					deleteObject(a);
					processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Cleared"));

				}
			}
			break;
		case "Directors":
			for (MediaMaker a : people.values()) {
				if (a.getMakerType() == "DIRECTOR") {
					deleteObject(a);
					processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Cleared"));
				}

			}
			break;
		case "Producers":
			for (MediaMaker a : people.values()) {
				if (a.getMakerType() == "PRODUCER") {
					deleteObject(a);
					processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Cleared"));

				}
			}

			break;
		}

	}

	//Create various delete methods for each type of object
	public void deleteObject(Media obj) {
		this.media.remove(obj);	
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));

	}

	public void deleteObject(Movie obj) {
		this.movies.remove(obj);
		this.media.remove(obj);
		
		for (MediaMaker me : actors)
		{
			ArrayList<Media> acCredits = me.getCredits();
			if (acCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : directors)
		{
			ArrayList<Media> diCredits = me.getCredits();
			if (diCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : producers)
		{
			ArrayList<Media> prCredits = me.getCredits();
			if (prCredits.contains(obj))
				me.removeCredit(obj);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}

	public void deleteObject(Series obj) {
		this.series.remove(obj);
		this.media.remove(obj);
		
		for (MediaMaker me : actors)
		{
			ArrayList<Media> acCredits = me.getCredits();
			if (acCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : directors)
		{
			ArrayList<Media> diCredits = me.getCredits();
			if (diCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : producers)
		{
			ArrayList<Media> prCredits = me.getCredits();
			if (prCredits.contains(obj))
				me.removeCredit(obj);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}

	public void deleteObject(Episode obj) {
		this.episodes.remove(obj);
		this.media.remove(obj);
		
		for (MediaMaker me : actors)
		{
			ArrayList<Media> acCredits = me.getCredits();
			if (acCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : directors)
		{
			ArrayList<Media> diCredits = me.getCredits();
			if (diCredits.contains(obj))
				me.removeCredit(obj);
		}
		for (MediaMaker me : producers)
		{
			ArrayList<Media> prCredits = me.getCredits();
			if (prCredits.contains(obj))
				me.removeCredit(obj);
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Media Alteration"));
	}

	public void deleteObject(MediaMaker obj) {
		this.people.remove(obj.getName());
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	public void deleteObject(Actor obj) {
		this.actors.remove(obj);
		this.people.remove(obj.getName());
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	public void deleteObject(Director obj) {
		this.directors.remove(obj);
		this.people.remove(obj.getName());
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	public void deleteObject(Producer obj) {
		this.producers.remove(obj);
		this.people.remove(obj.getName());
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "MediaMaker Alteration"));
	}

	//Create methods to return the various lists
	public ArrayList<Media> getMediaObjects() {

		return media;
	}

	public ArrayList<Media> getMovieObjects() {

		return movies;
	}

	public ArrayList<Media> getSeriesObjects() {

		return series;
	}

	public ArrayList<Media> getEpisodeObjects() {

		return episodes;
	}

	public ArrayList<MediaMaker> getMediaMakerObjects() {
		ArrayList<MediaMaker> results = new ArrayList<MediaMaker>();

		results.addAll(people.values());
		return results;
	}

	public ArrayList<MediaMaker> getActorObjects() {

		return actors;
	}

	public ArrayList<MediaMaker> getDirectorObjects() {

		return directors;
	}

	public ArrayList<MediaMaker> getProducerObjects() {

		return producers;
	}
	
	public void saveTxt(File file) throws IOException {
		FileWriter outFile = new FileWriter(file);	
		BufferedWriter bw = new BufferedWriter(outFile);
		
		for (int i = 0; i < media.size(); ++i)
		{
			bw.write(media.get(i).toString());
			bw.newLine();
		}
		for (int i = 0; i < actors.size(); ++i)
		{
			bw.write(actors.get(i).toString());
			bw.newLine();
		}
		for (int i = 0; i < directors.size(); ++i)
		{
			bw.write(directors.get(i).toString());
			bw.newLine();
		}
		for (int i = 0; i < producers.size(); ++i)
		{
			bw.write(producers.get(i).toString());
			bw.newLine();
		}
		
		bw.close();
	}
	
	public void saveBinary(File file) throws IOException {
		FileOutputStream fileOutput = new FileOutputStream(file);
		ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
		
		objectOutput.writeObject(this);
		objectOutput.close();
	}
	
	public static MediaModel readModel(File file) throws IOException, ClassNotFoundException {
		FileInputStream fileInput = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInput);
		
		MediaModel newModel = (MediaModel) objectInputStream.readObject();
		objectInputStream.close();
		return newModel;
	}

	/**
	 * This method adds the specified listener to the models ActionListener list
	 * 
	 * @param listener		ActionListener to add
	 */
	public synchronized void addActionListener(ActionListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<ActionListener>();
		}
		listeners.add(listener);
	}

	/**
	 * This methods removes the specified ActionLister from the ActionListener List
	 * @param listener
	 */
	public synchronized void removeActionListener(ActionListener listener) {
		if (listeners != null && listeners.contains(listener))
			listeners.remove(listener);
	}

	@SuppressWarnings("unchecked")
	private void processEvent(ActionEvent event) {
		// Borrowed from lab 7. May need to be altered.
		ArrayList<ActionListener> list;
		synchronized (this) {
			if (listeners == null)
				return;
			// Do not worry about the cast warning here.
			list = (ArrayList<ActionListener>) listeners.clone();
		}
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(event);
		}
	}

	public char[] getListeners() {
		System.out.println(listeners);
		return null;
	}
}
