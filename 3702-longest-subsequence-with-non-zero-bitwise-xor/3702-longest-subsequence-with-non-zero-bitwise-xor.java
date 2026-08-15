class Solution {
    public int longestSubsequence(int[] nums) {
        int xorAll = 0;
        boolean allZero = true;
        
        for (int num : nums) {
            xorAll ^= num;
            if (num != 0) {
                allZero = false;
            }
        }
        
        // If all elements are 0, any subsequence will have XOR = 0
        if (allZero) {
            return 0;
        }
        
        // If total XOR is non-zero, take the whole array
        if (xorAll != 0) {
            return nums.length;
        }
        
        // If total XOR is 0 but non-zero elements exist, 
        // removing any single non-zero element leaves XOR non-zero
        return nums.length - 1;
    }
}