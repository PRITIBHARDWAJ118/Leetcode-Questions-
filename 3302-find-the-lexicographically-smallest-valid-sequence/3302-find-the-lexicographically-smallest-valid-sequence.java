class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[i] stores the maximum index in word1 from which 
        // suffix word2[i...m-1] can be matched as a subsequence.
        int[] last = new int[m];
        int j = m - 1;
        for (int i = n - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }

        // If we couldn't match all characters of word2 from the right,
        // fill unreached indices with -1
        while (j >= 0) {
            last[j] = -1;
            j--;
        }

        int[] result = new int[m];
        boolean usedChange = false;
        int i1 = 0; // index in word1

        for (int i2 = 0; i2 < m; i2++) {
            boolean matched = false;

            // Greedily try to pick the smallest index i1 in word1
            while (i1 < n) {
                // Option 1: Exact character match
                if (word1.charAt(i1) == word2.charAt(i2)) {
                    result[i2] = i1;
                    i1++;
                    matched = true;
                    break;
                }
                
                // Option 2: Mismatch, but we haven't used our 1 character replacement yet.
                // We check if the remaining suffix word2[i2+1...m-1] can be matched in word1[i1+1...n-1].
                if (!usedChange) {
                    boolean canMatchRest = (i2 == m - 1) || (last[i2 + 1] > i1);
                    if (canMatchRest) {
                        usedChange = true;
                        result[i2] = i1;
                        i1++;
                        matched = true;
                        break;
                    }
                }

                i1++;
            }

            if (!matched) {
                return new int[0];
            }
        }

        return result;
    }
}