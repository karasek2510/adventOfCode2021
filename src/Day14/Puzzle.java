package Day14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Puzzle {


    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day14.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
        Map<String, BigInteger> polymerMap = new HashMap<>();
        for (int i = 0; i < st.length() - 1; i++) {
            String pair = String.valueOf(st.charAt(i)) + st.charAt(i + 1);
            if (!polymerMap.containsKey(pair)) {
                polymerMap.put(pair, BigInteger.ZERO);
            }
            polymerMap.put(pair, polymerMap.get(pair).add(BigInteger.ONE));
        }
        br.readLine();
        HashMap<String, String[]> inserationRules = new HashMap<>();
        while ((st = br.readLine()) != null) {
            String[] splitted = st.split(" -> ");
            String result1 = splitted[0].charAt(0) + splitted[1];
            String result2 = splitted[1] + splitted[0].charAt(1);
            inserationRules.put(splitted[0], new String[]{result1, result2});
        }
        for (int j = 0; j < 40; j++) {
            Map<String, BigInteger> newPolymerMap = new HashMap<>();
            for (String pair : polymerMap.keySet()) {
                String[] work = inserationRules.get(pair);
                for (String s : work) {
                    if (!newPolymerMap.containsKey(s)) {
                        newPolymerMap.put(s, BigInteger.ZERO);
                    }
                    newPolymerMap.put(s, newPolymerMap.get(s).add(polymerMap.get(pair)));
                }
            }
            polymerMap = newPolymerMap;
        }
        Map<String, BigInteger> result = new HashMap<>();
        for (String pair : polymerMap.keySet()) {
            String s = pair.split("")[0];
            if (!result.containsKey(s)) {
                result.put(s, BigInteger.ZERO);
            }
            result.put(s, result.get(s).add(polymerMap.get(pair)));
        }
        ArrayList<BigInteger> val = new ArrayList<>(result.values());
        Collections.sort(val);
        BigInteger score = val.get(val.size() - 1).subtract(val.get(0)).add(BigInteger.ONE);
        System.out.println("Puzzle2: "+score);


    }

}