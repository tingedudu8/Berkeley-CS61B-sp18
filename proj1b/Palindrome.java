public class Palindrome {

    /**
     * Given a String,
     * returns a Deque where the characters appear in the same order as in the String.
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    // public boolean isPalindrome(String word) {
    //     if (word.equals("") || word.length() == 1) {
    //         return true;
    //     }
    //     for (int i = 0, n = word.length() - 1; i < n; i++, n--) {
    //         if (word.charAt(i) != word.charAt(n)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // use recursion
    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (first == last) {
                return isPalindromeHelper(deque);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }
    
    // public boolean isPalindrome(String word, CharacterComparator cc) {
    //     if (word.equals("") || word.length() == 1) {
    //         return true;
    //     }
    //     for (int i = 0, j = word.length() - 1; i < j; i += 1, j -= 1) {
    //         if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (cc.equalChars(first, last)) {
                return isPalindromeHelper(deque, cc);
            } else {
                return false;
            }
        }
    }
    
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }

}

