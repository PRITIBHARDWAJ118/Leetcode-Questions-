class Solution {
    public long findKthSmallest(int[] coins, long k) {
        long low = 1;
        long high = (long) coins[0] * k;
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;
            if (count(coins, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(int[] coins, long target) {
        int n = coins.length;
        long count = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bits++;
                    lcmVal = lcm(lcmVal, coins[i]);
                    if (lcmVal > target) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (bits % 2 == 1) {
                count += target / lcmVal;
            } else {
                count -= target / lcmVal;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}