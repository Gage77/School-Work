/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE
 * <P>
 * This class represents a record of an place's population over time.
 * </P>
 * 
 * @version 1.0
 */

public class PopulationRecord {
	private String population;
	private String year;

	/**
	 * @param population
	 * @param year
	 */
	public PopulationRecord(String population, String year) {
		this.population = population;
		this.year = year;
	}

	/**
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setEvent(String population) {
		this.population = population;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public String toString() {
		return population + ": " + year;
	}
}