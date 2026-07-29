class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int halfLen = n / 2;
        int[] count = new int[26];

        // Since s is guaranteed to be a palindrome, collect half-frequencies
        for (int i = 0; i < halfLen; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Identify the middle character if s has odd length
        char midChar = 0;
        if (n % 2 != 0) {
            midChar = s.charAt(halfLen);
        }

        // Build the first half of the k-th lexicographical palindromic permutation
        StringBuilder leftHalf = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (count[c] == 0) continue;

                // Temporarily place character 'c' at position 'pos'
                count[c]--;

                // Calculate total ways to arrange the remaining characters
                long ways = countPermutations(count, halfLen - 1 - pos, k);

                if (k <= ways) {
                    leftHalf.append((char) ('a' + c));
                    found = true;
                    break;
                } else {
                    k -= ways;
                    count[c]++; // Backtrack and try next character
                }
            }

            // If k exceeds total valid permutations available
            if (!found) {
                return "";
            }
        }

        // Construct full palindrome
        StringBuilder result = new StringBuilder(leftHalf);
        if (n % 2 != 0) {
            result.append(midChar);
        }
        result.append(new StringBuilder(leftHalf).reverse());

        return result.toString();
    }

    /**
     * Calculates multinomial coefficient: totalRemaining! / (cnt1! * cnt2! * ... * cnt26!)
     * Clamped to target limit (k) to prevent integer overflow.
     */
    private long countPermutations(int[] count, int totalRemaining, long limit) {
        if (totalRemaining == 0) return 1;

        // Find the index of the largest count to skip its factorial in division
        int maxIdx = 0;
        for (int i = 1; i < 26; i++) {
            if (count[i] > count[maxIdx]) {
                maxIdx = i;
            }
        }

        long res = 1;
        int curN = totalRemaining;

        for (int i = 0; i < 26; i++) {
            if (i == maxIdx || count[i] == 0) continue;

            for (int j = 1; j <= count[i]; j++) {
                res = res * curN / j;
                curN--;
                if (res > limit) {
                    return limit + 1; // Early exit if count exceeds required k
                }
            }
        }

        return res;
    }
}