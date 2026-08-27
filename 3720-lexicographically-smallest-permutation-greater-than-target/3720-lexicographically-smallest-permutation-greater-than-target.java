class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to find the longest matching prefix with target, 
        // then find the rightmost position where we can place a larger character.
        char[] result = new char[n];
        int bestSplit = -1;
        char bestChar = ' ';

        // We can simulate matching step-by-step
        int[] currentCount = count.clone();
        for (int i = 0; i < n; i++) {
            char t = target.charAt(i);
            
            // Check if we can put a character strictly larger than t at position i
            for (int c = t - 'a' + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    bestSplit = i;
                    bestChar = (char) ('a' + c);
                    break;
                }
            }
            
            // Check if we can continue matching target[i]
            if (currentCount[t - 'a'] > 0) {
                currentCount[t - 'a']--;
            } else {
                break; // Cannot match further along target
            }
        }

        // If no valid split position exists, no permutation is strictly greater
        if (bestSplit == -1) {
            return "";
        }

        // Reconstruct the answer using the best split index found
        int[] freq = count.clone();
        for (int i = 0; i < bestSplit; i++) {
            result[i] = target.charAt(i);
            freq[target.charAt(i) - 'a']--;
        }

        // Place the larger character at bestSplit
        result[bestSplit] = bestChar;
        freq[bestChar - 'a']--;

        // Fill remaining positions with the smallest available characters
        int idx = bestSplit + 1;
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                result[idx++] = (char) ('a' + c);
                freq[c]--;
            }
        }

        return new String(result);
    }
}