package Day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Puzzle {
    public static void main(String[] args) {

        // Puzzle 1

        try {
            File file = new File("Inputs\\day03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st = br.readLine();
            int numberOfLines = 0;
            int[] binaryOneArray = new int[st.length()];
            while (st != null) {
                if (st.isBlank()) {
                    continue;
                }
                String[] splitted = st.split("");
                for (int i = 0;
                     i < splitted.length;
                     i++) {
                    if (splitted[i].equals("1")) {
                        binaryOneArray[i]++;
                    }
                }
                st = br.readLine();
                numberOfLines++;
            }
            StringBuilder gamma = new StringBuilder();
            StringBuilder epsilon = new StringBuilder();
            for (int bit : binaryOneArray) {
                if (bit > numberOfLines - bit) {
                    gamma.append("1");
                    epsilon.append("0");
                } else {
                    gamma.append("0");
                    epsilon.append("1");
                }
            }
            int gammaInt = Integer.parseInt(gamma.toString(), 2);
            int epsilonInt = Integer.parseInt(epsilon.toString(), 2);
            System.out.println("Gamma: " + gammaInt);
            System.out.println("Epsilon: " + epsilonInt);
            System.out.println("Multi: " + gammaInt * epsilonInt);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Puzzle 2

       
    }

    /**
     * mostCommon:
     *      - True: searching for the most common bits (oxygen)
     *      - False: searching for the least common bits (co2)
     */

    private static int getRating (ArrayList<String[]> data, boolean mostCommon){
        for(int i = 0; i<data.get(0).length;i++){
            ArrayList<String[]> dataOne = new ArrayList<>();
            ArrayList<String[]> dataZero = new ArrayList<>();
            for(int j = 0; j<data.size();j++){
                if(data.get(j)[i].equals("1")){
                    dataOne.add(data.get(j));
                }else{
                    dataZero.add(data.get(j));
                }
            }
            if(mostCommon){
                if(dataOne.size()>dataZero.size()){
                    data=dataOne;
                }else{
                    data=dataZero;
                }
            }else{
                if(dataOne.size()>dataZero.size()){
                    data=dataZero;
                }else{
                    data=dataOne;
                }
            }
            if(data.size()==1){
                break;
            }
        }
        return Integer.parseInt(String.join("",data.get(0)),2);
    }
}
