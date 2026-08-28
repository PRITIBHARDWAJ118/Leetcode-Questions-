class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Validate if s can form a palindrome
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if ((n % 2 == 0 && oddCount > 0) || (n % 2 == 1 && oddCount != 1)) {
            return "";
        }

        // Available characters for the left half
        int[] leftFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            leftFreq[i] = freq[i] / 2;
        }

        int[] targetArr = new int[n];
        for (int i = 0; i < n; i++) {
            targetArr[i] = target.charAt(i) - 'a';
        }

        // Try prefix match length L in the left half from half down to 0
        for (int L = half; L >= 0; L--) {
            int[] curFreq = leftFreq.clone();
            boolean possible = true;
            int[] leftHalf = new int[half];

            for (int i = 0; i < L; i++) {
                int c = targetArr[i];
                if (curFreq[c] > 0) {
                    leftHalf[i] = c;
                    curFreq[c]--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            // Try picking a character at position L
            int startChar = (L < half) ? targetArr[L] + 1 : 0;
            if (L == half) {
                // If L == half, we matched the entire left half. Check if the full palindrome > target
                String cand = constructPalindrome(leftHalf, oddChar, n);
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
                continue;
            }

            for (int c = startChar; c < 26; c++) {
                if (curFreq[c] > 0) {
                    leftHalf[L] = c;
                    curFreq[c]--;

                    // Fill remaining left half with smallest available characters
                    int idx = L + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (curFreq[ch] > 0) {
                            leftHalf[idx++] = ch;
                            curFreq[ch]--;
                        }
                    }

                    String cand = constructPalindrome(leftHalf, oddChar, n);
                    if (cand.compareTo(target) > 0) {
                        return cand;
                    }
                    break;
                }
            }
        }

        return "";
    }

    private String constructPalindrome(int[] leftHalf, int oddChar, int n) {
        StringBuilder sb = new StringBuilder();
        for (int val : leftHalf) {
            sb.append((char) ('a' + val));
        }
        if (n % 2 != 0) {
            sb.append((char) ('a' + oddChar));
        }
        for (int i = leftHalf.length - 1; i >= 0; i--) {
            sb.append((char) ('a' + leftHalf[i]));
        }
        return sb.toString();
    }
}