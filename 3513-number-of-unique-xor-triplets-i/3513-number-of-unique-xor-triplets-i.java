class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // For n >= 3, any integer in the range [0, 2^(msb(n) + 1) - 1] can be formed.
        return Integer.highestOneBit(n) << 1;
    }
}