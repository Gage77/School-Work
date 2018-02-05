/**
 * This class will be used to construct and manage Movie objects.
 */
public class Movie extends Media {
	/**
	 * Stores which venue the Movie was released on.
	 */
	private String venue;

	/**
	 * A constructor.
	 * 
	 * @param title
	 *            The title of the Movie object.
	 * @param releaseYear
	 *            The release year of the Movie object.
	 * @param venue
	 *            The venue of the Movie object.
	 */
	public Movie(String title, Integer releaseYear, String venue) {
		super(title, releaseYear);

		this.venue = venue;

	}

	/**
	 * An accessor method.
	 * 
	 * @return Returns the venue of the Movie object.
	 */
	public String getVenue() {
		if (venue == "" || venue == null) {
			return this.venue;
		}
		return " (" + this.venue + ") ";
	}

	/**
	 * An accessor method. Will be used to isolate all Movie objects so they may
	 * be displayed in the view when the Movie button is pressed.
	 * 
	 * @return Returns every Media object of the media type "MOVIE" in String
	 *         form.
	 */
	

	/**
	 * Returns the media type of the Media object; in this case movie. Allows
	 * Media objects stored in a List to be seperated by media type.
	 * 
	 * @return Returns the string "MOVIE."
	 */
	@Override
	public String getMediaType() {
		return "MOVIE";
	}

	/**
	 * Allows the Movie object to be displayed in String format.
	 */
	public String toString() {
		return "MOVIE" + this.getVenue() + ":" + this.getTitle() + " (" + this.getReleaseYear() + ")";
	}
}