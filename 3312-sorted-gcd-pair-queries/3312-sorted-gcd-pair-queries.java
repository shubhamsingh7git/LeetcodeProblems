class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] freq = new int[mx + 1];
        for (int x : nums) {
            freq[x]++;
        }
        long[] cnt = new long[mx + 1];
        for (int g = mx; g >= 1; g--) {
            long total = 0;
            for (int j = g; j <= mx; j += g) {
                total += freq[j];
                cnt[g] -= cnt[j];
            }
            cnt[g] += total * (total - 1) / 2;
        }
        long[] prefix = new long[mx + 1];
        for (int i = 1; i <= mx; i++) {
            prefix[i] = prefix[i - 1] + cnt[i];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int left = 1;
            int right = mx;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] > q) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = left;
        }
        return ans;
    }
}