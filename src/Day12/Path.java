package Day12;

import java.util.HashSet;

public class Path {

    private final HashSet<String> caves;
    private boolean isFinished = false;
    private String lastVisitedCave = null;
    public Path(HashSet<String> caves) {
        this.caves = caves;
    }
    public Path(String startingCave) {
        caves = new HashSet<>();
        caves.add(startingCave);
        lastVisitedCave = startingCave;
    }

    public boolean isCaveLegal(String cave) {
        return !(cave.toLowerCase().equals(cave) && caves.contains(cave));
    }

    public void addCave(String cave) {
        boolean isSmall = cave.equals(cave.toLowerCase());
        if (cave.equals("end")) {
            isFinished = true;
        }
        if(isSmall){
            caves.add(cave);
        }
        lastVisitedCave = cave;
    }

    public HashSet<String> getCaves() {
        return caves;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getLastVisitedCave() {
        return lastVisitedCave;
    }

    public void setLastVisitedCave(String lastVisitedCave) {
        this.lastVisitedCave = lastVisitedCave;
    }

}
