

public class Lab2 {
	/** The int array. */
	int[] myArray;

	/** The size of array. */
	int iSize;

	public Lab2(int iSize){
		/* Assign a new size. */
		this.iSize = iSize;

		/* Initialize the array. */
		myArray = new int[iSize];

		/* Assign some random integer to each cell of the array. */
		init();

		/* Further processing the array: the value of next cell is 
		 * the sum of its current value and its previous neighbor's. 
		 */
		work();

		System.out.println();
		
	}

	/**
	 *  Assign some random integer to each cell of the array. 
	 */
	public void init(){
		for (int i = 0; i < iSize; i++) {
			myArray[i] = 1;
		}
	}

	/**
	 * Perform addition on each cell of myArray.
	 */
	public void work(){
		for (int index = 1; index < myArray.length; index++) {
			myArray[index] = myArray[index-1] + myArray[index];
		}
	}


}
