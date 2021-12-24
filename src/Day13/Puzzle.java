package Day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Puzzle {

    public static void foldAlongHorizontal(HashSet<int[]> paper, int yFold) {
        HashSet<int[]> toAdd = new HashSet<>();
        HashSet<int[]> toRemove = new HashSet<>();
        for (int[] point : paper) {
            int x = point[0];
            int y = point[1];
            if (y > yFold) {
                toRemove.add(point);
                int yNew = 2 * yFold - y; // yNew = yFold-(y-yFold) = 2*yFold-y;
                int[] newPoint = {x, yNew};
                if (paper.stream().noneMatch(a -> Arrays.equals(a, newPoint))) {
                    toAdd.add(newPoint);
                }
            }
        }
        paper.removeAll(toRemove);
        paper.addAll(toAdd);
    }

    public static void foldAlongVertical(HashSet<int[]> paper, int xFold) {
        HashSet<int[]> toAdd = new HashSet<>();
        HashSet<int[]> toRemove = new HashSet<>();
        for (int[] point : paper) {
            int x = point[0];
            int y = point[1];
            if (x > xFold) {
                toRemove.add(point);
                int xNew = 2 * xFold - x; // xNew = xFold-(x-xFold) = 2*xFold-x;
                int[] newPoint = {xNew, y};
                if (paper.stream().noneMatch(a -> Arrays.equals(a, newPoint))) {
                    toAdd.add(newPoint);
                }
            }
        }
        paper.removeAll(toRemove);
        paper.addAll(toAdd);
    }


    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day13.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        HashSet<int[]> paper = new HashSet<>();
        while (!(st = br.readLine()).isEmpty()) {
            String[] splitted = st.split(",");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            paper.add(new int[]{x, y});
        }
        ArrayList<String> folds = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            folds.add(st);
        }
        //Puzzle 1
        String fold = folds.get(0);
        String line = fold.split(" ")[2];
        String[] splitted = line.split("=");
        if (splitted[0].equals("x")) {
            foldAlongVertical(paper, Integer.parseInt(splitted[1]));
        } else {
            foldAlongHorizontal(paper, Integer.parseInt(splitted[1]));
        }
        System.out.println("Puzzle1: " + paper.size());

        // Puzzle 2
        folds.remove(0);
        for (String fold2 : folds) {
            line = fold2.split(" ")[2];
            splitted = line.split("=");
            if (splitted[0].equals("x")) {
                foldAlongVertical(paper, Integer.parseInt(splitted[1]));
            } else {
                foldAlongHorizontal(paper, Integer.parseInt(splitted[1]));
            }
        }

        // Print answer
        int xMax = 0;
        int yMax = 0;
        for (int[] point : paper) {
            if (point[0] > xMax) {
                xMax = point[0];
            }
            if (point[1] > yMax) {
                yMax = point[1];
            }
        }
        String[][] toPrint = new String[yMax + 1][xMax + 1];
        for (String[] row : toPrint) {
            Arrays.fill(row, ".");
        }
        for (int[] point : paper) {
            toPrint[point[1]][point[0]] = "#";
        }
        System.out.println("Puzzle2: ");
        for (String[] row : toPrint) {
            for (String val : row) {
                System.out.print(val);
            }
            System.out.println();
        }


    }

}
