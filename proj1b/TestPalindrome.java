import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("ababa"));
        assertFalse(palindrome.isPalindrome("java"));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator cc = new OffByOne();

        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("A", cc));
        assertFalse(palindrome.isPalindrome("ababa", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
    }
}
