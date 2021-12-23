package Day11;

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

    public static int doOneStep(int[][] cavern) {
        int nRow = cavern.length;
        int nCol = cavern[0].length;
        boolean[][] flashed = new boolean[nRow][nCol];
        // Incrementing energy levels
        for (int iRow = 0; iRow < nRow; iRow++) {
            for (int iCol = 0; iCol < nCol; iCol++) {
                cavern[iRow][iCol]++;
            }
        }
        ArrayList<int[]> directions = new ArrayList<>();
        directions.add(new int[]{-1, -1}); // North-west
        directions.add(new int[]{-1, 0}); // North
        directions.add(new int[]{-1, 1}); // North-east
        directions.add(new int[]{0, 1}); // East
        directions.add(new int[]{1, 1}); // South-east
        directions.add(new int[]{1, 0}); // South
        directions.add(new int[]{1, -1}); // South-west
        directions.add(new int[]{0, -1}); // West
        // Flashes
        int cumFlashes = 0;
        int newFlashes;
        do {
            newFlashes = 0;
            for (int iRow = 0; iRow < nRow; iRow++) {
                for (int iCol = 0; iCol < nCol; iCol++) {
                    if (cavern[iRow][iCol] > 9) {
                        cavern[iRow][iCol] = 0;
                        flashed[iRow][iCol] = true;
                        newFlashes++;
                        for (int[] direction : directions) {
                            try {
                                int y = iRow + direction[0];
                                int x = iCol + direction[1];
                                if (!flashed[y][x]) {
                                    cavern[y][x]++;
                                }
                            } catch (Exception ignored) {

                            }
                        }
                    }
                }
            }
            cumFlashes += newFlashes;
        } while (newFlashes != 0);

        return cumFlashes;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day11.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int nRow;
        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            nRow = (int) stream.count();
        }
        int nCol = br.readLine().length();
        br = new BufferedReader(new FileReader(file));
        String st;
        int[][] cavern1 = new int[nRow][nCol];
        for (int iRow = 0; iRow < nRow; iRow++) {
            st = br.readLine();
            cavern1[iRow] = Stream.of(st.split("")).mapToInt(Integer::parseInt).toArray();
        }
        int[][] cavern2 = Arrays.stream(cavern1).map(int[]::clone).toArray(int[][]::new);
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            flashes += doOneStep(cavern1);
        }
        System.out.println("Puzzle1: " + flashes);
        int octopusNumb = cavern2.length * cavern2[0].length;
        int index = 0;
        int flashesNumb;
        do {
            index++;
            flashesNumb = doOneStep(cavern2);
        } while (flashesNumb != octopusNumb);
        System.out.println("Puzzle2: " + index);
    }

}
