import java.io.Serializable;
import java.util.ArrayList;


public class Director extends MediaMaker{

	public Director(String name) {
		super(name);
		super.setMakerType("Director");
	}
	
	public String toString() {
		String makerInfo = super.toString();
		return makerInfo;
	}

}