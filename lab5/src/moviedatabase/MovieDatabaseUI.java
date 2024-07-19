package moviedatabase;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
	private Scanner _scanner;
	private File file = new File("/home/emil/Desktop/javaproglab/lab5/src/moviedatabase/movies.txt"); // The file where movies are stored, change to match location in your system.
	private FileWriter _fileWriter;
	private Scanner _fileScanner;
	/**
	 * Construct a MovieDatabaseUI.
	 */
	public MovieDatabaseUI() {
		
	}
	/**
	 * Start the movie database UI.
	 */
	public void startUI() {
		_scanner = new Scanner(System.in);

		int input;		
		boolean quit = false;

		System.out.println("** MOVIE DATABASE **");

		while(!quit) {			
			input = getNumberInput(_scanner, 1, 4, getMainMenu());

			switch(input) {
			case 1: searchTitel(); break;
			case 2: searchReviewScore(); break;
			case 3: addMovie(); break;
			case 4: quit = true; 			
			}
		}
		//Close scanner to free resources
		_scanner.close();
	} 

	/**
	 * Get input and translate it to a valid number.
	 * 
	 * @param scanner the Scanner we use to get input 
	 * @param min the lowest correct number
	 * @param max the highest correct number
	 * @param message message to user
	 * @return the chosen menu number 
	 */
	private int getNumberInput(Scanner scanner, int min, int max, String message) {
		int input = -1;

		while(input < 0) {
			System.out.println(message);
			try {
				input = Integer.parseInt(scanner.nextLine().trim());		
			} 
			catch(NumberFormatException nfe) {
				input = -1;
			}
			if(input < min || input > max) {
				System.out.println("Invalid value");
				input = -1;
			}			
		}			
		return input;
	}
	/**
	 * Get search string from user, search title in the movie 
	 * database and present the search result.
	 */
	private void searchTitel() {
		System.out.print("Enter key word: ");
		String title = _scanner.nextLine().trim();

		try {
			_fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error finding the file");
			e.printStackTrace();
		}

		while (_fileScanner.hasNextLine()) {
			String line = _fileScanner.nextLine();
			String[] parts = line.split(",");
			String currentTitle = parts[0];
			String currentScore = parts[1];
			if(currentTitle.toLowerCase().contains(title.toLowerCase())) {
				System.out.println(currentTitle + ", Review score: " + currentScore + "/5");
			}
		}

		// Close scanner to avoid issues with open reader and writer at the same time
		_fileScanner.close();
				
	}
	/**
	 * Get search string from user, search review score in the movie 
	 * database and present the search result.
	 */
	private void searchReviewScore() {		
		int review = getNumberInput(_scanner, 1, 5, "Enter minimum review score (1 - 5): ");

		try {
			_fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error finding the file");
			e.printStackTrace();
		}

		while (_fileScanner.hasNextLine()) {
			String line = _fileScanner.nextLine();
			String[] parts = line.split(",");
			String currentTitle = parts[0];
			String currentScore = parts[1];
			int scoreAsInteger = Integer.parseInt(currentScore);
			if(scoreAsInteger >= review) {
				System.out.println(currentTitle + ", Review score: " + currentScore + "/5");
			}
		}

		// close scanner to avoid issues with open reader and open writer at the same time
		_fileScanner.close();
		
	}	
	/**
	 * Get information from user on the new movie and add
	 * it to the database.
	 */
	private void addMovie() {
		System.out.print("Title: ");
		String title = _scanner.nextLine().trim();
		int reviewScore = getNumberInput(_scanner, 1, 5, "Review score (1 - 5): ");

		try {
			_fileWriter = new FileWriter(file, true);
		} catch (IOException e) {
			System.out.println("Error finding the file");
			e.printStackTrace();
		}

		try {
			_fileWriter.write(title + "," + reviewScore + "\n");
		} catch (IOException e) {
			System.out.println("Error writing to the file");
			e.printStackTrace();
		}

		// Closing the filewriter to avoid possible issues with open writer and reader at the same time
		try {
			_fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error closing the filweriter");
			e.printStackTrace();
		}
	}	
	/**
	 * Return the main menu text.
	 * 
	 * @return the main menu text
	 */
	private String getMainMenu() {
		return  "-------------------\n" +
				"1. Search title\n" +
				"2. Search review score\n" +
				"3. Add movie\n" +
				"-------------------\n" + 
				"4. Close program";
	}	
}