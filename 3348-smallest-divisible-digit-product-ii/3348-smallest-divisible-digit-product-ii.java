import java.util.*;

class Solution {
    // Prime factors corresponding to 2, 3, 5, 7 for digits 1 to 9
    private static final int[][] FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {
        int[] target = new int[4];
        long tempT = t;
        
        // Factorize t into prime factors 2, 3, 5, 7
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                target[i]++;
                tempT /= primes[i];
            }
        }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();
        int firstZero = num.indexOf('0');
        
        // Count total factor contribution of original num prefix
        int[][] prefixFactors = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            for (int j = 0; j < 4; j++) {
                prefixFactors[i + 1][j] = prefixFactors[i][j];
            }
            if (d > 0) {
                for (int j = 0; j < 4; j++) {
                    prefixFactors[i + 1][j] += FACTORS[d][j];
                }
            }
        }

        // Case 1: Check if num itself is valid (no zeros and product divisible by t)
        if (firstZero == -1) {
            boolean valid = true;
            for (int j = 0; j < 4; j++) {
                if (prefixFactors[n][j] < target[j]) {
                    valid = false;
                    break;
                }
            }
            if (valid) return num;
        }

        // Case 2: Try replacing a digit at index i with a larger digit d
        // Iterate from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Cannot keep digits at or after the first zero
            if (firstZero != -1 && i > firstZero) continue;

            int currentDigit = num.charAt(i) - '0';
            
            for (int biggerDigit = currentDigit + 1; biggerDigit <= 9; biggerDigit++) {
                int[] remainingNeeded = new int[4];
                for (int j = 0; j < 4; j++) {
                    int provided = prefixFactors[i][j] + FACTORS[biggerDigit][j];
                    remainingNeeded[j] = Math.max(0, target[j] - provided);
                }

                int availableLength = n - 1 - i;
                int minLengthNeeded = minDigitsNeeded(remainingNeeded);

                if (minLengthNeeded <= availableLength) {
                    // Valid prefix found, construct the smallest valid trailing digits
                    StringBuilder sb = new StringBuilder();
                    sb.append(num.substring(0, i));
                    sb.append(biggerDigit);

                    String suffix = constructSmallestSuffix(remainingNeeded, availableLength);
                    sb.append(suffix);
                    return sb.toString();
                }
            }
        }

        // Case 3: Need to increase the total length of the number
        int minLengthNeeded = minDigitsNeeded(target);
        int totalLength = Math.max(n + 1, minLengthNeeded);

        StringBuilder sb = new StringBuilder();
        String suffix = constructSmallestSuffix(target, totalLength);
        sb.append(suffix);
        return sb.toString();
    }

    // Returns minimum number of digits (from 2..9) required to satisfy prime factor requirements
    private int minDigitsNeeded(int[] req) {
        int r2 = req[0], r3 = req[1], r5 = req[2], r7 = req[3];
        
        int count = r5 + r7;        // Digits 5 and 7
        count += (r3 + 1) / 2;      // Digits 9 (covers two 3s each)
        
        int rem2 = r2;
        if (r3 % 2 != 0) {
            rem2 = Math.max(0, rem2 - 1); // Extra '3' can pair with '2' to form '6'
        }
        
        count += (rem2 + 2) / 3;    // Digits 8 (covers three 2s each)
        return count;
    }

    // Constructs the lexicographically smallest string of given length satisfying required factors
    private String constructSmallestSuffix(int[] req, int targetLength) {
        int r2 = req[0], r3 = req[1], r5 = req[2], r7 = req[3];
        List<Integer> digits = new ArrayList<>();

        // Greedily collect largest available digits (9, 8, 7, 5, 6, 4, 3, 2)
        for (int i = 0; i < r7; i++) digits.add(7);
        for (int i = 0; i < r5; i++) digits.add(5);

        while (r3 >= 2) {
            digits.add(9);
            r3 -= 2;
        }

        while (r2 >= 3) {
            digits.add(8);
            r2 -= 3;
        }

        if (r3 > 0 && r2 > 0) {
            digits.add(6);
            r3--;
            r2--;
        } else if (r3 > 0) {
            digits.add(3);
            r3--;
        }

        if (r2 == 2) {
            digits.add(4);
            r2 -= 2;
        } else if (r2 == 1) {
            digits.add(2);
            r2--;
        }

        Collections.sort(digits);

        StringBuilder sb = new StringBuilder();
        // Fill remaining spaces with '1's at the beginning to keep total number minimal
        int onesNeeded = targetLength - digits.size();
        for (int i = 0; i < onesNeeded; i++) {
            sb.append('1');
        }

        for (int d : digits) {
            sb.append(d);
        }

        return sb.toString();
    }
}