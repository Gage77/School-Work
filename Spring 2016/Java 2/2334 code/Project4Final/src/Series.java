
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Series extends Media {
	private Integer endYear;
	private List<Media> episodes;

	public Series(String title, Integer startYear, Integer endYear) {
		super(title, startYear);
		this.endYear = endYear;
		episodes = new ArrayList<Media>();
	}

	public Series(String seriesTitle, Integer startyear, Integer endyear2, ArrayList<Episode> episodeList) {
		super(seriesTitle, startyear);
		this.endYear = endyear2;
	}

	public void addEpisode(Episode obj) {
		episodes.add(obj);
	}

	public Integer getEndYear() {
		return this.endYear;
	}

	public List<Media> getEpisodes() {
		return this.episodes;
	}

	@Override
	public String getMediaType() {
		return "SERIES";
	}

	public String toString(Boolean includeEpisodes) {
		String temp = "SERIES: " + super.getTitle() + " (" + super.getReleaseYear() + "-" + this.endYear + ") \n";
		if(includeEpisodes == true){
			temp += this.episodes;
		}
		return temp;
	}
}