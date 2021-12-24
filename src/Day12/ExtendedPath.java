package Day12;

import java.util.HashSet;

public class ExtendedPath extends Path {

    private boolean isSmallCaveVisitedTwice = false;

    public ExtendedPath(HashSet<String> caves) {
        super(caves);
    }

    public ExtendedPath(String startingCave) {
        super(startingCave);
    }

    @Override
    public boolean isCaveLegal(String cave) {
        if (cave.equals("start")) {
            return false;
        } else {
            if (cave.equals("end")) {
                return true;
            } else {
                if (cave.equals(cave.toLowerCase())) {
                    return !isSmallCaveVisitedTwice() || !getCaves().contains(cave);
                } else {
                    return true;
                }
            }
        }
    }

    @Override
    public void addCave(String cave) {
        boolean isSmall = cave.equals(cave.toLowerCase());
        if (cave.equals("end")) {
            setFinished(true);
        }
        if (isSmall && getCaves().contains(cave)) {
            setSmallCaveVisitedTwice(true);
        }
        if(isSmall){
            getCaves().add(cave);
        }
        setLastVisitedCave(cave);
    }

    public boolean isSmallCaveVisitedTwice() {
        return isSmallCaveVisitedTwice;
    }

    public void setSmallCaveVisitedTwice(boolean smallCaveVisitedTwice) {
        isSmallCaveVisitedTwice = smallCaveVisitedTwice;
    }
}
