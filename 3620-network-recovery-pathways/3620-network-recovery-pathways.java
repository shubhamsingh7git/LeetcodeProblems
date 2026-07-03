import java.util.*;

class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int hi = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            hi = Math.max(hi, e[2]);
        }

        int lo = 0;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(graph, online, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(List<int[]>[] graph, boolean[] online, long k, int limit) {

        int n = graph.length;

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {

            long[] cur = pq.poll();

            int u = (int) cur[0];
            long d = cur[1];

            if (d != dist[u])
                continue;

            if (d > k)
                continue;

            if (u == n - 1)
                return true;

            for (int[] e : graph[u]) {

                int v = e[0];
                int w = e[1];

                if (w < limit)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                long nd = d + w;

                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{v, nd});
                }
            }
        }

        return false;
    }
}