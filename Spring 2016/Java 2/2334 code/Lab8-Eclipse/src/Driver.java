import java.util.Scanner;

/**
 * Lab #8 CS 2334 DATE GOES HERE
 * <P>
 * This class implements the driver.
 * </P>
 * 
 * @version 1.0
 */
public class Driver {

	/**
	 * 
	 * @param args
	 * 
	 *            This program does not use command line arguments.
	 */
	public static void main(String[] args) {
		// This is true if you are doing the bonus part of this lab.
		final boolean DOINGBONUS = false;

		/**
		 * 
		 * Part I. Search for a given integer in an array of integers.
		 * 
		 * 
		 */

		final int ARRAYSIZE = 500;

		/* The sorted list that we will search. */
		int[] array = new int[ARRAYSIZE];

		// Fill the array with values
		for (int i = 0; i < ARRAYSIZE; i++) {
			array[i] = 2 * i;
		}

		// Ask the user for a key to search.
		System.out.println("Enter an integer to search for.");
		Scanner sc = new Scanner(System.in);
		int key = sc.nextInt();
		
		// Call the recursive Integer Binary Search.
		int indexOfKey = IntegerSearcher.recursiveBinarySearch (array, key, 0,
				ARRAYSIZE - 1);

		// Output the result of the integer binary search.
		if (indexOfKey < 0) {
			System.out.println("The key: " + key
					+ " couldn't be found in the array.");
			return;
		}
		else {
			System.out.println("The key: " + key + " was found at position "
					+ indexOfKey + ".");
		}

		/**
		 * BONUS PART:
		 * 
		 * Part II. Add all the integers in that array up to and including
		 * indexOfKey.
		 * 
		 * 
		 */
		if (DOINGBONUS) {
			int sumElementsUpToIndex = Adder.recursiveSum (array, 0,
					indexOfKey, 0);

			System.out
			.println("The sum of the elements in the array from index 0 up to "
					+ " (including) index "
					+ indexOfKey
					+ " is: "
					+ sumElementsUpToIndex);
		}

		/** End of the BONUS PART */
	}
}
