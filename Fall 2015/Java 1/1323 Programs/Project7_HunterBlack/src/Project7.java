import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * This program is a simple spell check, taking in
 * user input and comparing it to a dictionary file
 * @author Hunter Black
 */

public class Project7 {
	
	public static int findSizeOfDictionary(String filename)throws FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		int count = 0;
		
		//next loop finds size of dictionary.txt, storing # to count
		while (file.hasNextLine()) {
			count = count + 1;
			file.nextLine();
		}
		file.close();
		return count;
	}
	
	public static String[] readFile(String fileName, int fileSize) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName));
		String[] data = new String[fileSize];
		int i = 0;
		
		//creates array "data" with size of dictionary.txt
		for (i = 0; i < fileSize; ++i){
			data[i] = file.next();
		}
		
		file.close();
		return data;
	}
	
	public static void spellcheck(String[] dictionary) {
		String userWord;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word or QUIT to stop");
		userWord = input.nextLine();
		
		//sentinel controlled loop
		while (!userWord.equals("QUIT")){
			int value = Arrays.binarySearch(dictionary, userWord);
			
			//binarySearch returns positive # if userWord is in dictionary, and 
			//negative if it is not in dictionary
			if (value >= 0){
				System.out.println("That word is spelled correctly");
			}
			else {
				System.out.println("That word is not spelled correctly");
			}
			System.out.println("Enter a word or QUIT to stop");
			userWord = input.nextLine();
			}
		System.out.println("Thank you, goodbye");
		
		input.close();
		return;
	}
	
	public static void main(String[]args) throws FileNotFoundException {
		String filename = "Dictionary.txt";
		
		//gets size of dictionary.txt, storing size in variable size
		int size = findSizeOfDictionary(filename);
		
		//Creates array "data", which is then stored in array "dictionary"
		String[] dictionary = readFile(filename, size);
		
		//Compares user input against dictionary array
		spellcheck(dictionary);
		
		return;
	}

}
