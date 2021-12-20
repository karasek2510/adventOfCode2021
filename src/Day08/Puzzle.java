package Day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle {

    public static int sub(String string, String substring) {
        int index = 0;
        int counter = 0;
        for (char character : substring.toCharArray()) {
            index = string.indexOf(character, index);
            if (index == -1){
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        try{
            int counter = 0;
            int sum = 0;
            File file = new File("Inputs\\day08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st= br.readLine())!=null){
                String[] digits = new String[10];
                String[] splitted = st.split(" \\| ");
                String[] patterns = splitted[0].split(" ");
                String[] values = splitted[1].split(" ");
                for(String value : values){ // Puzzle 1
                    if(value.length()==7||(value.length()>=2&&value.length()<=4)){
                        counter++;
                    }
                }
                for(int i =0;i<patterns.length;i++){ // Sorting chars in patterns
                    char[] chars = patterns[i].toCharArray();
                    Arrays.sort(chars);
                    patterns[i]=new String(chars);
                }
                for(int i =0;i<values.length;i++) { // Sorting chars in values
                    char[] chars = values[i].toCharArray();
                    Arrays.sort(chars);
                    values[i] = new String(chars);
                }
                ArrayList<String> patternsAL = new ArrayList<>(Arrays.asList(patterns));
                for(String pattern : patternsAL){ // Getting known values
                    if(pattern.length()==2){
                        digits[1]=pattern;
                    }else if(pattern.length()==4){
                        digits[4]=pattern;
                    }else if(pattern.length()==3){
                        digits[7]=pattern;
                    }else if(pattern.length()==7){
                        digits[8]=pattern;
                    }
                }
                patternsAL.remove(digits[1]);
                patternsAL.remove(digits[4]);
                patternsAL.remove(digits[7]);
                patternsAL.remove(digits[8]);
                for(String pattern: patternsAL){ //Getting 9 and 3 patterns
                    if(pattern.length()==6 && sub(pattern,digits[4])==0){
                        digits[9]=pattern;
                    }else if(pattern.length()==5 && sub(pattern,digits[7])==0){
                        digits[3]=pattern;
                    }
                }
                patternsAL.remove(digits[9]);
                patternsAL.remove(digits[3]);
                for(String pattern: patternsAL) { //Getting 0 and 5 patterns
                    if (pattern.length() == 6 && sub(pattern,digits[7])==0) {
                        digits[0] = pattern;
                    }else if(pattern.length()==5 && sub(pattern,digits[4])==1){
                        digits[5] = pattern;
                    }
                }
                patternsAL.remove(digits[0]);
                patternsAL.remove(digits[5]);
                for(String pattern: patternsAL) { //Getting 6 pattern
                    if (pattern.length() == 6) {
                        digits[6] = pattern;
                    }
                }
                patternsAL.remove(digits[6]);
                digits[2]=patternsAL.get(0); //Getting 2 pattern
                ArrayList<String> digitsAL = new ArrayList<>(Arrays.asList(digits));
                StringBuilder sb = new StringBuilder();
                for(String value : values){ //Building values
                    sb.append(digitsAL.indexOf(value));
                }
                sum+=Integer.parseInt(sb.toString());
            }
            System.out.println("Puzzle 1: "+counter);
            System.out.println("Puzzle 2: "+sum);

        }catch (Exception e){
            e.printStackTrace();
        }




    }
}