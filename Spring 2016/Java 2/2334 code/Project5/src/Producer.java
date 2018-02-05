public class Producer extends MediaMaker{

	
	private static final long serialVersionUID = 1L;

	/**
	 * This is the base constructor for Prodcuer objects
	 * 
	 * @param name		Name to be set by the super method
	 */
	public Producer(String name) {
		super(name, "PRODUCER");
	}
	
	/**
	 * Overrides the toString method, returning a string representation 
	 * of the object
	 */
	public String toString(){
		return "PRODUCER: " + this.getName();
	}
}