import java.util.ArrayList;

public class MediaMaker {
	private String name;
	private String makerType;
	private ArrayList<Media> credits;
	
	public MediaMaker()
	{
		
	}

	MediaMaker(String name) {
		this.name = name;
		this.credits = new ArrayList<Media>();
		this.setMakerType(null);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCredit(Media obj) {
	}

	public void removeCredit(Media obj) {
	}

	public void replaceCredit(Media obj) {
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Media> getCredits() {
		return this.credits;
	}

	public String getMakerType() {
		return makerType;
	}

	public void setMakerType(String makerType) {
		this.makerType = makerType;
	}

	public void addMovieActed(Movie movies) {
		credits.add(movies);
	}

	public void addSeriesActed(Series series) {
		credits.add(series);
		
	}

	public void addObject(String fullName, MediaMaker person) {
		this.name = fullName;		
	}
	
	public String toString() {
		String makerInfo;
		makerInfo = this.makerType + ": " + this.name + "\n";
		for (int i = 0; i < credits.size(); ++i)
		{
			makerInfo = makerInfo + credits.get(i).toString() + "\n";
		}
		
		return makerInfo;
	}
		
}
