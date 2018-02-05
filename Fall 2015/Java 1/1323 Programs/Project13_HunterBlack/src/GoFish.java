
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GoFish 
{
	
	public static int STARTING_HAND_SIZE = 7;
	
	/** Play a game of Go Fish!  The rules are below.
	 * A regular deck of cards consists of 52 cards.  
	 * There are four suits and thirteen card ranks (Ace, 2, 3,…10, Jack, Queen, and King). 
	 * We’re going to simplify our cards.  The cards will have ranks from 1 to 13, 
	 * and each rank will have identical cards.  This removes suit from the game.
	 * 
	 * The computer deals seven cards to the human and the computer from a shuffled deck. The 
	 * remaining cards are shared in a pile.
	 * 
	 * The human player should play first. The human asks the computer for all its card(s) 
	 * of a particular rank that is already in his or her hand. 
	 * For example Alice may ask, "Computer, do you have any threes?" Alice must have at 
	 * least one card of the rank she requested in her hand. The computer must hand over 
	 * all cards of that rank. If the computer has no cards of that rank, 
	 * Alice is told to "Go fish," and she draws a card from the pool and places 
	 * it in her own hand. When any player at any time has all four cards of one rank,
	 *  it forms a book, and the cards must be removed from the hand and placed face 
	 *  up in front of that player. 
	 *  
	 *  If the player has no cards in their hand, they may not request cards form the other 
	 *  player, they just draw a card.
	 *  When the pile is empty, no cards are drawn, but the player still gets to ask for cards 
	 *  following the same rules.
	 *  
	 *  The computer is not allowed to examine or deduce the human player’s cards while 
	 *  playing the game. The computer should randomly pick one card from their hand to request.  
	 *  This means that the computer is not being strategic at all and will 
	 *  probably lose most of the time (unless the player really stinks at Go Fish!). 
	 *  
	 *  When all sets of cards have been laid down, the game ends. The player with the 
	 *  most cards in piles wins.
	 *  
	 *  The game is easier to play if the cards are printed out in sorted order.  
	 *  This also uses a method in the Collections class, which meets a learning objective.

	 * @param args There are no command line arguments.
	 */
	public static void main(String[] args) 
	{
		// TODO: Create deck of cards
		ArrayList<Integer> pool = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		
		createDeck(pool);
		
		// Shuffle cards
		//TODO: Shuffle Cards
		Collections.shuffle(pool);
		
		playOneGame(pool, input);
	}
	
	/** Play one full game of Go Fish!. 
	 * 
	 * @param pool The deck of cards, already shuffled.
	 * @param input Attached to the keyboard to interact with the user.
	 */
	public static void playOneGame(ArrayList<Integer> pool, Scanner input)
	{
		ArrayList<Integer> computer = new ArrayList<Integer>();
		ArrayList<Integer> person = new ArrayList<Integer>();
		ArrayList<Integer> computerPile = new ArrayList<Integer> ();
		ArrayList<Integer> personPile = new ArrayList<Integer>();

		// TODO: Deal cards
		dealHands(pool, person, computer);
		Collections.sort(computer);
		Collections.sort(person);
		
		// TODO: Show the person their starting hand
		showCards(person);
	
		
		// Play the game
		while (computerPile.size() + personPile.size() < 52 || !pool.isEmpty())
		{
			// Let the person play first
			// show the person their cards
			if (!person.isEmpty())
			{
				System.out.println("What card do you want?");
				int card = input.nextInt();
				
				//TODO: Play one turn with the person doing the choosing
				playOneTurn(card, person, computer, personPile, computerPile, pool);
			}
			else
			{
				//TODO: Let the player draw from the deck
				int card = pool.remove(0);
				person.add(card);
				Collections.sort(person);
			}
			
			showGameState(person, computerPile, personPile);
			
			// Now it is the computer's turn
			// Randomly choose a card
			if (!computer.isEmpty())
			{
				int card = computer.get((int)(Math.random()*computer.size()));	
				System.out.println("Do you have any "  + card + "'s ?");
				
				//TODO: Play one turn with the computer doing the choosing
				playOneTurn(card, computer, person, computerPile, personPile, pool);
			}
			else if (!pool.isEmpty())
			{
				//TODO: Let the computer draw from the deck
				int card = pool.remove(0);
				computer.add(card);
				Collections.sort(computer);
			}
			
			showGameState(person, computerPile, personPile);
		}
		
		// TODO: Determine the winner and tell the user--remember ties are possible

			
	}
	
	/** Show the user their cards and their pile and the computer's pile.
	 * 
	 * @param person The cards in the person's hand.
	 * @param computerPile The pile of completed books for the computer.
	 * @param personPile The pile of completed books for the person.
	 */
	public static void showGameState(ArrayList<Integer> person, ArrayList<Integer> computerPile,
			ArrayList<Integer> personPile)
	{
		System.out.println("Here are your cards");
		showCards(person);
		System.out.println("Here is your pile");
		showCards(personPile);
		System.out.println("Here is my pile");
		showCards(computerPile);
	}
	
	/** Play one turn of Go Fish!. The chooser is the person who is selecting a card from the
	 * other person's hand.  This will alternate between the person and the computer.
	 * @param card The card that has been selected.
	 * @param chooser The hand for the player who is currently choosing.
	 * @param chosen The hand for the player who is being asked for cards.
	 * @param chooserPile The pile for the player who is currently choosing.
	 * @param chosenPile The pile for the player who is being asked for cards.
	 * @param pool The deck of cards that have not yet been distributed, already sorted.
	 */
	public static void playOneTurn(int card, ArrayList<Integer> chooser, ArrayList<Integer> chosen,
			ArrayList<Integer> chooserPile, ArrayList<Integer> chosenPile, ArrayList<Integer> pool)
	{
		if (chosen.contains(card))
		{
			//TODO: Chosen gives cards to Chooser
			transferCards(card, chooser, chosen);
			//TODO: If there is a set of four matching cards, put them up on the table
			if (Collections.frequency(chooser, card) == 4)
			{
				transferCards(card, chooserPile, chooser);
			}
			Collections.sort(chooser);
		}
		else
		{
			System.out.println("Go fish!");
			
			//TODO: Draw a card by removing it from the pool and putting it in the chooser's hand
			int pull = pool.remove(0);
			chooser.add(pull);
			//TODO: If there is a set of four matching cards, put them on the table
			if (Collections.frequency(chooser, pull) == 4)
			{
				transferCards(pull, chooserPile, chooser);
			}
			
			Collections.sort(chooser);
		}
	}
	
	/** Transfer all cards of rank card from the source to the destination.
	 * 
	 * @param card The rank of the selected card.
	 * @param destination The hand that will receive the cards.
	 * @param source The hand that will lose the cards.
	 */
	public static void transferCards(int card, ArrayList<Integer> destination, ArrayList<Integer> source)
	{
		while (source.contains(card))
		{
			destination.add(card);
			source.remove(new Integer(card)); // this is that tricky thing from the handout
		}
	}
	
	/** Deal two equal size hands, one to each player.
	 * 
	 * @param deck The deck of cards that should be dealt. These cards should have been shuffled.
	 * @param hand1 The first player.
	 * @param hand2 The second player.
	 */
	public static void dealHands(ArrayList<Integer> deck, ArrayList<Integer> hand1, ArrayList<Integer> hand2)
	{
		//TODO: Deal the cards
		int i = 0;
		//Person's hand
		for (i = 0; i < STARTING_HAND_SIZE; ++i)
		{
			int card = deck.remove(0);
			hand1.add(card);
		}
		
		//Computer's hand
		for (i = 0; i < STARTING_HAND_SIZE; ++i)
		{
			int card = deck.remove(0);
			hand2.add(card);
		}
	}
	
	/** Build a deck of 52 cards, 4 of each rank from 1 to 13
	 * 
	 * @return The deck of cards.
	 */
	public static ArrayList<Integer> createDeck(ArrayList<Integer> pool)
	{
		//TODO: Create a deck of cards
		int i = 0;
		
		for (i = 0; i < 52; ++i)
		{
			int num = (i % 13) + 1;
			pool.add(num);
		}
		return null;// keep the compiler happy and quiet
	}
	

	/** Show all of the cards is any given pack, hand, deck, or pile.
	 * 
	 * @param cards The cards to be displayed
	 */
	public static void showCards(ArrayList<Integer> cards)
	{
		// TODO: Sort the cards to make it easier for the user to know what they have
		
		for (Integer i: cards)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
