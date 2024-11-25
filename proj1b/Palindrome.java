public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dq1 = wordToDeque(word);
        Deque<Character> dq2 = wordToDeque(word);
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < word.length(); i++) {
            str1 += dq1.removeFirst();
            str2 += dq2.removeLast();
        }
        return str1.equals(str2);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        if (len <= 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
