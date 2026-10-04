class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if (originalColor == color) {
            return image;
        }

        dfs(image, sr, sc, originalColor, color);

        return image;
    }

    private void dfs(int[][] image, int row, int col,
                     int originalColor, int color) {

        int m = image.length;
        int n = image[0].length;

        if (row < 0 || row >= m ||
            col < 0 || col >= n ||
            image[row][col] != originalColor) {
            return;
        }

        image[row][col] = color;

        dfs(image, row + 1, col, originalColor, color); 
        dfs(image, row - 1, col, originalColor, color); 
        dfs(image, row, col + 1, originalColor, color); 
        dfs(image, row, col - 1, originalColor, color); 
    }
}