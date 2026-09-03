class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }

        // If there are no odd numbers, all numbers are even -> true
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Check if any even number is strictly smaller than the smallest odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x < minOdd) {
                return false;
            }
        }

        return true;
    }
}