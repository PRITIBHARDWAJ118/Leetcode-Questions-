class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        // Base case: subarray of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill DP table for lengths from 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        // Player 1 can win or tie if max net score difference >= 0
        return dp[0][n - 1] >= 0;
    }
}