package Day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Puzzle {

    public static void main(String[] args) {
        try {
            File file = new File("Inputs\\day04.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            int[] numbers = Stream.of(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
            String st;
            ArrayList<Board> boards = new ArrayList<>();
            while ((st = br.readLine()) != null) {
                if (st.isBlank()) {
                    continue;
                }
                int[][] board = new int[5][5];
                for (int i = 0; i < 5; i++) {
                    String[] tempStr = st.trim().replaceAll(" +", " ").split(" ");
                    board[i] = Stream.of(tempStr).mapToInt(Integer::parseInt).toArray();
                    st = br.readLine();
                }
                boards.add(new Board(board));
            }
            boolean first = true;
            int lastBoardScore = 0;
            for (int number : numbers) {
                ArrayList<Board> toDelete = new ArrayList<>();
                for (Board board : boards) {
                    int score = board.checkNumber(number);
                    if (score != -1) {
                        if (first) {
                            System.out.println("First score: " + score);
                            first = false;
                        }
                        lastBoardScore = score;
                        toDelete.add(board);
                    }
                }
                for (Board board : toDelete) {
                    boards.remove(board);
                }
            }
            System.out.println("Last board score: " + lastBoardScore);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Field {
        private final int number;
        private boolean marked;

        public Field(int number) {
            this.number = number;
            this.marked = false;
        }

        public int getNumber() {
            return number;
        }

        public boolean isNotMarked() {
            return !marked;
        }

        private void mark() {
            marked = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Field field = (Field) o;
            return number == field.number;
        }

    }

    private static class Board {

        private final ArrayList<ArrayList<Field>> board = new ArrayList<>();

        public Board(int[][] data) {
            for (int[] datum : data) {
                ArrayList<Field> row = new ArrayList<>();
                for (int i : datum) {
                    row.add(new Field(i));
                }
                board.add(row);
            }
        }


        public int checkNumber(int number) {
            for (ArrayList<Field> row : board) {
                int index = row.indexOf(new Field(number));
                if (index != -1) {
                    row.get(index).mark();
                    if (checkBingo()) {
                        return calculateScore(number);
                    }
                }
            }
            return -1;
        }

        public boolean checkBingo() {
            for (ArrayList<Field> row : board) {
                int temp = 0;
                for (Field field : row) {
                    if (field.isNotMarked()) {
                        break;
                    }
                    temp++;
                }
                if (temp == 5) {
                    return true;
                }
            }
            for (int i = 0; i < 5; i++) {
                int temp = 0;
                for (int j = 0;
                     j < 5;
                     j++) {
                    if (board.get(j).get(i).isNotMarked()) {
                        break;
                    }
                    temp++;
                }
                if (temp == 5) {
                    return true;
                }
            }
            return false;
        }

        public int calculateScore(int number) {
            int sum = 0;
            for (ArrayList<Field> row : board) {
                for (Field field : row) {
                    if (field.isNotMarked()) {
                        sum += field.getNumber();
                    }
                }
            }
            return number * sum;
        }

    }

}
