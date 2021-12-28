package Day17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Puzzle {

    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day17.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
        String[] splitted = st.split(":");
        splitted = splitted[1].split(",");
        String[] xRange = splitted[0].strip().substring(2).split("\\.\\.");
        String[] yRange = splitted[1].strip().substring(2).split("\\.\\.");
        int xTargetLeft = Integer.parseInt(xRange[0]);
        int xTargetRight = Integer.parseInt(xRange[1]);
        int yTargetUp = Integer.parseInt(yRange[1]);
        int yTargetDown = Integer.parseInt(yRange[0]);
        int highestPos = 0;
        int numberOfPossibleVelocities = 0;
        for (int x = 0; x < xTargetRight * 10; x++) {
            for (int y = -10 * Math.abs(yTargetDown); y < 10 * Math.abs(yTargetUp); y++) {
                int tempHighestPos;
                boolean hit = false;
                tempHighestPos = 0;
                Probe probe = new Probe(x, y);
                while (probe.xPos <= xTargetRight && probe.yPos >= yTargetDown) {
                    probe.doOneStep();
                    if (probe.yPos > tempHighestPos) {
                        tempHighestPos = probe.yPos;
                    }
                    if (probe.xPos >= xTargetLeft && probe.xPos <= xTargetRight && probe.yPos <= yTargetUp && probe.yPos >= yTargetDown) {
                        hit = true;
                        numberOfPossibleVelocities++;
                        break;
                    }
                }
                if (hit && tempHighestPos > highestPos) {
                    highestPos = tempHighestPos;
                }
            }
        }
        System.out.println("Puzzle1: "+highestPos);
        System.out.println("Puzzle2: "+numberOfPossibleVelocities);


    }

}
