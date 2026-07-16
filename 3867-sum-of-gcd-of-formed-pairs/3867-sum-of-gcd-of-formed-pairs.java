import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx = 0;
        
        // 1. Construct prefixGcd
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = calculateGcd(nums[i], mx);
        }
        
        // 2. Sort prefixGcd in non-decreasing order
        Arrays.sort(prefixGcd);
        
        // 3. Form pairs and compute the sum of their GCDs
        long sum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            sum += calculateGcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return sum;
    }
    
    // Helper method to compute the Greatest Common Divisor
    private int calculateGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}