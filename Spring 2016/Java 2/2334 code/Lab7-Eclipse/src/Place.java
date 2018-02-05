import java.util.ArrayList;

/**
 * Lab #7 CS 2334, Section 0?? DATE GOES HERE
 * <P>
 * This class represents places, which are named locations that have populations over time.
 * </P>
 * 
 * @version 1.0
 */
public class Place extends NamedLocation {
	private ArrayList<PopulationRecord> populationHistory = new ArrayList<PopulationRecord>();

	/**
	 * @param name
	 * @param population
	 * @param year
	 */
	public Place(String name, String population, String year) {
		super(name);
		populationHistory.add(new PopulationRecord(population, year));
	}

	/**
	 * @param name
	 * @param populationRecord
	 */
	public Place(String name, PopulationRecord populationRecord) {
		super(name);
		populationHistory.add(populationRecord);
	}

	/**
	 * @param tokens
	 *            The place name followed by pairs of population and year info in
	 *            String array.
	 */
	public Place(String[] tokens) {
		super(tokens[0].trim());
		for (int i = 1; i + 1 < tokens.length; i += 2)
			populationHistory.add(new PopulationRecord(tokens[i].trim(),
					tokens[i + 1].trim()));
	}
	
	public Place (Place place) {
		super(place);
		populationHistory = new ArrayList<PopulationRecord>(place.getHistory());
	}

	/**
	 * @return the population history for a place
	 */
	public ArrayList<PopulationRecord> getHistory() {
		return populationHistory;
	}

	/**
	 * @param populationRecord
	 *            the new population record to add to the population history
	 */
	public void addToHistory(PopulationRecord populationRecord) {
		populationHistory.add(populationRecord);
	}

	public String toString() {
		return super.toString() + " " + populationHistory;
	}
}
