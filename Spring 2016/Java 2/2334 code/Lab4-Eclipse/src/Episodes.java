import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Lab #4 CS 2334, Section 0?? February 2?, 2016
 * <P>
 * This class models the episodes of a series.
 * </P>
 * 
 * @author
 * @version 1.0
 */
public class Episodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** List of episodes */
	List<Episode> episodes = null;

	/**
	 * This is the default constructor for Episodes.
	 */
	public Episodes() {
		this.episodes = new ArrayList<Episode>();
	}

	/**
	 * This method is used to add an episode to the list of episodes.
	 * 
	 * @param episode
	 */
	public void addEpisode(Episode episode) {
		// TODO This constructor really should check to ensure that the values
		// of this episode do not conflict with those of other episodes or of
		// the series itself. For example, there should not be two episodes with
		// the same season and episode number combination in the same series.
		// Similarly, the year of the episode should be within the range of
		// years of the series.
		episodes.add(episode);
	}

	/**
	 * This method returns the attributes of this class as a single string with
	 * one resident per line.
	 * 
	 * @return String representing the contents of this object.
	 */
	public String toString() {
		String attributesAsString = "";
		for (Episode episode : episodes) {
			attributesAsString += episode + "\n";
		}
		return (attributesAsString);
	}
}