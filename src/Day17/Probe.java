package Day17;

public class Probe {
    int xVel;
    int yVel;
    int xPos = 0;
    int yPos = 0;

    public Probe(int xVel, int yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void doOneStep() {
        xPos += xVel;
        yPos += yVel;
        if (xVel != 0) {
            xVel--;
        }
        yVel--;
    }
}
