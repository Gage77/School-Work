
public class Episode {
	
	private String eTitle = "";
	private String sTitle = "";
	private String eNumber = "";
	private String year = "";
	
	public Episode() {
		
	}
	
	public Episode(String eTitle, String sTitle, String eNumber, String year) {
		this.eTitle = eTitle;
		this.sTitle = sTitle;
		this.eNumber = eNumber;
		this.year = year;
	}
	
	public String toString() {
		return "EPISODE: " + "(" + this.sTitle + "): " + this.eTitle + "(" + this.eNumber + ") " + this.year;
	}

}
