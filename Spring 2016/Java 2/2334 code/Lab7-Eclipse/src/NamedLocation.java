/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE
 * <P>
 * This class represents a named location. It is a very simple representation
 * that only includes a name.
 * </P>
 * 
 * @version 1.0
 */
public class NamedLocation {
	private String name;

	/**
	 * @param name
	 */
	public NamedLocation(String name) {
		this.name = name;
	}

	/**
	 * Copy constructor
	 * 
	 * @param namedLocation
	 *            the namedLocation to copy
	 */
	public NamedLocation(NamedLocation namedLocation) {
		// No copy needed for name because Strings are immutable
		this.name = namedLocation.getName();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}