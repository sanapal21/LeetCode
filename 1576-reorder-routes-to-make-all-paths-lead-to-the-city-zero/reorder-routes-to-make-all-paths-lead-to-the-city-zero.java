import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : connections) {
            int a = edge[0];
            int b = edge[1];

            graph[a].add(new int[]{b, 1});
            graph[b].add(new int[]{a, 0});
        }

        return dfs(0, -1, graph);
    }

    private int dfs(int node, int parent, List<int[]>[] graph) {

        int changes = 0;

        for (int[] neighbor : graph[node]) {
            int next = neighbor[0];
            int cost = neighbor[1];

            if (next == parent) {
                continue;
            }

            changes += cost;
            changes += dfs(next, node, graph);
        }

        return changes;
    }
}