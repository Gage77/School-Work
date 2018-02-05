/**
 * Lab #8  
 * CS 2334
 * DATE GOES HERE
 * <P>
 * This class implements a recursive binary search over integer arrays.
 * 
 * </P>
 * 
 * @version 1.0
 */
public class IntegerSearcher {
	
	/**
	 * <P>
	 * This is the implementation of a binary search over an integer
	 * list. 
	 * </P>
	 * This method requires that list is sorted and then we search
	 * for key in the sub-array: array[left]....array[right], including
	 * BOTH ends.
	 * 
	 * @requires   The list must be sorted in increasing order.
	 *
	 * @param array  Must be sorted in increasing order.
	 * @param key    The integer that we are looking for.
	 * @param left   The first index of the sub-array over which to search
	 * @param right  The last index of the sub-array over which to search,
	 * 
	 * 
	 * @return  -1 if the key is not in the array. 
	 *           A positive index in [left,right] if key is in the array.
	 */
	public static int recursiveBinarySearch(int[] array, int key,
											int left, int right) 
	{
		/*
		 *  Base case 1: The array [left...right] is empty.
		 * 
		 *  Determine what condition must left and right satisfy for the 
		 *  array array[left...right] to be empty.
		 */			
		if (left > right) { 
			return -1;  
		}
		
		/*
		 * Find the mid-point of the sublist array[left...right] 
		 */
		int mid = (left + right) / 2;  //how will we get this from our parameters?

		/*
		 * Base case 2: The key was found at array[mid].	
		 */
		if (array[mid] == key) {
			return mid;
		}

		/* 
		 * 
		 * Recursive cases: 
		 * 
		 * At this point, you know that the array array[left..right] is not empty
		 * and that the key is not the middle element.
		 * 
		 */
		
		if (array[mid] < key) {  //what condition should cause another call?
			/** 
			 * Remember, we must reduce the size of the problem at each call,
			 * and it must terminate. This is, we must search over a <smaller>
			 * sub-list.
			 *  
			 * */
			return recursiveBinarySearch(array, key, mid + 1, right); //need parameters!!!
				
		}
		else {  // what is the only condition remaining?
			/** 
			 * Remember, we must reduce the size of the problem at each call,
			 * and it must terminate. This is, we must search over a <smaller>
			 * sub-list.
			 *  
			 * */
		    return recursiveBinarySearch(array, key, left, mid - 1); //need parameters!!!
		}
	}
}
