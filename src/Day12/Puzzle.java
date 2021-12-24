package Day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Puzzle {

    public static void puzzle1(HashMap<String, Set<String>> links) {
        HashSet<Path> finishedPaths = new HashSet<>();
        HashSet<Path> paths = new HashSet<>();
        paths.add(new Path("start"));
        while (paths.size() != 0) {
            HashSet<Path> tempPaths = new HashSet<>();
            for (Path path : paths) {
                for (String nextCave : links.get(path.getLastVisitedCave())) {
                    if (path.isCaveLegal(nextCave)) {
                        Path tempPath = new Path(new HashSet<>(path.getCaves()));
                        tempPath.addCave(nextCave);
                        tempPaths.add(tempPath);
                    }
                }
            }
            paths = tempPaths;
            for (Path path : paths) {
                if (path.isFinished()) {
                    finishedPaths.add(path);
                }
            }
            paths.removeAll(finishedPaths);
        }

        System.out.println("Puzzle1: " + finishedPaths.size());
    }

    public static void puzzle2(HashMap<String, Set<String>> links) {
        long startTime = System.currentTimeMillis();
        HashSet<ExtendedPath> finishedPaths = new HashSet<>();
        HashSet<ExtendedPath> currentPaths = new HashSet<>();
        currentPaths.add(new ExtendedPath("start"));
        while (currentPaths.size() != 0) {
            HashSet<ExtendedPath> temporaryPaths = new HashSet<>();
            for (ExtendedPath path : currentPaths) {
                for (String cave : links.get(path.getLastVisitedCave())) {
                    if (path.isCaveLegal(cave)) {
                        ExtendedPath tempPath = new ExtendedPath(new HashSet<>(path.getCaves()));
                        tempPath.setSmallCaveVisitedTwice(path.isSmallCaveVisitedTwice());
                        tempPath.addCave(cave);
                        temporaryPaths.add(tempPath);
                    }
                }
            }
            currentPaths = temporaryPaths;
            for (ExtendedPath path : currentPaths) {
                if (path.isFinished()) {
                    finishedPaths.add(path);
                }
            }
            currentPaths.removeAll(finishedPaths);
        }
        System.out.println("Puzzle2: " + finishedPaths.size());
        System.out.println("Time: " + (double) (System.currentTimeMillis() - startTime) / 1000);
    }


    public static void main(String[] args) throws IOException {
        File file = new File("Inputs\\day12.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        HashMap<String, Set<String>> links = new HashMap<>();
        while ((st = br.readLine()) != null) {
            String[] caves = st.split("-");
            if (!links.containsKey(caves[0])) {
                links.put(caves[0], new HashSet<>());
            }
            if (!links.containsKey(caves[1])) {
                links.put(caves[1], new HashSet<>());
            }
            links.get(caves[0]).add(caves[1]);
            links.get(caves[1]).add(caves[0]);
        }
        puzzle1(links);
        puzzle2(links);


    }
}
