import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int distinctCharCount = 0;
        
        // Traverse backwards to process highest frequencies first
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) break;
            
            // Determine push multiplier based on how many distinct characters processed
            int pushMultiplier = (distinctCharCount / 8) + 1;
            totalPushes += freq[i] * pushMultiplier;
            
            distinctCharCount++;
        }
        
        return totalPushes;
    }
}