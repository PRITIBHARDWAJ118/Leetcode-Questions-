class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

        // Option 1: Delete both from the front
        int bothFront = right + 1;

        // Option 2: Delete both from the back
        int bothBack = n - left;

        // Option 3: Delete one from the front, one from the back
        int frontAndBack = (left + 1) + (n - right);

        return Math.min(bothFront, Math.min(bothBack, frontAndBack));
    }
}