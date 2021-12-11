package Day01;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle {


    private static ArrayList<Integer> getInput() {
        try {
            File file = new File("Inputs\\day01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            ArrayList<Integer> input = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                if (st.isBlank()) {
                    continue;
                }
                input.add(Integer.valueOf(st));
            }
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> input = getInput();

        // Puzzle 1
        int counter = 0;
        for (int i = 1;
             i < input.size();
             i++) {
            if (input.get(i) > input.get(i - 1)) {
                counter++;
            }
        }
        System.out.println(counter);

        // Puzzle 2.
        counter = 0;
        int size = (int) (Math.floor(input.size() / 3) * 3);
        int[] windows = new int[size];
        for (int i = 0;
             i < size;
             i++) {
            windows[i] = input.get(i) + input.get(i + 1) + input.get(i + 2);
        }
        for (int i = 1;
             i < windows.length;
             i++) {
            if (windows[i] > windows[i - 1]) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
