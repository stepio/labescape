package co.tide.labescape;

/**
 * @author istepanov
 */
public class PathFinder {

    public boolean isExit(char[][] labyrinth, int nowX, int nowY) {
        if (nowX == 0 || nowX == labyrinth.length - 1 || nowY == 0 || nowY == labyrinth[nowX].length - 1) {
            if (labyrinth[nowX][nowY] == LabChars.FREE.getValue()) {
                return true;
            }
        }
        return false;
    }
}
