package Day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;


public class Puzzle {

    public static void main(String[] args) {
        try {
            File file = new File("Inputs\\day07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st = br.readLine();
            String[] splitted = st.split(",");
            int[] positions = new int[splitted.length];
            for(int i = 0; i<splitted.length;i++){
                int posInt = Integer.parseInt(splitted[i]);
                positions[i]=posInt;
            }
            Arrays.sort(positions);
            int med = positions[positions.length/2];
            System.out.println(med);
            int sum = 0;
            for(int pos: positions){
                sum+=Math.abs(med-pos);
            }
            System.out.println(sum);
            int[] fuels = new int[1000];
            for(int i = 0; i<1000; i++){
                int fuel = 0;
                for(int crab : positions){
                    int n = Math.abs(i-crab);
                    fuel+=n*(n+1)/2;
                }
                fuels[i]=fuel;
            }
            Arrays.sort(fuels);
            System.out.println(fuels[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
