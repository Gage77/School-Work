
public class Director extends MediaMaker {

	/**
	 * This is the base constructor for Director objects
	 * 
	 * @param name		Name to be set by the super method
	 */
	public Director(String name) {
		super(name);
		super.setMakerType("DIRECTOR");
	}
	
	/**
	 * Overrides the toString method, returning a string representation
	 * of the object
	 */
	public String toString(){
		return "DIRECTOR: " + this.getName();
	}
}