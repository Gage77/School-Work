/**
 * Lab #5
 * CS 2334, Section SECTION NUMBER GOES HERE
 * DATE GOES HERE
 * <p>
 * Each object of this class represents a episode.
 * </p>
 * @version 1.0
 *
 */
public class Episode {
	/** Title (name) of the Episode */
	private String title;

	/** Season number of the Episode */
	String seasonNumber;

	/** Episode number of the Episode */
	String episodeNumber;

	/** Year of the Episode */
	String year;
	
	/**
	 * @param title
	 * @param seasonNumber
	 * @param episodeNumber
	 * @param year
	 */
	public Episode(String title, String seasonNumber, String episodeNumber, String year) {
		this.setTitle(title);
		this.setSeasonNumber(seasonNumber);
		this.setEpisodeNumber(episodeNumber);
		this.setYear(year);
	}

	/**
	 * @return the title of the episode
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set as the episode title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the number of the season
	 */
	public String getSeasonNumber() {
		return seasonNumber;
	}
	
	/**
	 * @param seasonNumber the number of the season to set
	 */
	public void setSeasonNumber(String seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	/**
	 * @return the number of the episode
	 */
	public String getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * @param episodeNumber the number of the episode to set
	 */
	public void setEpisodeNumber(String episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/**
	 * @return the year of the episode
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set for the episode
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * @return the episode as a String
	 */
	public String toString() {
		return title + " (#" + seasonNumber + ", " + episodeNumber + ") " + year;
	}
}
