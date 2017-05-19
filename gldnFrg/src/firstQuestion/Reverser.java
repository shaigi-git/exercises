package firstQuestion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Reverser {

    private static final Set<Character> VOWELS =
            new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    private static boolean isVowel(char c) {
        return VOWELS.contains(Character.toLowerCase(c));
    }

    /**
     * Question 1
     * ==========
     * This function receives a characters array and returns a reversed array
     * with all vowels in upper-case and all consonants lower-case. All the
     * rest of the characters remain the same. For a null array a null array is returned.
     * @param arr characters array
     * @return reversed characters array, vowels in upper-case, consonants in lower-case
     */
    public static char[] reverse(char[] arr) {

        if (arr == null) return null;

        char[] reversed = new char[arr.length];

        for (int i = 0; i < arr.length; i++) {

            char c = arr[i];

            c = isVowel(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            reversed[arr.length - i - 1] = c;
        }
        return reversed;
    }
}
