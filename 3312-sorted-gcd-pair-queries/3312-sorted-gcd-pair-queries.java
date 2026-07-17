import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Find the maximum value in nums to bound our sieve
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Step 1: Count frequency of each number
        int[] counts = new int[maxVal + 1];
        for (int num : nums) {
            counts[num]++;
        }

        // Step 2: Compute total pairs that have a GCD of i or a multiple of i
        long[] gcdPairsCount = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            long totalDivisible = 0;
            for (int j = i; j <= maxVal; j += i) {
                totalDivisible += counts[j];
            }
            gcdPairsCount[i] = (totalDivisible * (totalDivisible - 1)) / 2;
        }

        // Step 3: Apply Inclusion-Exclusion from back to front to get exact counts
        for (int i = maxVal; i >= 1; i--) {
            for (int j = 2 * i; j <= maxVal; j += i) {
                gcdPairsCount[i] -= gcdPairsCount[j];
            }
        }

        // Step 4: Build prefix sums of the pair counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdPairsCount[i];
        }

        // Step 5: Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Binary search to find the smallest GCD value where prefixSums[gcd] > target
            int low = 1, high = maxVal, ans = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > target) {
                    ans = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = ans;
        }

        return answer;
    }
}