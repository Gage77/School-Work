import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


public class Media implements Comparable<Media>, Serializable{
	
	private static final long serialVersionUID = 1L;
	/** Stores the title of a Media object. Can be accessed with the 'getTitle()' method */
	protected String title;
	/** Stores the release year of a Media object. Can be accessed with the 'getReleaseYear()' method */
	protected String releaseYear;
	/** List of all MediaMakers associated with this media */
	private ArrayList<MediaMaker> makers = new ArrayList<MediaMaker>();
	/** Stores a list of all actors for a Media object */
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	/** Stores a list of all directors for a Media object */
	private ArrayList<Director> directors = new ArrayList<Director>();
	/** Stores a list of all producers for a Media object */
	private ArrayList<Producer> producers = new ArrayList<Producer>();
	
	/**
	 * This is the base constructor for Media objects
	 */
	public Media() {
		
	}

	/**
	 * A constructor.
	 * 
	 * @param title
	 *            The title of a Media object.
	 */
	public Media(String title) {
		this.title = title;
	}

	/**
	 * A constructor
	 * 
	 * @param title
	 *            The title of a Media object.
	 * @param releaseYear
	 *            The year a Media object was released.
	 */

	public Media(String title, String releaseYear) {
		this.title = title;
		this.releaseYear = releaseYear;
	}
	
	/**
	 * Mutator method that adds an actors name to a Media objects list of
	 * actors.
	 * 
	 * @param name
	 *            The name of the actor.
	 */
	public void addActor(Actor a) {
		this.actors.add(a);
		this.makers.add(a);
	}

	/**
	 * Mutator method that adds a directors name to a Media objects list of
	 * directors.
	 * 
	 * @param name
	 *            The name of the director.
	 */
	public void addDirector(Director d) {
		this.directors.add(d);
		this.makers.add(d);
	}

	/**
	 * Mutator method that adds a producers name to a Media objects list of
	 * producers.
	 * 
	 * @param name
	 *            The name of the producer.
	 */
	public void addProducer(Producer p) {
		this.producers.add(p);
		this.makers.add(p);
	}

	/**
	 * Allows a Media objects title to be accessed.
	 * 
	 * @return The media objects title.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Allows a Media objects release year to be accessed.
	 * 
	 * @return The media objects release year.
	 */
	public String getReleaseYear() {
		return this.releaseYear;
	}
	
	/**
	 * This method will return the associated list of MediaMakers
	 * 
	 * @return	The MediaMaker objects associated with this Media objects
	 */
	public ArrayList<MediaMaker> getMakers() {
		return this.makers;
	}

	/**
	 * Allows a Media objects list of actors to be accessed.
	 * 
	 * @return The media objects list of actors.
	 */
	public ArrayList<Actor> getActors() {
		return this.actors;
	}

	/**
	 * Allows a Media objects list of directors to be accessed.
	 * 
	 * @return The media objects list of directors.
	 */
	public ArrayList<Director> getDirectors() {
		return this.directors;
	}

	/**
	 * Allows a Media objects list of producers to be accessed.
	 * 
	 * @return The media objects list of producers.
	 */
	public ArrayList<Producer> getProducers() {
		return this.producers;
	}

	/**
	 * To be Overridden by Media subclasses. Allows the static List of Media
	 * objects to be separated by media type.
	 * 
	 * @return Returns a string with the Media object's media type.
	 */
	public String getMediaType() {
		return "";
	}

	/**
	 * Overwritten method. Should compare the title of Media Objects.
	 */
	@Override
	public int compareTo(Media arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
	final class YearComparator implements Comparator<Media>{

		@Override
		public int compare(Media obj1, Media obj2) {
			return obj1.getReleaseYear().compareTo(obj2.getReleaseYear());
		}}

