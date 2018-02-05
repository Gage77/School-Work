import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

/**
 * Here is where you should write your unit tests for Lab8
 * 
 * @author YOUR LAB SECTION HERE
 *
 */
public class Lab8Tests {

	
	/**
	 * Here are 3 test methods you can use to test out the IntegerSearcher class.
	 * 
	 */
	@Test
	public void binarySearchCornerCase() {
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int key = 10;
		int num = IntegerSearcher.recursiveBinarySearch(a, key, 0, a.length - 1);
		
		assertEquals(10, num);
		
	}
	
	@Test
	public void binarySearchInvalidValue() {
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int key = 55;
		int num = IntegerSearcher.recursiveBinarySearch(a, key, 0, a.length - 1);
		
		assertEquals(-1, num);
	}
	
	@Test
	public void binarySearchRandomNumber() {
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int key = 3;
		int num = IntegerSearcher.recursiveBinarySearch(a, key, 0, a.length - 1);

		assertEquals(3, num);
	}
	
	/**
	 * BONUS QUESTION TEST
	 * 
	 * Be sure to write a test for the adder function if you would like extra credit!
	 * 
	 */
	@Test
	public void testAdder() {
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int num = Adder.recursiveSum(a, 0, 5, 0);
		
		assertEquals(15, num);
	}

}
