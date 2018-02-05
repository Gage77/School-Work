
public class Director extends MediaMaker {

	
	private static final long serialVersionUID = 1L;

	/**
	 * This is the base constructor for Director objects
	 * 
	 * @param name		Name to be set by the super method
	 */
	public Director(String name) {
		super(name, "DIRECTOR");
	}
	
	/**
	 * Overrides the toString method, returning a string representation
	 * of the object
	 */
	public String toString(){
		return "DIRECTOR: " + this.getName();
	}
}