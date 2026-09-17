class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000000;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = INF;
        }
        int left = 0;
        int sum = 0;
        int ans = INF;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            dp[right + 1] = dp[right];
            if (sum == target) {
                int len = right - left + 1;
                if (dp[left] != INF) {
                    ans = Math.min(ans, dp[left] + len);
                }
                dp[right + 1] = Math.min(dp[right + 1], len);
            }
        }
        return ans == INF ? -1 : ans;
    }
}