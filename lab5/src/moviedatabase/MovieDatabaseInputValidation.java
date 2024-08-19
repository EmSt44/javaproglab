package moviedatabase;

public class MovieDatabaseInputValidation {

    /**
     * check if a string is empty or not, returns true if the string is accepted.
     * @param input The string to be checked.
     * @return False if empty true if not empty.
     */
    public static boolean sanitizeInput(String input) {
        if (input.equals("")) {
            return false;
        }
        else if (input.equals(null)) {
            return false;
        }
        else {
            return true;
        }
    }
}
