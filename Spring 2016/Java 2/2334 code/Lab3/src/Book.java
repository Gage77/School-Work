import java.util.ArrayList;


/**
 * Lab #3
 * CS 2334, Section 013
 * February 8, 2016
 * <P>
 * This class provides a very simple model for a book.
 * A Book has a title, an author, a publication year, and one or more genres.
 * </P>
 * @author Dean Hougen and Hunter Black, Grant Patovisti, Cole Howe 
 * @version 1.0
 */
public class Book implements Comparable<Book> 
{
		
	/** The title of the book. */
	private String title;
	
	/** The name of the book's author. */
	private String authorName;
	
	/** The year the book was published. */
	private int year;
	
	/** The genre(s) of the book. */
	private ArrayList<String> genres;
	
	/**
	 * This is the default constructor for the class.
	 */   
	public Book() 
	{
		this.title = "";
		this.authorName = "";
		this.year = -1;
		this.genres = null;
	}

	/**
	 * This is a constructor for the class. 
	 * It instantiates the class with user-supplied values.
	 * This version of the constructor is used when only one genre is provided.
	 * <P>
	 * @param        title	            	The title of the book.
	 * @param 		 authorName 			The name of the book's author.
	 * @param 		 year 					The year that the book was published.
	 * @param        genre      	        The genre of the book.
	 */   
	public Book (String title, String lastName, int year, String genre) 
	{
		this.title = title;
		this.authorName = lastName;
		this.year = year;
		this.genres = new ArrayList<String>();
		genres.add(genre);
	}
	
	/**
	 * This is a constructor for the class. 
	 * It instantiates the class with user-supplied values.
	 * This version of the constructor is used when multiple genres are provided.
	 * <P>
	 * @param        title		        	The title of the book.
	 * @param 		 authorName 			The name of the book's author.
	 * @param 		 year 					The year that the book was published.
	 * @param        genres			      	The genres of the book.
	 */   
	public Book (String title, String authorName, int year, ArrayList<String> genres) 
	{
		this.title = title;
		this.authorName = authorName;
		this.year = year;
		this.genres = genres;
	}
	
	/**
	 * This method returns the attributes of this class as a single string.
	 * </P>
	 * @return            String representing the contents of this object.
	 */
	public String toString()
	{
		// Add the code for toString() here.
		String info;
		info = title + ", " + authorName + ", " + year + ", " + this.genres.toString();
		return info; // Make sure you replace this return statement.	
	}

	/**
	 * This method compares an instance of this Book, with another
	 * instance of Book.
	 * <P>
	 * Algorithm:<br>
	 * Compare the passed objects title with the original objects title, comparing
	 * alphabetically from a-z, and returning negative number if the passed object's title is 
	 * alphabetically before, 0 if they're the same, and positive number if alphabetically ahead
	 * </P>
	 * @param             Book        The object to which we are comparing
	 *                                this instance of Book.
	 * @return            int		  Positive if object being compared is greater, 0 if the same,
	 * 								  negative if less than
	 * @override
	 */    
	public int compareTo(Book other)
	{
		// Add the code for compareTo() here.
		int titleDiff = this.title.compareTo(other.title);
		if (titleDiff != 0)
			return titleDiff; // Make sure you replace this return statement.
		return 0;
	}
}