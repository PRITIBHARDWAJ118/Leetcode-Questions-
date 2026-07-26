class Solution {
    public int maximumProduct(int[] nums) {
        // Track 3 largest elements
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // Track 2 smallest elements (negative numbers with largest magnitude)
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int n : nums) {
            // Update maximums
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            // Update minimums
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

       
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}