package Day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Puzzle {
    public static String[] bracketsBegin = {"(", "[", "{", "<"};
    public static String[] bracketsClose = {")", "]", "}", ">"};

    public static void main(String[] args) {
        try {
            File file = new File("Inputs\\day10.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int sum = 0;
            ArrayList<String[]> incomplete = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                String[] symbols = st.split("");
                String illegalCharacter = checkForErrors(symbols);
                if (illegalCharacter == null) {
                    incomplete.add(symbols);
                    continue;
                }
                String[] bracketsClose = {")", "]", "}", ">"};
                int[] bracketsPointsPuzzle1 = {3, 57, 1197, 25137};
                for (int i = 0; i < 4; i++) {
                    if (illegalCharacter.equals(bracketsClose[i])) {
                        sum += bracketsPointsPuzzle1[i];
                        break;
                    }
                }
            }
            System.out.println("Puzzle1: " + sum);
            ArrayList<Long> scores = new ArrayList<>();
            for (String[] symbols : incomplete) {
                ArrayList<String> sequence = completeSequence(symbols);
                long score = 0;
                for (int i = sequence.size() - 1; i >= 0; i--) {
                    score *= 5;
                    int[] bracketsPointsPuzzle2 = {1, 2, 3, 4};
                    for (int j = 0; j < 4; j++) {
                        if (sequence.get(i).equals(bracketsBegin[j])) {
                            score += bracketsPointsPuzzle2[j];
                            break;
                        }
                    }
                }
                scores.add(score);
            }
            Collections.sort(scores);
            long score = scores.get((int) Math.ceil(scores.size() / 2));

            System.out.println("Puzzle2: " + score);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String checkForErrors(String[] symbols) {
        ArrayList<String> stack = new ArrayList<>();
        for (String symbol : symbols) {
            if (symbol.equals("(") || symbol.equals("{") || symbol.equals("<") || symbol.equals("[")) {
                stack.add(symbol);
            } else {
                for (int i = 0; i < 4; i++) {
                    if (symbol.equals(bracketsClose[i])) {
                        if (stack.get(stack.size() - 1).equals(bracketsBegin[i])) {
                            stack.remove(stack.size() - 1);
                            break;
                        } else {
                            return symbol;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static ArrayList<String> completeSequence(String[] symbols) {
        ArrayList<String> stack = new ArrayList<>();
        for (String symbol : symbols) {
            if (symbol.equals("(") || symbol.equals("{") || symbol.equals("<") || symbol.equals("[")) {
                stack.add(symbol);
            } else {
                for (int i = 0; i < 4; i++) {
                    if (symbol.equals(bracketsClose[i])) {
                        if (stack.get(stack.size() - 1).equals(bracketsBegin[i])) {
                            stack.remove(stack.size() - 1);
                        }
                    }
                }
            }
        }
        return stack;
    }

}
