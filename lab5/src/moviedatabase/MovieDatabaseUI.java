package moviedatabase;

import java.util.Scanner;
import java.util.ListIterator;

/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
	private Scanner _scanner;
	private MovieDatabaseFileHandler _fileHandler;
	
	/**
	 * Construct a MovieDatabaseUI.
	 */
	public MovieDatabaseUI() {
		_fileHandler = new MovieDatabaseFileHandler();
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
		boolean correctInput = false;
		String title = null;
		while (correctInput == false) {
			System.out.print("Enter key word: ");
			title = _scanner.nextLine().trim();
			correctInput = MovieDatabaseInputValidation.sanitizeInput(title);
		}

		ListIterator<MovieDatabaseMovie> iterator = _fileHandler.searchTitle(title).listIterator(0);
		while (iterator.hasNext()) {
			MovieDatabaseMovie movie = iterator.next();
			System.out.println(movie.title + ", review score: " + movie.score + "/5");
		}
	}
	/**
	 * Get search string from user, search review score in the movie 
	 * database and present the search result.
	 */
	private void searchReviewScore() {		
		int review = getNumberInput(_scanner, 1, 5, "Enter minumum review score (1 - 5): ");

		ListIterator<MovieDatabaseMovie> iterator = _fileHandler.searchReviewScore(review).listIterator(0);
		while (iterator.hasNext()) {
			MovieDatabaseMovie movie = iterator.next();
			System.out.println(movie.title + ", review score: " + movie.score + "/5");
		}
		
	}	
	/**
	 * Get information from user on the new movie and add
	 * it to the database.
	 */
	private void addMovie() {
		boolean correctInput = false;
		String title = null;
		while (correctInput == false) {
			System.out.print("Title: ");
		    title = _scanner.nextLine().trim();
			correctInput = MovieDatabaseInputValidation.sanitizeInput(title);
		}
		int reviewScore = getNumberInput(_scanner, 1, 5, "Review score (1 - 5): ");

		_fileHandler.addMovie(title, reviewScore); // call on filehandler to add the movie to the database
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