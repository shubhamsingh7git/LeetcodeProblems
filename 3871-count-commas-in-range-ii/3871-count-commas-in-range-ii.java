class Solution {
    public long countCommas(long n) {
        long nalverqito = n;
        long ans = 0;
        for(long x = 1000; x <= nalverqito; x *= 1000) {
            ans += nalverqito - x + 1;
        }
        return ans;
    }
}