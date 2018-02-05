
import java.util.ArrayList;
import java.util.List;

public class Series extends Media {
	
	private static final long serialVersionUID = 1L;
	private String yearRange;
	private ArrayList<Media> episodes = new ArrayList<Media>();

	public Series(String title, String releaseYear, String yearRange) {
		super(title, releaseYear);
		this.yearRange = yearRange;
	}

	public void addEpisode(Episode obj) {
		episodes.add(obj);
	}

	public String getYearRange() {
		return this.yearRange;
	}

	public List<Media> getEpisodes() {
		return this.episodes;
	}

	@Override
	public String getMediaType() {
		return "SERIES";
	}

	public String toString() {
		return "SERIES: " + this.title + " (" + this.releaseYear + ")       " + this.yearRange;	
	}
}