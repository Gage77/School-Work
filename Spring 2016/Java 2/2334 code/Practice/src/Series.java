import java.util.ArrayList;

public class Series 
{
	private String title = "";
	private String releaseYear = "";
	private String yearRange = "";
	
	private ArrayList<Episode> episodes = new ArrayList<Episode>();
	
	public Series() {
		
	}
	
	public Series(String title, String releaseYear, String yearRange) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.yearRange = yearRange;
	}
	
	public void addEpisode(Episode e) {
		episodes.add(e);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getReleaseYear() {
		return releaseYear;
	}
	
	public String getYearRange() {
		return yearRange;
	}
	
	public ArrayList<Episode> getEpisodes() {
		return this.episodes;
	}
	
	public String toString() {
		return "SERIES: " + this.title + " " + this.releaseYear + "\t" + this.yearRange;
	}
}
