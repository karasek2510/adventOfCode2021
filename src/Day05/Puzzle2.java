package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle2 {

    public static int[] getx1y1x2y2(String str){
        str = str.replaceAll("\\s+","");
        String[] splitted = str.split("->");
        String[] temp1 = splitted[0].split(",");
        String[] temp2 = splitted[1].split(",");
        int x1 = Integer.parseInt(temp1[0]);
        int y1 = Integer.parseInt(temp1[1]);
        int x2 = Integer.parseInt(temp2[0]);
        int y2 = Integer.parseInt(temp2[1]);
        return new int[]{x1,y1,x2,y2};
    }

    public static void main(String[] args) {
        try {
            File file = new File("Inputs\\day05.txt");
            BufferedReader tempBr = new BufferedReader(new FileReader(file));
            String st;
            int maxX = 0;
            int maxY = 0;
            while ((st= tempBr.readLine()) != null) {
                int[] x1y1x2y2 = getx1y1x2y2(st);
                int x1 = x1y1x2y2[0];
                int y1 = x1y1x2y2[1];
                int x2 = x1y1x2y2[2];
                int y2 = x1y1x2y2[3];
                if(x1>maxX) maxX=x1;
                if(x2>maxX) maxX=x2;
                if(y1>maxY) maxY=y1;
                if(y2>maxY) maxY=y2;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            int[][] coordinates = new int[maxY+1][maxX+1];
            while ((st= br.readLine()) != null) {
                if (st.isBlank()) {
                    continue;
                }
                int[] x1y1x2y2 = getx1y1x2y2(st);
                int x1 = x1y1x2y2[0];
                int y1 = x1y1x2y2[1];
                int x2 = x1y1x2y2[2];
                int y2 = x1y1x2y2[3];

                if(x1==x2){
                    if(y1>y2){
                        for(int i = y2;i<=y1;i++){
                            coordinates[i][x1]+=1;
                        }
                    }else{
                        for(int i = y1;i<=y2;i++){
                            coordinates[i][x1]+=1;

                        }
                    }
                }else if(y1==y2){
                    if(x1>x2){
                        for(int i = x2;i<=x1;i++){
                            coordinates[y1][i]+=1;
                        }
                    }else{
                        for(int i = x1;i<=x2;i++){
                            coordinates[y1][i]+=1;

                        }
                    }
                }else if(x1-x2==y1-y2){
                    if(x1>x2){
                        for(int i=0;x2+i<=x1;i++){
                            coordinates[y2+i][x2+i]+=1;
                        }
                    }else{
                        for(int i=0;x1+i<=x2;i++){
                            coordinates[y1+i][x1+i]+=1;
                        }
                    }
                }else if(x1-x2==y2-y1){
                    if(x1>x2){
                        for(int i = 0; x2+i<=x1;i++){
                            coordinates[y2-i][x2+i]+=1;
                        }
                    }else{
                        for(int i = 0; x1+i<=x2;i++){
                            coordinates[y1-i][x1+i]+=1;
                        }
                    }
                }
            }
            int counter = 0;
            for(int[] row : coordinates){
                for(int element: row){
                    if(element>=2){
                        counter++;
                    }
                }
            }
            System.out.println(counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
