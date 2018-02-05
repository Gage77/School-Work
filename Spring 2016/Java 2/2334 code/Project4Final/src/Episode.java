import java.util.Iterator;

public class Episode extends Media {
	private String seriesName;
	private Integer episodeNumber;

	/**
	 * This is the base constructor for Episode objects
	 * 
	 * @param title				Title to be set by the super method in Media class
	 * @param releaseYear		Release year to be set by  the super method in Media class
	 * @param seriesName		Name of the series this episode is connected to
	 * @param episodeNumber		Number of the episode in the series
	 */
	public Episode(String title, Integer releaseYear, String seriesName, Integer episodeNumber) {
		super(title, releaseYear);
		this.seriesName = seriesName;
		this.episodeNumber = episodeNumber;
	}

	/**
	 * Overloaded constructor
	 * 
	 * @param episodeTitle		Title to be set by the super method in Media class
	 * @param endyear			End year of the Episode
	 * @param seriesTitle		Title of the series this episode is in
	 * @param seasonNum			Number of the season
	 * @param episodeNum		Number of the episode in the season
	 * @param startyear			Release year to be set by the super method in Media Class
	 * @param suspended			Whether or not the episode was suspended
	 */
	public Episode(String episodeTitle, Integer endyear, String seriesTitle, int seasonNum, int episodeNum,
			Integer startyear, boolean suspended) {
		super(episodeTitle, startyear);
		this.seriesName = seriesTitle;
		this.episodeNumber = episodeNum;
	}

	public Episode(String episodeTitle, Integer startyear, String seriesTitle, boolean suspended) {
		super(episodeTitle, startyear);
		this.seriesName = seriesTitle;
	}

	public Episode(String episodeTitle, Integer episodeRelease, String title, int parseInt, int parseInt2,
			boolean suspended) {
		super(episodeTitle, episodeRelease);	
	}

	/**
	 * Sets the name of the series
	 * 
	 * @param seriesName		Name of series to set
	 */
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	/**
	 * Sets the episode number 
	 * 
	 * @param episodeNumber		Number of the episode
	 */
	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/**
	 * Returns the name of the series this episode is in
	 * 
	 * @return		Name of the series 
	 */
	public String getSeriesName() {
		return this.seriesName;
	}

	/**
	 * Returns the number of the episode
	 * 
	 * @return		Number of the episode
	 */
	public Integer getEpisodeNumber() {
		return this.episodeNumber;
	}

	/**
	 * This method overrides the super class's getMediaType method,
	 * returning "Episode"
	 */
	@Override
	public String getMediaType() {
		return "EPISODE";
	}

	/**
	 * Overrides the toString method, returning a string representation
	 * of the object
	 */
	public String toString() {

		return "SERIES: " + this.seriesName + " EPISODE: " + super.getTitle() + " (" + super.getReleaseYear() + ") "
				+ "#" + this.episodeNumber + "\n";

	}
}