package main;

import java.io.*;
import java.util.Scanner;

/**
 * A class for a program that tests the users knowledge of translating words from one language to another, currently from swedish to english.
 * Will display a word for the user to translate and gives feedback on the correctness of the answer input by the user.
 * Run by running the method vocabulary in the main class.
 */
public class Vocabulary {

    /**
     * Runs the program.
     * The user will be displayed a word in one language and be prompted to write the version of that word in another language.
     * The program will evaluate the correctness of this translation and print out if it was correct, almost correct or incorrect.
     * This will be done 10 times or until the user exits the program by pressing Q and enter.
     * The amount of correct translations will be tracked and displayed at the end of the run.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void vocabulary() throws FileNotFoundException, IOException{

        // The current language, this only changes the title text. The actual words are in the file.
        String language = "Engelska";

        // The file to be used for the words, change the filepath to the correct one for your system.
        String filePath = "/home/emil/Desktop/javaproglab/lab4/src/main/words.txt";

        File file = new File(filePath);

        // Buffered reader to read the words file 
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Scanner for input from the user
        Scanner input = new Scanner(System.in);

        System.out.println("** Glosövning - " + language + " **");
        System.out.println("Skriv det " + language + " ordet. Avsluta programmet genom att skriva Q.");

        // Main input loop
        int totalCorrect = 0;
        int iterations = 0;
        for (int i = 0; i < 10; i++ ) {
            String firstLanguageWord = br.readLine();
            System.out.print(firstLanguageWord + " : ");
            String userInput = input.nextLine();

            if (userInput == "q" || userInput == "Q") { // If the user enters "q" or "Q" the program should be aborted.
                System.out.println("Programmet avbröts");
                break;
            }

            Integer lowestDifferenceInChars = null;
            String bestMatchWord = null;
            String secondLanguageWord;
            while ((secondLanguageWord = br.readLine()) != null) {
                if (secondLanguageWord.equals("-")) {
                    break;
                }
                int incorrectAmount = compareWords(secondLanguageWord, userInput);
                if (lowestDifferenceInChars == null || lowestDifferenceInChars > incorrectAmount) {
                    lowestDifferenceInChars = incorrectAmount;
                    bestMatchWord = secondLanguageWord;
                }
            }

            if (lowestDifferenceInChars == 0) {
                totalCorrect += 1;
                System.out.println("Korrekt! " + totalCorrect + " rätt av " + (i+1) + " ord.");
            }
            else if (lowestDifferenceInChars == 1) {
                System.out.println("Nästan rätt. Korrekt svar är " + bestMatchWord + ". " + totalCorrect + " rätt av " + (i+1) + " ord.");
            }
            else {
                System.out.println("Inkorrekt. " + totalCorrect + " rätt av " + (i+1) + " ord.");
            }
            iterations++;

        }

         System.out.println("Du svarade på totalt " + iterations + " glosor och hade " + totalCorrect + " rätt. Välkommen åter!");

         // Closing the reader and scanner to not cause a memory leak
         br.close();
         input.close();
        }

        /**
         * Compares two strings and returns a number depending on their similarity.
         * @param correctWord The first string to be compared.
         * @param inputWord The second string for the comparison.
         * @return an integer corresponding to how many characters were different in the two strings
         */
        public static int compareWords(String correctWord, String inputWord) {

            int notIdenticalCount = 0;
            int longestWord;
            int shortestWord;

            if (correctWord.length() > inputWord.length()) {
                longestWord = correctWord.length();
                shortestWord = inputWord.length();
            }
            else {
                longestWord = inputWord.length();
                shortestWord = correctWord.length();
            }

            for (int i = 0; i < shortestWord; i++) {
                if (correctWord.charAt(i) != inputWord.charAt(i)) {
                    notIdenticalCount++;
                }
            }

            notIdenticalCount += longestWord - shortestWord;

            return notIdenticalCount;
        }


}

