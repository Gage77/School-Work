import java.io.Serializable;
import java.util.ArrayList;

public class Actor extends MediaMaker {

	public Actor(String name) {
		super(name);
		super.setMakerType("Actor");
	}
	
	public String toString() {
		String makerInfo = super.toString();
		return makerInfo;
	}
}

