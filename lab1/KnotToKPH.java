import java.util.Scanner;

public class KnotToKPH {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter number: ");

        Double num = input.nextDouble(); 
        Double result = num * 1.852;
        System.out.println(num + " knop motsvarar: " + result);
        input.close();
    }
    
}
