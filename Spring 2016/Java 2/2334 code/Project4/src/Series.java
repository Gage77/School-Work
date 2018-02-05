import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Series extends Media {
	private Integer endYear;
	private List<Media> episodes;

	public Series(String title, Integer startYear, Integer endYear, ArrayList<Episode> episodes) {
		super(title, startYear);
		this.endYear = endYear;
		this.episodes = (List)episodes;
	}
	
	public Series(String title, Integer startyear, Integer endYear){
		super(title, startyear);
		this.endYear = endYear;
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
	
	public void setEndYear(Integer endYear) {
		this.endYear = endYear;
	}

	public String toString(boolean includeEpisodes) {
		String temp = "SERIES: " + super.getTitle() + " (" + super.getReleaseYear() + "-" + this.endYear + ") \n";
		if(includeEpisodes == true){
			temp += this.episodes;
		}
		return temp;
	}
}