import java.util.*;

class Solution {
    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }
    static class State {
        long score;
        List<Integer> list;
        State(long score, List<Integer> list) {
            this.score = score;
            this.list = list;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<Interval> a = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            a.add(new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            ));
        }
        a.sort((x, y) -> Integer.compare(x.l, y.l));
        State[][] dp = new State[n + 1][5];
        for(int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }
        for(int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new ArrayList<>());
            for(int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];
                int j = findNext(a, i + 1, a.get(i).r);
                State next = dp[j][k - 1];
                List<Integer> picked = new ArrayList<>(next.list);
                picked.add(a.get(i).idx);
                Collections.sort(picked);
                State take = new State(a.get(i).w + next.score, picked);
                if(take.score > skip.score) {
                    dp[i][k] = take;
                } else if(take.score < skip.score) {
                    dp[i][k] = skip;
                } else {
                    dp[i][k] = compare(take.list, skip.list) < 0 ? take : skip;
                }
            }
        }
        List<Integer> ans = dp[0][4].list;
        int[] result = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
    private int findNext(List<Interval> a, int start, int right) {
        int l = start;
        int r = a.size();
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(a.get(mid).l > right) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int compare(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());
        for(int i = 0; i < n; i++) {
            if(!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }
        return Integer.compare(a.size(), b.size());
    }
}