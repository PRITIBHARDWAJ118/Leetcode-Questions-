class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int countOnes = 0;
        
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            // Shrink the window while maintaining exactly k '1's
            while (countOnes == k) {
                // Remove leading '0's to get the shortest possible substring starting at 'left'
                while (s.charAt(left) == '0') {
                    left++;
                }

                int currentLen = right - left + 1;
                String currentSub = s.substring(left, right + 1);

                // Update result if a shorter substring is found,
                // or if equal length and lexicographically smaller
                if (currentLen < minLen) {
                    minLen = currentLen;
                    result = currentSub;
                } else if (currentLen == minLen) {
                    if (currentSub.compareTo(result) < 0) {
                        result = currentSub;
                    }
                }

                // Move left forward to break the condition and look for next windows
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}