package Day15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Puzzle {

    public static int shortestPath(int[][] map, int startX, int startY, int destinationX, int destinationY) {
        long timeStart = System.currentTimeMillis();
        int nRow = map.length;
        int nCol = map[0].length;
        int[][] distances = new int[nRow][nCol];
        boolean[][] visited = new boolean[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
            Arrays.fill(visited[i], false);
        }
        distances[startY][startX] = 0;
        visited[startY][startX] = true;
        int xCurr;
        int yCurr;
        while(!visited[destinationY][destinationX]) {
            int[] tempNode = minXY(distances, visited);
            xCurr = tempNode[0];
            yCurr = tempNode[1];
            visited[yCurr][xCurr] = true;
            for (int[] neighbour : findNeighbours(xCurr, yCurr, visited)) {
                int tempX = neighbour[0];
                int tempY = neighbour[1];
                int alt = distances[yCurr][xCurr] + map[tempY][tempX];
                if (alt < distances[tempY][tempX]) {
                    distances[tempY][tempX] = alt;
                }
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - timeStart) / 1000);
        return distances[destinationY][destinationX];
    }

    public static int[] minXY(int[][] distances, boolean[][] visited) {
        int value = Integer.MAX_VALUE;
        int[] xy = new int[2];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                if (distances[i][j] < value && !visited[i][j]) {
                    value = distances[i][j];
                    xy[0] = j;
                    xy[1] = i;
                }
            }
        }
        return xy;
    }

    public static ArrayList<int[]> findNeighbours(int x, int y, boolean[][] visited) {
        ArrayList<int[]> neighbours = new ArrayList<>();
        int nRow = visited.length;
        int nCol = visited[0].length;
        if (x != 0 && !visited[y][x - 1]) {
            neighbours.add(new int[]{x - 1, y});
        }
        if (x != nCol - 1 && !visited[y][x + 1]) {
            neighbours.add(new int[]{x + 1, y});
        }
        if (y != 0 && !visited[y - 1][x]) {
            neighbours.add(new int[]{x, y - 1});
        }
        if (y != nRow - 1 && !visited[y + 1][x]) {
            neighbours.add(new int[]{x, y + 1});
        }
        return neighbours;
    }

    public static int[][] buildTrueMap(int[][] oldMap) {
        int nRowOldMap = oldMap.length;
        int nColOldMap = oldMap[0].length;
        int nRowNewMap = oldMap.length * 5;
        int nColNewMap = oldMap[0].length * 5;
        int[][] newMap = new int[nRowOldMap * 5][nColOldMap * 5];
        for (int i = 0; i < nRowNewMap; i += nRowOldMap) {
            for (int j = 0; j < nColNewMap; j += nColOldMap) {
                int iTemp = i / nRowOldMap;
                int jTemp = j / nColOldMap;
                pasteIntoArray(i, j, newMap, increaseRiskLevels(oldMap, iTemp + jTemp));
            }
        }
        return newMap;
    }

    public static void pasteIntoArray(int x, int y, int[][] pasteInto, int[][] toPaste) {
        for (int i = y; i < y + toPaste.length; i++) {
            System.arraycopy(toPaste[i - y], 0, pasteInto[i], x, x + toPaste[0].length - x);
        }
    }

    public static int[][] increaseRiskLevels(int[][] map, int by) {
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j] + by;
                if (newMap[i][j] > 9) {
                    newMap[i][j] = newMap[i][j] % 9;
                }
            }
        }
        return newMap;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day15.txt");
        int nRow;
        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            nRow = (int) stream.count();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        int nCol = br.readLine().length();
        br = new BufferedReader(new FileReader(file));
        int[][] map = new int[nRow][nCol];
        for (int i = 0; i < nRow; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        int distance = shortestPath(map, 0, 0,nCol-1,nRow-1);
        System.out.println("Puzzle1: " + distance);
        int[][] trueMap = buildTrueMap(map);
        distance = shortestPath(trueMap, 0, 0,5*nCol-1,5*nRow-1);
        System.out.println("Puzzle2: " + distance);
    }

}
