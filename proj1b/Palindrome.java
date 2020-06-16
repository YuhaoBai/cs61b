public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d);
    }

    private boolean isPalindrome(Deque<Character> d) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        if (d.removeFirst() == d.removeLast()) {
            return isPalindrome(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome(d, cc);
    }

    private boolean isPalindrome(Deque<Character> d, CharacterComparator cc) {
        if (d.isEmpty() || d.size() == 1) {
            return true;
        }
        if (cc.equalChars(d.removeFirst(), d.removeLast())) {
            return isPalindrome(d, cc);
        } else {
            return false;
        }
    }


}
