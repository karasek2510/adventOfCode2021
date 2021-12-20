package Day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Puzzle {

    public static void main(String[] args) {
        try {

            File file = new File("Inputs\\day09.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            int nRow;
            try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                nRow = (int) stream.count();
            }
            int nCol = br.readLine().length();
            br = new BufferedReader(new FileReader(file));
            String st;
            int[][] map = new int[nRow][nCol];
            int iRow = 0;
            while ((st = br.readLine()) != null) {
                String[] splitted = st.split("");
                for (int iCol = 0; iCol < nCol; iCol++) {
                    map[iRow][iCol] = Integer.parseInt(splitted[iCol]);
                }
                iRow++;
            }
            ArrayList<int[]> lowPointsCord = getLowPoints(map);
            ArrayList<Integer> lowPointsValues = new ArrayList<>();
            for (int[] cords : lowPointsCord) {
                lowPointsValues.add(map[cords[0]][cords[1]]);
            }
            int sum = lowPointsValues.stream().mapToInt(a -> a).sum();
            sum += lowPointsValues.size();
            System.out.println("Puzzle1: " + sum);
            ArrayList<Integer> basinsSizes = getBasins(lowPointsCord, map);
            int multi = 1;
            for (int i = 0; i < 3; i++) {
                int max = Collections.max(basinsSizes);
                basinsSizes.remove(new Integer(max));
                multi *= max;
            }
            System.out.println("Puzzle2: " + multi);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<int[]> getLowPoints(int[][] map) {
        int nRow = map.length;
        int nCol = map[0].length;
        ArrayList<int[]> lowPointsCord = new ArrayList<>();
        for (int iRow = 0; iRow < nRow; iRow++) {
            for (int iCol = 0; iCol < nCol; iCol++) {
                int value = map[iRow][iCol];
                //Up
                if (iRow != 0 && value >= map[iRow - 1][iCol]) {
                    continue;
                }
                //Down
                if (iRow != nRow - 1 && value >= map[iRow + 1][iCol]) {
                    continue;
                }
                //Left
                if (iCol != 0 && value >= map[iRow][iCol - 1]) {
                    continue;
                }
                //Right
                if (iCol != nCol - 1 && value >= map[iRow][iCol + 1]) {
                    continue;
                }
                lowPointsCord.add(new int[]{iRow, iCol});
            }
        }

        return lowPointsCord;
    }

    public static ArrayList<Integer> getBasins(ArrayList<int[]> lowPointsCord, int[][] map) {
        ArrayList<Integer> basinsSizes = new ArrayList<>();
        for (int[] cord : lowPointsCord) {
            int nRow = map.length;
            int nCol = map[0].length;
            boolean[][] visited = new boolean[nRow][nCol];
            ArrayList<int[]> queue = new ArrayList<>();
            queue.add(cord);
            int size = 0;
            while (!queue.isEmpty()) {
                size++;
                int[] fieldCord = queue.get(queue.size() - 1);
                queue.remove(queue.size() - 1);
                int iRow = fieldCord[0];
                int iCol = fieldCord[1];
                int value = map[iRow][iCol];
                //Up
                if (iRow != 0 && value < map[iRow - 1][iCol] && map[iRow - 1][iCol] < 9 && !visited[iRow - 1][iCol]) {
                    queue.add(new int[]{iRow - 1, iCol});
                    visited[iRow - 1][iCol] = true;
                }
                //Down
                if (iRow != nRow - 1 && value < map[iRow + 1][iCol] && map[iRow + 1][iCol] < 9 && !visited[iRow + 1][iCol]) {
                    queue.add(new int[]{iRow + 1, iCol});
                    visited[iRow + 1][iCol] = true;
                }
                //Left
                if (iCol != 0 && value < map[iRow][iCol - 1] && map[iRow][iCol - 1] < 9 && !visited[iRow][iCol - 1]) {
                    queue.add(new int[]{iRow, iCol - 1});
                    visited[iRow][iCol - 1] = true;
                }
                //Right
                if (iCol != nCol - 1 && value < map[iRow][iCol + 1] && map[iRow][iCol + 1] < 9 && !visited[iRow][iCol + 1]) {
                    queue.add(new int[]{iRow, iCol + 1});
                    visited[iRow][iCol + 1] = true;
                }
            }
            basinsSizes.add(size);
        }
        return basinsSizes;
    }

}
