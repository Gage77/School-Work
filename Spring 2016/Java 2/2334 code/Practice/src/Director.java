import java.util.ArrayList;

public class Director 
{
	private String name;
	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<Episode> episodes = new ArrayList<Episode>();
	
	public Director() {
		
	}
	
	public Director(String name) {
		this.name = name;
	}
	
	public void addMovieCredit(Movie m) {
		movies.add(m);
	}
	
	public void addEpisodeCredit(Episode e) {
		episodes.add(e);
	}
	
	public ArrayList<Movie> getMovieCredits() {
		return this.movies;
	}
	
	public ArrayList<Episode> getEpisodeCredits() {
		return this.episodes;
	}
	
	public void removeMovieCredit(int index) {
		movies.remove(index);
	}
	
	public void removeEpisodeCredit(int index) {
		episodes.remove(index);
	}
	
	public String toString() {
		return "DIRECTOR: " + this.name;
	}
}
