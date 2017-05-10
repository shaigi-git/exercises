package secondQuestion;

public class Utils {

    /**
     * Wraps the string with double quotes
     * @param str
     * @return same string wrapped with double quotes
     */
    public static String wrapWithQuotes(String str) {
        return "\"" + str + "\"";
    }

    /**
     * Removes the last comma from a string.
     * Assuming comma is 2 places before end of string
     * @param str
     * @return the same string without the end comma
     */
    public static String removeLastComma(String str) {
        if (!str.isEmpty()) {
            return str.substring(0, str.length() - 2);
        } else {
            return str;
        }
    }
}
