
public class Movie 
{
	
	private String title;
	private String releaseYear;
	private String venue;
	
	public Movie() {
		
	}
	
	public Movie(String title, String releaseYear, String venue) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.venue = venue;
	}
	
	public String toString() {
		return "MOVIE: (" + this.venue + ") " + this.title + " " + this.releaseYear;
	}
}