package hangman;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is a simple implementation of the widely known game of Hangman.
 * @author Trinity Boston
 *
 */
public class Hangman {
	public static String[] asianCountries = {"JAPAN","CAMBODIA","THAILAND","VIETNAM",
			"CHINA","KOREA","LAOS","INDIA","MALAYSIA","RUSSIA","MONGOLIA"}; 
	public static String[] animals = {"CHEETAH","KOALA","KANGAROO","ELEPHANT",
			"RHINO","HIPPO","RACCOON","EAGLE","CROCODILE","PENGUIN","OTTER","WHALE","SHARK"};
	public static String[] pokemon = {"PIKACHU","TREECKO","PIPLUP","GARCHOMP","LUGIA","BLASTOISE"};
	
	public static String[][] categories = {asianCountries,animals,pokemon};
	public static String[] categoryTitles = {"asianCountries", "animals", "pokemon"};
	/**
	 * This function displays the initial amount of letters in the word with underscores
	 * representing each letter
	 * @param gw - guessword so the method can generate an array of gw's length
	 */
	public static void displayUnderscores(String gw) {
		for(int i = 0; i < gw.length(); i++) {
			System.out.print("_ ");
		}
		System.out.println();
	}
	
	/**
	 * This function creates an array filled with underscores for each letter in the guessword.
	 * The purpose of this function is to create an array that will get updated and have each 
	 * underscore replaced with a guessed letter.
	 * @param gw - guessword is passed so the length can be recorded
	 * @return char underscoreArray - array of underscores to be manipulated with each guessed letter
	 */
	public static char[] generateUnderscoreArray(String gw) {
		int stringLength = gw.length();
		char underscoreArray[] = new char[stringLength];
		
		for(int i = 0; i < stringLength; i++) {
			underscoreArray[i] = '_';
		}
		return underscoreArray;
	}
	
	/**
	 * This function displays an array to the console so the user can see the underscores
	 * and see how many letters they have left.
	 * @param arr - char array to display
	 */
	public static void displayArray(char[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * This function provides the functionality of the game. It sets the guessword, gets user input from 
	 * the keyboard for letter guesses, and updates the underscore array by filling it in with guessed letters
	 * so the user can see which letters they have guessed. It also keeps track of the number of guesses,
	 * errors, and letters left. If the user has guessed all letters or had too many errors, this function
	 * will end the game. 
	 */
	public static void game() {
		//Randomly generates a number to be used as the index of the randomly selected 
		//category from categories[]
		int categoryIndex = (int) (Math.random() * (categories.length));
		
		//Sets category as the randomly selected category
		String[] category = categories[categoryIndex]; 
		
		//Randomly select a guessword from the category word list
		String guessword = category[(int) (Math.random() * category.length)];
		
		//Set categoryTitle to the category name so it can be displayed to user
		String categoryTitle = categoryTitles[categoryIndex];
		
		
		//Display welcome message
		System.out.println("Welcome to Hangman!");
			
		//Display category and # of attempts
		System.out.println("The category is " + categoryTitle + ". You have 6 attempts.");
		
		
		int guesses = 0; //number of guesses
		int errors = 0; //number of errors
		int lettersLeft = guessword.length(); //number of unguessed letters left
		int lettersFilled = 0; //number of letters filled (gets reset with each guess)
		char[] guessed = new char[26];
		
		displayUnderscores(guessword); //Displays underscores to indicate number of letters in guessword
		System.out.println();
		
		Scanner keyboard = new Scanner(System.in); //User input
	
		//Initializes array of underscores to be manipulated with each correct guess
		char[] usArray = generateUnderscoreArray(guessword); 
		
		//Game starts and continues until 6 errors are made or the word is guessed
		while(errors < 6) {
			System.out.print("Guess #" + (guesses + 1) + ":"); //displays number of guesses
			char attempt = Character.toUpperCase(keyboard.next().charAt(0)); //user input
			System.out.println("Your input: " + attempt); //displays user input
			
			//For loop to check if guessed letter is in the guessword
			for(int i = 0; i < guessword.length(); i++) {
				//if user input == letter in guessword
				if(attempt == (guessword.charAt(i))) {
					if(Arrays.binarySearch(guessed, attempt) < 0) {
						usArray[i] = attempt; //change underscore in array to the letter
						lettersLeft--; //Subtract one letter from letters left to guess
						lettersFilled++; //Add one to letters filled in word
						guessed[0] = attempt;
						
					}
					else {
						System.out.println("You already guessed that letter!");
					}
				}
			}
			//guessed[0] = attempt;
			guesses++; //Increment number of guesses
			guessed[guesses] = attempt;
			
			//If no letters were filled, then the guess was incorrect so errors is incremented
			if(lettersFilled == 0) errors++;
			displayHangman(errors); //shows hangman
			lettersFilled = 0; //reset to zero for new guess
			
			System.out.println("Errors: " + errors); //display number of errors
			displayArray(usArray); //display updated array with any guessed letters added
			System.out.println();
			
			//If user guesses the word
			if(lettersLeft == 0) {
				System.out.println("Congrats! You guessed it! The word was " + guessword + "."); //winner message
				return; //break while loop and terminate program
			}
		}
		keyboard.close();
		System.out.println("So close! The word was " + guessword + '.'); //losing message
	}
	
	/**
	 * This function provides the visual of the hangman with each guess. A switch statement
	 * allows the hangman to gain a body part with each incorrect guess.
	 * @param stage - The amount of errors is passed so that the hangman reflects 
	 * how many attempts the user has left
	 */
	public static void displayHangman(int stage) {
		switch(stage) {
		case 1:
			System.out.println("--------");
			System.out.println("|    |  ");
			System.out.println("|    O   ");
			System.out.println("|       ");
			System.out.println("|       ");
			System.out.println("++++++++");
			break;
		case 2:
			System.out.println("--------");
			System.out.println("|    |  ");
			System.out.println("|    O   ");
			System.out.println("|   /    ");
			System.out.println("|       ");
			System.out.println("++++++++");
			break;
		case 3:
			System.out.println("--------");
			System.out.println("|    |  ");
			System.out.println("|    O   ");
			System.out.println("|   /|    ");
			System.out.println("|       ");
			System.out.println("++++++++");
			break;
		case 4:
			System.out.println("--------");
			System.out.println("|    |  ");
			System.out.println("|    O   ");
			System.out.println("|   /|\\    ");
			System.out.println("|       ");
			System.out.println("++++++++");
			break;
		case 5:
			System.out.println("--------");
			System.out.println("|    |");
			System.out.println("|    O");
			System.out.println("|   /|\\");
			System.out.println("|   / ");
			System.out.println("++++++++");
			break;
		case 6:
			System.out.println("--------");
			System.out.println("|    |");
			System.out.println("|    O");
			System.out.println("|   /|\\");
			System.out.println("|   / \\");
			System.out.println("++++++++");
			break;
		default:
			System.out.println("--------");
			System.out.println("|    |  ");
			System.out.println("|       ");
			System.out.println("|       ");
			System.out.println("|       ");
			System.out.println("++++++++");
			break;
		}
	}

	/**
	 * This function starts the game upon running by calling the game() method.
	 */
	public static void start() {
		//Start game
		game();
	}
	
}
