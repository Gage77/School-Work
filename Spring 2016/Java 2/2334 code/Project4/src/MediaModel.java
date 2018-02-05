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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Project #4
 * CS 2334, Section 013 
 * 4/13/16
 * <p>
 * This class will satisfy the model portion of the MVC design plan
 * It will contain lists which themselves will contain all available 
 * Movie, Series/Episode, MediaMaker, Actor, Director, and Producer objects
 * obtained from the file chosen by the user.
 * </p>
 * @version 1.0
 *
 */

public class MediaModel implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Object> everything = new ArrayList<Object>();
	private ArrayList<ActionListener> actionListenerList;
	private LinkedHashMap<String, MediaMaker> people;
	private List<Media> media;
	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<Series> series = new ArrayList<Series>();
	private ArrayList<Episode> episodes = new ArrayList<Episode>();
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<Director> directors = new ArrayList<Director>();
	private ArrayList<Producer> producers = new ArrayList<Producer>();
	
	public void addObject(Media obj){}
	public void addObject(MediaMaker obj){}
	
	public Media[] getMediaObjects(){
		return (Media[]) this.media.toArray();
	}
	
	public void parseSelectedFile(File file, String type){
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";

		if (type.equals("Movies"))
		{
			while ((line = br.readLine()) != null)
			{
				Movie newMovie = ParseFile.parseMovie(line);
				movies.add(newMovie);
			}
			for (int i = 0; i < movies.size(); ++i)
			{
				System.out.println(movies.get(i).toString());
			}
		}
		else if (type.equals("Series/Episode"))
		{
			while ((line = br.readLine()) != null)
			{
				Series newSeries = ParseFile.parseSeries(line);
				series.add(newSeries);
			}
			for (int i = 0; i < series.size(); ++i)
			{
				System.out.println(series.get(i).toString(true));
			}
		}
		else if (type.equals("Actors"))
		{
			while ((line = br.readLine()) != null)
			{
				ParseFile.parseActingCredits(line, people, media);
			}
		}
		else if (type.equals("Directors"))
		{
			while ((line = br.readLine()) != null)
			{
				ParseFile.parseActingCredits(line, people, media);
			}
		}
		else if (type.equals("Producers"))
		{
			while ((line = br.readLine()) != null)
			{
				ParseFile.parseActingCredits(line, people, media);
			}
		}
		
		br.close();
		
		}
		
		catch (FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "That file does not exist!");
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error reading the file");
		}
	}
	
	//The next methods deal with handling movies
	public void addMovie(Movie m){
		this.movies.add(m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add movie"));
		Collections.sort(this.movies);
	}
	
	public void replaceMovie(Movie m, int index){
		this.movies.set(index, m);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "replace movie"));
	}
	
	public void editMovie(Movie m, String title, Integer releaseYear, String venue) {
		int index = movies.indexOf(m);
		
		m.setTitle(title);
		m.setReleaseYear(releaseYear);
		m.setVenue(venue);
		
		movies.set(index, m);
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit movie"));
	}
	
	public ArrayList<Movie> getMovieObjects(){
		return movies;
	}
	
	//This is for adding movie objects to JList
	public Movie[] getMovieObjectsArray() {
		if (!movies.isEmpty())
			return (Movie[])movies.toArray();
		return null;
	}
	
	//The next methods deal with handling series
	public void addSeries(Series s){
		this.series.add(s);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add series"));
		Collections.sort(this.series);
	}
	
	public void replaceSeries(Series s, int index){
		this.series.set(index, s);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "replace series"));
	}
	
	public void editSeries(Series s, String title, Integer startYear, Integer endYear) {
		int index = series.indexOf(s);
		
		s.setTitle(title);
		s.setReleaseYear(startYear);
		s.setEndYear(endYear);
		
		series.set(index, s);
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit series"));
	}
	
	public ArrayList<Series> getSeriesObjects(){
		return series;
	}
	
	public void addEpisode(Episode e) {
		episodes.add(e);
		
		for (int i = 0; i < series.size(); ++i)
		{
			if (series.get(i).getTitle().equals(e.getSeriesName()))
			{
				series.get(i).addEpisode(e);
			}
		}
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "add episode"));
	}
	
	public void replaceEpisode(Episode e, int index) {
		this.episodes.set(index, e);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "replace episode"));
	}
	
	public void editEpisode(Episode e, String episodeTitle, String seriesTitle, Integer episodeNumber, Integer releaseYear){
		int index = episodes.indexOf(e);
		
		e.setTitle(episodeTitle);
		e.setReleaseYear(releaseYear);
		e.setEpisodeNumber(episodeNumber);
		e.setSeriesName(seriesTitle);
		
		episodes.set(index, e);
		
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "edit episode"));
	}
	
	public ArrayList<Episode> getEpisodeObjects(){
		return episodes;
	}
	
	public Episode[] getEpisodeObjectsArray() {
		if (!episodes.isEmpty())
			return (Episode[])episodes.toArray();
		return null;
	}
	
	public ArrayList<MediaMaker> getMediaMakerObjects(){
		return (ArrayList<MediaMaker>) people.values();
	}
	
	public void getActorObjects(){}
	public void getDirectorObjects(){}
	public void getProducerObjects(){}
	
	public void saveTxt(File file) throws IOException {
		FileWriter outFile = new FileWriter(file);	
		BufferedWriter bw = new BufferedWriter(outFile);
		
		for (int i = 0; i < movies.size(); ++i)
		{
			everything.add(movies.get(i));
		}
		for (int i = 0; i < series.size(); ++i)
		{
			everything.add(series.get(i));
		}
		for (int i = 0; i < actors.size(); ++i)
		{
			everything.add(actors.get(i));
		}
		for (int i = 0; i < directors.size(); ++i)
		{
			everything.add(directors.get(i));
		}
		for (int i = 0; i < producers.size(); ++i)
		{
			everything.add(producers.get(i));
		}
		
		for (int i = 0; i < everything.size(); ++i)
		{
			bw.write(everything.get(i).toString());
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
	 * Fire event.
	 */
	private void processEvent(ActionEvent e) {
		ArrayList<ActionListener> list;
		
		synchronized (this) {
			if (actionListenerList == null) return;
			list = (ArrayList<ActionListener>)actionListenerList.clone();
		}
		
		for (int i = 0; i < list.size(); i++) {
			ActionListener listener = list.get(i);
			listener.actionPerformed(e);
		}
	}
	
	
	
}