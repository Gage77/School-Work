import java.io.*;
import java.util.Scanner;

/** This project was inspired by word jumbles.  The program will randomly choose a word
 * from a dictionary, randomly arrange the letters and give the user three chances to guess 
 * the word.  If the user guesses correctly, they win, otherwise they lose.  The game is repeated
 * as many times as the user wishes.
 * 
 * @author Deborah A. Trytten
 * @version 1.0
 *
 */
public class WordJumble 
{
	
	public static String[] readDictionary(String filename) throws FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		int wordsInDictionary = 0;
		int i = 0;
		
		while (file.hasNext()){
			++wordsInDictionary;
			file.next();
		}
		file.close();
		file = new Scanner (new File(filename));
		
		String[] dictionary = new String[wordsInDictionary];
		for (i = 0; i < dictionary.length && file.hasNextLine(); ++i){
			dictionary[i] = file.next();
		}

		file.close();
		
		return dictionary;
		
	}
	
	public static String scrambledWordGenerator(String chosenWord) {
		
		// mix up the letters randomly
		StringBuilder tempWord = new StringBuilder(chosenWord);
		StringBuilder scrambledTempWord = new StringBuilder();
		
		// Repeatedly choose a letter to remove from the current word, and put these randomly
		// chosen letters in order in a new word.  This uses the StringBuilder class, which is like String
		// but has different methods--including delete and append which are useful here
		// You do not need to understand this part of the code to use it!
		while (tempWord.length() > 0)
		{
			int letterNumber = (int) (Math.random()*tempWord.length());
			char nextLetter = tempWord.charAt(letterNumber);
			tempWord.deleteCharAt(letterNumber);
			scrambledTempWord.append(nextLetter);
		}
		String scrambledWord = scrambledTempWord.toString();
		
		return scrambledWord;
	}
	
	public static boolean guessAndCheck(String chosenWord, String scrambledWord, int TRIES, Scanner keyboard) {
		int guesses = 0;
		String guessedWord="";  // keep the compiler happy
		while (guesses < TRIES)
		{
			System.out.println("The scrambled word is " + scrambledWord);
			System.out.println("What is your guess?");
			guessedWord = keyboard.nextLine();
			guessedWord = guessedWord.toLowerCase();
			++guesses;
			if (guessedWord.equals(chosenWord))
				guesses = TRIES; // stop the loop
		}
		boolean won = (guessedWord.equals(chosenWord));
		
		return won;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner keyboard = new Scanner(System.in);
		final String fileName = "dictionary.txt";
		
		String[] dictionary = readDictionary(fileName);
				
		//Play this game repeatedly
		String repeatPlay="yes"; // Used at the end of the loop to determine if the game should be played again
		
		int wins = 0;
		int losses = 0;
		final int TRIES = 3; // the number of guesses a user is allowed
		
		while (repeatPlay.equalsIgnoreCase("yes"))
		{		
			int randomCount = (int)(Math.random() * dictionary.length - 1);
			String chosenWord = dictionary[randomCount];
			
			String scrambledWord = scrambledWordGenerator(chosenWord);
			
			// Give the  user 3 chances to guess the word
			
			boolean won = guessAndCheck(chosenWord, scrambledWord, TRIES, keyboard);
			
			//If they didn't succeed, tell them the word, otherwise congratulate them
			if (won)
			{
				System.out.println("Congratulations!  You guessed the word correctly!");
				++wins;
			}
			else
			{
				System.out.println("I'm sorry but you lost.  The word was: " + chosenWord);
				++losses;
			}
			
			// Report the number of wins and losses
			System.out.println("You have won " + wins + " times.");
			System.out.println("You have lost " + losses + " times.");
		
			// Let them choose to play again
			System.out.println("Do you want to play again? Yes or no");
			repeatPlay = keyboard.nextLine();
			

		}
		keyboard.close();
	}

}
