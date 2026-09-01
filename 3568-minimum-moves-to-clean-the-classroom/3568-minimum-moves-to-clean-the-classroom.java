import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        // Target mask when all litter is collected
        int targetMask = (1 << litterCount) - 1;
        if (targetMask == 0) return 0;

        // bestEnergy[x][y][mask] stores max energy seen for state (x, y, mask)
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        
        // If starting cell is 'L' or 'R'
        int initialMask = 0;
        if (classroom[startX].charAt(startY) == 'L') {
            initialMask |= (1 << litterId[startX][startY]);
        }
        
        queue.offer(new int[]{startX, startY, initialMask, energy});
        bestEnergy[startX][startY][initialMask] = energy;

        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], e = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                if (e == 0) continue; // Can't move if energy is depleted

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        char cell = classroom[nr].charAt(nc);
                        int nextMask = mask;
                        int nextEnergy = e - 1;

                        if (cell == 'L' && litterId[nr][nc] != -1) {
                            nextMask |= (1 << litterId[nr][nc]);
                        } else if (cell == 'R') {
                            nextEnergy = energy; // Energy reset
                        }

                        if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                            bestEnergy[nr][nc][nextMask] = nextEnergy;
                            queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}