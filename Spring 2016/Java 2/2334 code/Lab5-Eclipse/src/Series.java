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
public class Series {
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
	 * @param episode
	 *            the episode to add to the episodeMap
	 */
	public void addEpisode(Episode episode) {
		this.episodeMap.put(episode.getTitle(), episode);
	}

	/**
	 * Clears the collection of episodes
	 */
	public void clearEpisodes() {
		this.episodeMap = new LinkedHashMap<String, Episode>();
	}
}