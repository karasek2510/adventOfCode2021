package Day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle {

    public static void main(String[] args) {

        // Puzzle 1

        try {
            File file = new File("Inputs\\day02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int horizontal = 0;
            int depth = 0;
            while ((st = br.readLine()) != null) {
                if (st.isBlank()) {
                    continue;
                }
                String[] splitted = st.split(" ");
                if (splitted[0].equals("forward")) {
                    horizontal += Integer.parseInt(splitted[1]);
                } else {
                    if (splitted[0].equals("up")) {
                        depth -= Integer.parseInt(splitted[1]);
                    } else {
                        if (splitted[0].equals("down")) {
                            depth += Integer.parseInt(splitted[1]);
                        }
                    }
                }
            }
            System.out.println("Depth: " + depth);
            System.out.println("Horizontal: " + horizontal);
            System.out.println("Multi: " + depth * horizontal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Puzzle 2

        try {
            File file = new File("Inputs\\day02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int horizontal = 0;
            int aim = 0;
            int depth = 0;
            while ((st = br.readLine()) != null) {
                if (st.isBlank()) {
                    continue;
                }
                String[] splitted = st.split(" ");
                if (splitted[0].equals("forward")) {
                    horizontal += Integer.parseInt(splitted[1]);
                    depth += Integer.parseInt(splitted[1]) * aim;
                } else {
                    if (splitted[0].equals("up")) {
                        aim -= Integer.parseInt(splitted[1]);
                    } else {
                        if (splitted[0].equals("down")) {
                            aim += Integer.parseInt(splitted[1]);
                        }
                    }
                }
            }
            System.out.println("Depth: " + depth);
            System.out.println("Horizontal: " + horizontal);
            System.out.println("Multi: " + depth * horizontal);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
