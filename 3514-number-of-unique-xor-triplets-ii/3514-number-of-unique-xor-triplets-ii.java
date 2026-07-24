class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // 1. Extract unique values from nums
        boolean[] present = new boolean[2048];
        int uniqueCount = 0;
        for (int x : nums) {
            if (!present[x]) {
                present[x] = true;
                uniqueCount++;
            }
        }
        
        int[] S = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (present[i]) {
                S[idx++] = i;
            }
        }
        
        // 2. Compute all unique pairwise XOR values
        boolean[] pairXor = new boolean[2048];
        for (int i = 0; i < S.length; i++) {
            for (int j = i; j < S.length; j++) {
                pairXor[S[i] ^ S[j]] = true;
            }
        }
        
        // 3. Compute all unique triplet XOR values
        boolean[] tripletXor = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (pairXor[p]) {
                for (int x : S) {
                    tripletXor[p ^ x] = true;
                }
            }
        }
        
        // 4. Count total unique XOR values
        int ans = 0;
        for (boolean b : tripletXor) {
            if (b) ans++;
        }
        
        return ans;
    }
}