package main;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class for handling input integers from a user.
 * Asks the user how many integers they wish to input and then asks the user to input that amount of integers.
 * Prints out the number of integers, how many were unique and the average of these integers.
 * Is run statically via the method intHandler.
 * 
 * @author Emil Strand
 */
public class IntHandler {
    
    /**
     * Asks the user how many integers they wish to input and then to input that number of integers, then prints out how many numbers were input how many were unique and the average of all the numbers.
     * No other values than integers accepted.
     * No negative integers are accepted.
     * The number of integers must not be 0.
     * @return void
     */
    public static void intHandler() { // Kör från en main klass.

        int[] numbers = handleInput();

        averageAndRepeats(numbers);
    }

    /**
     * Takes input from user, first and integer denoting how many integers the user wishes to input and then asks the user to input these integers.
     * Does not accept input other than integers, the number of integers can not be zero or negative, the following integers can be 0 but not negative.
     * @return An array containing the users input numbers
     */
    private static int[] handleInput() {

        Scanner input = new Scanner(System.in);

        int amountOfInts = 0; // Intitaliseras som 0.
        do {
            try {
                System.out.print("Hur många heltal vill du ange: ");
                amountOfInts = input.nextInt();
                if (amountOfInts <= 0) {
                    System.out.println("Ogiltigt värde, måste vara ett positivt heltal");
                }
            } catch (InputMismatchException e) {
            System.out.println("Ogiltigt värde.");
        }
        input.nextLine();
    } while (amountOfInts <= 0);

        int[] numbers;
        numbers = new int [amountOfInts];

        int registeredInts = 0;
        do {
            try {
                System.out.print("Ange heltal " + (registeredInts + 1) + ": ");
                numbers[registeredInts] = input.nextInt();
                if (numbers[registeredInts] >= 0) {
                    ++registeredInts; 
                }
                else {
                    System.out.println("Ogiltigt värde, negativa värden ej tillåtna.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ogiltigt värde.");
            }
            input.nextLine();
        } while (registeredInts < amountOfInts);

        input.close();

        return numbers;
    }


    /**
     * Takes an array of integers and calculates their average states the length of the array and how many unique values it contains.
     * @param numbers The array of integers that the method should handle
     * @return void
     */
    private static void averageAndRepeats(int[] numbers) {

        int total = 0; // Totala värdet av att addera hela arrayen numbers.

        int singles = 0; // Mängden värden som bara uppstått en gång.


        for (int i = 0; i < numbers.length; i++) {
          
            total += numbers[i];

            boolean isUnique = true; // Initialiseras som true

            for (int j = 0; j < numbers.length; j++) {
                
                if (numbers[i] == numbers[j] && i != j) {
                    isUnique = false;
                }
            }

            if (isUnique) {
                singles += 1;
            }
        }

        System.out.println("Du angav " + numbers.length + " tal.");
        System.out.println("Varav " + singles + " av talen bara angavs en gång.");
        System.out.println("Medelvärdet för talen är: " + total/numbers.length);
    }

}
