import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Lab #5
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * This class models a series (such as a TV series).
 * </p>
 * 
 * @version 1.0
 *
 */
public class Series implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Name of the Series */
	private String title;

	/** Year that the Series started */
	private int startYear;

	/** Year that the Series ended */
	private int endYear;

	/** Episodes of the Series */
	private LinkedHashMap<String, Episode> episodeMap;

	/**
	 * This is the default constructor for Series.
	 */
	public Series() {
		title = "";
		startYear = 0;
		endYear = 0;
		this.episodeMap = new LinkedHashMap<String, Episode>();
	}

	/**
	 * This is the basic constructor for Series. It instantiates the class with
	 * user supplied values for series title and start and end years.
	 * <P>
	 * 
	 * @param title
	 *            The title of the Series
	 * @param startYear
	 *            The first year that the Series aired
	 * @param endYear
	 *            The last year that the Series aired
	 */
	public Series(String title, int startYear, int endYear) {
		// We ought to add suitability checks to ensure endYear >= startYear,
		// for example. However, you are not required to do that for your lab.
		this.title = title;
		this.startYear = startYear;
		this.endYear = endYear;
		this.episodeMap = new LinkedHashMap<String, Episode>();
	}

	/**
	 * This is an advanced constructor for Series. It instantiates the class
	 * with user supplied values for series title and start and end years and
	 * also a LinkedHashMap of Episodes.
	 * 
	 * @param title
	 *            The title of the Series
	 * @param startYear
	 *            The first year that the Series aired
	 * @param endYear
	 *            The last year that the Series aired
	 * @param episodeMap
	 *            The LinkedHashMap of Episodes
	 */
	public Series(String title, LinkedHashMap<String, Episode> episodeMap) {
		this.title = title;
		this.episodeMap = episodeMap;
	}

	/**
	 * @return the state title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title of the state to set
	 */
	public void setTitle(String name) {
		this.title = name;
	}

	/**
	 * @return the startYear
	 */
	public int getStartYear() {
		return startYear;
	}

	/**
	 * @param startYear
	 *            the startYear to set
	 */
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	/**
	 * @return the endYear
	 */
	public int getEndYear() {
		return endYear;
	}

	/**
	 * @param endYear
	 *            the endYear to set
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/**
	 * @return an array of the episodes in the episodeMap
	 */
	public Episode[] getEpisodeArray() {
		Episode[] templateEpisodeArray = new Episode[1];
		return episodeMap.values().toArray(templateEpisodeArray);
	}

	/**
	 * @param episodeMap
	 *            the collection of episodes to set
	 */
	public void setEpisodeMap(LinkedHashMap<String, Episode> episodeMap) {
		this.episodeMap = episodeMap;
	}
	
	/**
	 * @param title the title of the episode to look for in the collection
	 * @return true if the episode name is already in the collection, false otherwise
	 */
	public boolean contains(String title) {
		return this.episodeMap.containsKey(title);
	}
	
	/**
	 * @param title the title of the episode to look for in the collection
	 * @return the found episode converted to a String
	 */
	public String getEpisodeString(String title) {
		return this.episodeMap.get(title).toString();
	}

	/**
	 * @param episode to add to the episodeMap
	 * 
	 * If the episode is NOT in the episode collection, it can be added.
	 */
	public boolean addEpisode(Episode episode) {
		if (!this.episodeMap.containsKey(episode.getTitle())){
			this.episodeMap.put(episode.getTitle(), episode);
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * @param episode the episode to replace in the episodeMap 
	 * 
	 * If the episode IS in the episode collection, it can be replaced.
	 */
	public boolean replaceEpisode(Episode episode) {
		if (this.episodeMap.containsKey(episode.getTitle())){
			this.episodeMap.put(episode.getTitle(), episode);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Clears the collection of episodes
	 */
	public void clearEpisodes() {
		this.episodeMap = new LinkedHashMap<String, Episode>();
	}	

	// TODO:  Add methods for object input and output
	// These should load and save the entire Series object
	// Model these after the object IO routines of Lab 4
	// "Hard code" the name of the data file for this to be "StarTrek.dat"
	public void load() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("StarTrek.dat");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		
		this.episodeMap = (LinkedHashMap<String, Episode>) objectInputStream.readObject();
		objectInputStream.close();
	}
	
	public void save(LinkedHashMap<String, Episode> episodeMap) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("StarTrek.dat");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(episodeMap);
		objectOutputStream.close();
	}
	
	// TODO:  Add method for printing to the console
	// This should print all data of the Series object to the console
	// The formatting is up to you but should be intuitive to the grader
	
	public void print(){
		Episode[] episodes = this.getEpisodeArray();
		System.out.println("TITLE: " + this.getTitle() + "\n START YEAR: " + this.getStartYear() 
			+ "\n END YEAR: " + this.getEndYear() + "\n EPISODES: " + episodes.toString());
	}
}