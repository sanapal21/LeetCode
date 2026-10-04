class Solution {
    private int m, n;
    private int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Start DFS from Pacific borders
        for (int c = 0; c < n; c++) {
            dfs(0, c, heights, pacific);
        }

        for (int r = 0; r < m; r++) {
            dfs(r, 0, heights, pacific);
        }

        // Start DFS from Atlantic borders
        for (int c = 0; c < n; c++) {
            dfs(m - 1, c, heights, atlantic);
        }

        for (int r = 0; r < m; r++) {
            dfs(r, n - 1, heights, atlantic);
        }

        // Find cells reachable from both oceans
        List<List<Integer>> result = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int r, int c, int[][] heights, boolean[][] visited) {
        visited[r][c] = true;

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }

            if (visited[nr][nc]) {
                continue;
            }

            if (heights[nr][nc] >= heights[r][c]) {
                dfs(nr, nc, heights, visited);
            }
        }
    }
}