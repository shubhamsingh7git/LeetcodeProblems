class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        long[] dp = new long[26];
        long total = 0;
        for(char c : s.toCharArray()) {
            int x = c - 'a';
            long old = dp[x];
            dp[x] = (total + 1) % MOD;
            total = (total + dp[x] - old + MOD) % MOD;
        }
        return (int)total;
    }
}