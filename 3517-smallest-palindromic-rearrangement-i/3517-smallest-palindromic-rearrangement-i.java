class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        // Since s is already a palindrome, we can extract the first half
        String firstHalf = s.substring(0, n / 2);
        
        // Sort the characters in the first half to make it lexicographically smallest
        char[] halfChars = firstHalf.toCharArray();
        java.util.Arrays.sort(halfChars);
        
        String sortedFirstHalf = new String(halfChars);
        StringBuilder result = new StringBuilder(sortedFirstHalf);
        
        // If s has an odd length, append the middle character
        if (n % 2 != 0) {
            result.append(s.charAt(n / 2));
        }
        
        // Append the reverse of the sorted first half to complete the palindrome
        result.append(new StringBuilder(sortedFirstHalf).reverse());
        
        return result.toString();
    }
}