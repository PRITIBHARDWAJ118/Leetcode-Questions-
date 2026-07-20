  import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        // Effective shifts needed
        k = k % total;
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Calculate original 1D index after shifting backwards by k
                int flatIndex = (i * n + j - k) % total;
                if (flatIndex < 0) {
                    flatIndex += total;
                }
                
                // Convert 1D index back to 2D coordinates in the original grid
                int origRow = flatIndex / n;
                int origCol = flatIndex % n;
                
                row.add(grid[origRow][origCol]);
            }
            result.add(row);
        }
        
        return result;
    }
}
    