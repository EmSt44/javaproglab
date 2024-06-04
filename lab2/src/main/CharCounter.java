package main;

import java.util.Scanner;


/*
Implementerar ett program som frågar användaren efter en sträng och ett tecken.
Räknar sedan längden på meningen, hur många gånger tecknet förekommer i meningen
och index på första och sista platsen tecknet förekommer i meningen.
*/
public class CharCounter{
    
    public static void charCounter() { // Kör denna metod från en main metod i en main klass.

        Scanner input = new Scanner(System.in); // Scanner för input i programmet.

        String sentence = ""; // Variabel för strängen från användaren.
        char character = ' '; // Variabel för tecknet från användaren.

        boolean looper1 = true; // Bool som blir falsk när loopen avslutas.
        while (looper1 == true) { // While loop för att kunna fråga efter fler inputs ifall användaren skriver en tom mening.
            System.out.println("Skriv in en mening: ");
            sentence = input.nextLine();
            if (sentence.length() != 0) {
                looper1 = false; // Användaren har gett en valid input, avsluta loopen.
            }
        }

        boolean looper2 = true; // Bool som blir falsk när loopen ska avslutas.
        while (looper2 == true) { // While loop för att kunna fråga efter fler inputs ifall användaren skriver något annat än en enda char.
            System.out.println("Skriv in ett tecken: ");
            String inputString = input.nextLine();

            if (inputString.length() == 1) {
                character = inputString.charAt(0);
                looper2 = false; // Användaren har gett en valid input, avsluta loopen.
            }
        }
        
        input.close();

        countCharInSentence(sentence, character);

    }

    private static void countCharInSentence(String str, char cha) {

        int count = 0; // Antal gånger tecknet förekommer i strängen.
        int firstEncounter = 0; // Första gången tecknet förekommer i strängen.
        int lastEncounter = 0; // Sista gången tecknet förekommer i strängen.
        boolean encountered = false; // sann om tecknet stötts på tidigare.

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == cha) {
                count++;
                lastEncounter = i;

                if (encountered == false) { // Ifall tecknets stöts på för första gången sparas det i firstEncountered och encountered blir sann.
                    firstEncounter = i;
                    encountered = true;
                }
            }

        }

        if (encountered == true) {
            System.out.println("Meningen har totalt " + str.length() + " tecken.");
            System.out.println("Tecknet " + cha + " förekommer " + count + " gånger.");
            System.out.println("Första gången på indexplats " + firstEncounter + ".");
            System.out.println("Sista gången på indexplats " + lastEncounter + ".");
        }
        else {
            System.out.println("Meningen har totalt " + str.length() + " tecken.");
            System.out.println("Tecknet förekommer inte i meningen.");
        }
        
    }

}