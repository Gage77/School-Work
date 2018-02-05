import java.util.ArrayList;

public class MediaMaker {
	private String name;
	private String makerType;
	private ArrayList<Media> credits;

	MediaMaker(String name) {
		this.name = name;
		this.credits = new ArrayList<Media>();
		this.setMakerType(null);
	}

	public MediaMaker() {
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCredit(Media obj) {
		credits.add(obj);
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
	
}