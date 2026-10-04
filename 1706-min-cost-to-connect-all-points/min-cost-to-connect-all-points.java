class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        boolean[] visited = new boolean[n];

        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

       
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        minDist[0] = 0;
        pq.offer(new int[]{0, 0});

        int totalCost = 0;
        int connected = 0;

        while (connected < n) {
            int[] current = pq.poll();

            int cost = current[0];
            int u = current[1];

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            totalCost += cost;
            connected++;

            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int distance =
                        Math.abs(points[u][0] - points[v][0]) +
                        Math.abs(points[u][1] - points[v][1]);

                    if (distance < minDist[v]) {
                        minDist[v] = distance;
                        pq.offer(new int[]{distance, v});
                    }
                }
            }
        }

        return totalCost;
    }
}