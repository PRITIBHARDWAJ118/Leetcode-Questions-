class Solution {
    private Integer[][] memo;
    private int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new Integer[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int left, int right) {
        if (left == right) {
            return 0;
        }

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        int maxScore = 0;

        for (int mid = left; mid < right; mid++) {
            int leftSum = prefix[mid + 1] - prefix[left];
            int rightSum = prefix[right + 1] - prefix[mid + 1];

            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(left, mid));
            } else if (rightSum < leftSum) {
                maxScore = Math.max(maxScore, rightSum + solve(mid + 1, right));
            } else {
                int takeLeft = leftSum + solve(left, mid);
                int takeRight = rightSum + solve(mid + 1, right);
                maxScore = Math.max(maxScore, Math.max(takeLeft, takeRight));
            }
        }

        return memo[left][right] = maxScore;
    }
}