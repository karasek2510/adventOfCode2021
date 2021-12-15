package Day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Puzzle {

    public static void cycle(long[] fishes) {
        long temp1 = 0;
        long temp2 = 0;
        for (int i = 8; i >= 0; i--) {
            temp1 = fishes[i];
            fishes[i] = temp2;
            temp2 = temp1;
        }
        fishes[8] = temp1;
        fishes[6] += temp1;
    }

    public static void main(String[] args) {
        try {
            File file = new File("Inputs\\day06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st = br.readLine();
            long[] fishes1 = new long[9];
            String[] splitted = st.split(",");
            for (String fish : splitted) {
                fishes1[Integer.parseInt(fish)] += 1;
            }
            long[] fishes2 = Arrays.copyOf(fishes1, fishes1.length);
            for (int i = 0; i < 80; i++) {
                cycle(fishes1);
            }
            long sum = 0;
            for (long fish : fishes1) {
                sum += fish;
            }
            System.out.println("Puzzle1: " + sum);
            for (int i = 0; i < 256; i++) {
                cycle(fishes2);
            }
            sum = 0;
            for (long fish : fishes2) {
                sum += fish;
            }
            System.out.println("Puzzle2: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
