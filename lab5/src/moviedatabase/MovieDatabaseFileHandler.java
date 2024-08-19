package moviedatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * A handler for files
 */
public class MovieDatabaseFileHandler {

    private File file = new File("files/movies.txt"); // The file where movies are stored, change to match location in your system.
	private FileWriter _fileWriter;
	private Scanner _fileScanner;

	public MovieDatabaseFileHandler() {

	}

   
	/**
	 * Searches the file for a given title and return a linked list containing matching movies.
	 * @param title The title being searched for.
	 * @return A linked list containing all movies matching the title.
	 */
	public LinkedList<MovieDatabaseMovie> searchTitle(String title) {

		try {
			_fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error finding the file");
			e.printStackTrace();
		}

		LinkedList<MovieDatabaseMovie> matchingMovies = new LinkedList<MovieDatabaseMovie>();
		while (_fileScanner.hasNextLine()) {
			String line = _fileScanner.nextLine();
			String[] parts = line.split(",");
			String currentTitle = parts[0];
			String currentScore = parts[1];
			if(currentTitle.toLowerCase().contains(title.toLowerCase())) {
				MovieDatabaseMovie currentMovie = new MovieDatabaseMovie(currentTitle,Integer.parseInt(currentScore));
				matchingMovies.add(currentMovie);
			}
		}

		// Close scanner to avoid issues with open reader and writer at the same time
		_fileScanner.close();

		return matchingMovies;
				
	}
	/**
	 * Searches the file for a given review score and returns all movies with a score equal to or higher.
	 * @param review the review score to search
	 * @return A linked list containing all movies with a score equal or higher than the given one.
	 */
	public LinkedList<MovieDatabaseMovie> searchReviewScore(int review) {		

		try {
			_fileScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error finding the file");
			e.printStackTrace();
		}

		LinkedList<MovieDatabaseMovie> matchingMovies = new LinkedList<MovieDatabaseMovie>();
		while (_fileScanner.hasNextLine()) {
			String line = _fileScanner.nextLine();
			String[] parts = line.split(",");
			String currentTitle = parts[0];
			String currentScore = parts[1];
			int scoreAsInteger = Integer.parseInt(currentScore);
			if(scoreAsInteger >= review) {
				MovieDatabaseMovie currentMovie = new MovieDatabaseMovie(currentTitle,Integer.parseInt(currentScore));
				matchingMovies.add(currentMovie);
			}
		}

		// close scanner to avoid issues with open reader and open writer at the same time
		_fileScanner.close();

		return matchingMovies;
		
	}	

	/**
	 * Add movie with the provided title and score to the database.
	 * @param title The title of the movie.
	 * @param reviewScore The score of the movie.
	 */
	public void addMovie(String title, int reviewScore) {

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
    
}
