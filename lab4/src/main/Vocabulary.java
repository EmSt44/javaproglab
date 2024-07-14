package main;

import java.io.*;
import java.util.Scanner;

/**
 * A class for a program that tests the users knowledge of translating words from one language to another, currently from swedish to english.
 * Will display a word for the user to translate and gives feedback on the correctness of the answer input by the user.
 * Run by running the method vocabulary in the main class.
 */
public class Vocabulary {

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
        for (int i = 0; i < 10; i++ ) {
            String firstLanguageWord = br.readLine();
            System.out.print(firstLanguageWord + " : ");
            String userInput = input.nextLine();

            String secondLanguageWord;
            while ((secondLanguageWord = br.readLine()) != null) {
                if (secondLanguageWord == "-") {
                    break; // Break the loop once the limiter '-' in the file is encountered, after this limiter comes the next word.
                }
                //compareWords(userInput, secondLanguageWord)
            }
        }

        //private static bool



        // Closing the reader and scanner to not cause a memory leak
        br.close();
        input.close();

    }
    
}
