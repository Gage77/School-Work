import java.util.Iterator;

public class Episode extends Media {
	private String seriesName;
	private Integer episodeNumber;
	private boolean suspended;

	public Episode(String title, Integer releaseYear, String seriesName, Integer episodeNumber) {
		super(title, releaseYear);
		this.seriesName = seriesName;
		this.episodeNumber = episodeNumber;
	}
	
	public Episode(String title, Integer releaseYear, String seriesName, boolean suspended){
		super(title, releaseYear);
		this.seriesName = seriesName;
		this.suspended = suspended;
	}
	
	public Episode(String episodeTitle, Integer endYear, String seriesTitle, int seasonNum, int episodeNum, Integer startYear, boolean suspended)
	{
		super(episodeTitle, startYear);
		this.seriesName = seriesTitle;
		this.episodeNumber = episodeNum;
		this.suspended = suspended;
	}

	public Episode(String episodeTitle, Integer episodeRelease, String title, int parseInt, int parseInt2, boolean suspended2) {
		super(episodeTitle, episodeRelease);
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public Integer getEpisodeNumber() {
		return this.episodeNumber;
	}

	@Override
	public String getMediaType() {
		return "EPISODE";
	}

	public String toString() {

		return "SERIES: " + this.seriesName + " EPISODE: " + super.getTitle() + " (" + super.getReleaseYear() + ") "
				+ "#" + this.episodeNumber + "\n";

	}
}