import java.util.*;

/**
 * Lab #3 CS 2334, Section 013 February 8, 2016
 * <P>
 * This class implements a program that tests the Book class code.
 * </P>
 * 
 * @author Dean Hougen and Hunter Black, Grant Patovisti, Cole Howe
 * @version 1.0
 */
public class Lab3Driver 
{

	/**
	 * This is the main method for this driver program.
	 * <P>
	 * Algorithm<br>
	 * Test out the Book class and an ArrayList of them to help your
	 * understanding of creating, sorting, and searching lists.
	 * </P>
	 * 
	 * @param args
	 *            Contains the command line arguments.
	 */

	public static void main(String[] args) 
	{
		ArrayList<Book> books = new ArrayList<Book>();

		/*
		 * Instantiate eight objects of type Book and add them to the list
		 * books. I have created and added the first Book for you. :)
		 */

		/*
		 * This is a list of the genres that we create for one Book. This list
		 * is referenced by the variable "genres. You can add to this list by
		 * invoking the add method in ArrayList.
		 */
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Autobiography");
		genres.add("Adventure");
		genres.add("History");

		Book firstBook = new Book("There and Back Again", "B.B.", 3018, genres);

		/*
		 * The Book list is referenced by the variable books. You can add to this list
		 * by invoking the add method in ArrayList.
		 */
		books.add(firstBook);

		/*
		 * Create and add the other seven Book objects here.
		 */
		Book secondBook = new Book("Dune", "Frank Herbert", 1965, "Sci-Fi");
		books.add(secondBook);
		
		genres.removeAll(genres);
		genres.add("Horror");
		genres.add("Adventure");
		Book thirdBook = new Book("It", "Stephen King", 1986, genres);
		books.add(thirdBook);
		
		genres.removeAll(genres);
		genres.add("Adventure");
		genres.add("Fantasy");
		Book fourthBook = new Book("Silmarillion", "J.R.R. Tolkien", 1977, genres);
		books.add(fourthBook);
		
		Book fifthBook = new Book("The Hobbit", "J.R.R. Tolkien", 1937, genres);
		books.add(fifthBook);
		
		Book sixthBook = new Book("Enders Game", "Orson Scott Card", 1985, "Sci-Fi");
		books.add(sixthBook);
		
		Book seventhBook = new Book("The Eye of the World", "Robert Jordan", 1990, genres);
		books.add(seventhBook);
		
		Book eighthBook = new Book("Steve Jobs", "Walter Isaacson", 2011, "Biography");
		books.add(eighthBook);

		/*
		 * Print out the unsorted list of books. This uses an iterator to
		 * "iterate" through the list.
		 */
		System.out.println("\n\nUnsorted List:");

		Iterator<Book> iterator = books.iterator();

		while (iterator.hasNext()) {
			// Note: This line of code will automatically call the toString
			// method of the Book class.
			System.out.println(iterator.next());
		}

		/*
		 * Sort the list of Books using Collections.sort(). Take a look at
		 * Collections.sort() in the API at
		 * http://download.oracle.com/javase/8/docs/api/java/util/Collections.html#sort%28java.util.List%29
		 * 
		 * You need to call Collections.sort() and pass it the list of Books.
		 * SEE THE LAB HANDOUT FOR MORE INFORMATION.
		 */

		Collections.sort(books);

		/*
		 * Print out the sorted list of Books.
		 */
		System.out.println("");
		System.out.println("Sorted List:");
		/*
		 * HINT: Use an iterator the same way I used one above when the unsorted
		 * list of Books was printed.
		 */

		iterator = books.iterator();
		while (iterator.hasNext()) 
		{
			System.out.println(iterator.next());
		}

		/*
		 * Search for a particular Book in the list.
		 */
		System.out.println("\n\nSearching");
		Book key = new Book("Analysis: The Arrakeen Crisis", "Princess Irulan", 10282, "Politics");
		System.out.println("Key is " + key);

		/*
		 * Call Collections.binarySearch() to find the index of key. Make sure
		 * you test the value of index to see if it negative, which indicates
		 * that the key was not found in the list.
		 */

		int index = Collections.binarySearch(books, key);

		/*
		 * Print out whether the Book was found or not and the index at which it
		 * was found. HINT: If index is negative print a statement saying that
		 * the item searched for is not in the list. Otherwise, print out a
		 * statement telling that item was found in the list and give the index
		 * of item in the list as well.
		 */

		System.out.println("");
		index = Collections.binarySearch(books, key);
		if (index < 0)
		{
			System.out.println("The item searched for is not in the specified list");
		}
		else if (index > 0)
		{
			System.out.println("Item found in specified list at index: " + index);
		}
		
		//Bonus
		System.out.println("");
		System.out.println("List printed again with a for each loop:");
		
		for (Book printer : books)
		{
			System.out.println(printer);
		}

	}
}