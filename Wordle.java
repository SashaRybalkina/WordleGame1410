
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
	
	/***
	 * Makes a string array out of all the words found in the filename.
	 * 
	 * @param filename: A file containing one integer and several words.
	 * @return A string array with the words from the filename as tokens.
	 * @throws FileNotFoundException: Throws the exception of a filename 
	 * not being found.
	 */
	
	public static String[] wordsFromFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner fileScanner = new Scanner(file);
		int index = 0;
		String[] newArray = new String[fileScanner.nextInt()];
		fileScanner.nextLine();
			while (fileScanner.hasNextLine()) {
				String token = fileScanner.nextLine();
				newArray[index] = token;
				index++;
			}
		return newArray;
	}
	
	/**
	 * Chooses a random index and gets a word from the index in an array.
	 * 
	 * @param stringArray: An array containing strings.
	 * @return A random word from the array.
	 */
	
	public static String pickRandomWord(String[] stringArray) {
		
		Random randomGenerator = new Random();
		
		int stringIndex = randomGenerator.nextInt(stringArray.length);
		
		return stringArray[stringIndex];
	}
	
	/**
	 * Compares the given word with every word in the given array and decides if
	 * the word can be found in the array.
	 * 
	 * @param string: Any given word.
	 * @param stringArray: Any given string array.
	 * @return true if word is found in the array, false otherwise.
	 */
	
	public static boolean wordInArray(String string, String[] stringArray) {
		
		for (String arrayString: stringArray) {
			
			if (arrayString.equals(string))
				
				return true;
		}
		return false;
	}
	
	/**
	 * Asks the user to enter a five letter word that is in the array. If the
	 * input word isn't five letters or in the array, keeps asking for input.
	 * 
	 * @param stringArray: Any given string array.
	 * @return The input word of the user.
	 */
	
	public static String getUserGuess(String[] stringArray) {
		
		Scanner scannerInput = new Scanner(System.in);
		
		System.out.println("Please enter a five letter word: ");
		
		while (true) {
			
			String input = scannerInput.next();
			
			if (input.length() != 5)
				System.out.println("Please enter a five letter word: ");
			
			else if (input.length() == 5 && !wordInArray(input, stringArray)) {
				System.out.println("Please enter a five letter word: ");
				
			}
			
			else if (input.length() == 5 && wordInArray(input, stringArray)) {
				return input;

			}
		}

	}
	
	/**
	 * Decides if a given character can be found in a given word.
	 * 
	 * @param character: Any given singular character.
	 * @param word: Any given word.
	 * @return true if character can be found in word, false otherwise.
	 */
	
	public static boolean letterInWord(char character, String word) {
		
		for (int index = 0; index < word.length(); index++) {
			if (character == word.charAt(index))
				return true;
		}
		return false;
	}
	
	/**
	 * Displays a string where the character of the guess word is in the 
	 * correct spot of the secret word is capitalized, the character in the 
	 * incorrect spot but in the word is lower-case, and the character in the
	 * incorrect spot and not in the word is replaced with a dash.
	 * 
	 * @param guessWord: Any given guess word.
	 * @param secretWord: The word the user needs to guess.
	 */
	
	public static void displayMatching(String guessWord, String secretWord) {
		
		String result = "";
		
		for (int index = 0; index < guessWord.length(); index++) {
			
			if (guessWord.charAt(index) == secretWord.charAt(index)) {	
				result += Character.toUpperCase(guessWord.charAt(index));
			}
			
			else if (letterInWord(guessWord.charAt(index), secretWord)) {
				result += guessWord.charAt(index);
			}
			
			else {
				result += "-";
			}
			
		}
		System.out.println(result);
	}
	

	public static void main(String[] args) {
		
		try{
			
			String[] words = wordsFromFile("words.txt");
			
			String secretWord = pickRandomWord(words);
			
			int sum = 0;
			
			while (sum < 6) {
				
				sum++;
				
				String guessWord = getUserGuess(words);
				
				if (guessWord.equals(secretWord)) {
					System.out.println("You Win!");
					break;
				}
				
				else {
					displayMatching(guessWord, secretWord);

				}
			
			}
			if (sum == 6
					) {
				
				System.out.println("Game Over. The secret word was " + secretWord + ".");
			
			}
			
		}
		catch (FileNotFoundException e){
			
			System.out.println("File not found");
		}


	}

}
