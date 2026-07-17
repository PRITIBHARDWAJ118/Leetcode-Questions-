class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set pointers for the end of the initialized parts of both arrays
        int p1 = m - 1;
        int p2 = n - 1;
        // Set a pointer for the very end of nums1 (where the next largest element goes)
        int p = m + n - 1;

        // Move backwards, comparing elements and placing the larger one at the end
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }
}