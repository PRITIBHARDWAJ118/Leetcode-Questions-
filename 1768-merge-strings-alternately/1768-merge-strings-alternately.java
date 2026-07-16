class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder st = new StringBuilder();
        int i = 0;
        
        // Loop as long as we haven't reached the end of BOTH strings
        while (i < word1.length() || i < word2.length()) {
            // If word1 still has characters, append the current one
            if (i < word1.length()) {
                st.append(word1.charAt(i));
            }
            // If word2 still has characters, append the current one
            if (i < word2.length()) {
                st.append(word2.charAt(i));
            }
            
            // Move to the next index
            i++;
        }
        
        // Convert the StringBuilder back to a String and return
        return st.toString();
    }
}