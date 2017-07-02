package co.tide.labescape;

/**
 * @author istepanov
 */
public class PathTools {

    public boolean isBorderOfLine(char[][] labyrinth, int nowX) {
        return nowX == 0 || nowX == labyrinth.length - 1;
    }

    public boolean isBorderOfColumn(char[][] labyrinth, int nowX, int nowY) {
        return nowY == 0 || nowY == labyrinth[nowX].length - 1;
    }

    public boolean isInside(char[][] labyrinth, int nowX, int nowY) {
        return nowX >= 0 && nowX <= labyrinth.length - 1
                && nowY >= 0 && nowY <= labyrinth[nowX].length - 1;
    }

    public boolean isOutside(char[][] labyrinth, int nowX, int nowY) {
        return !isInside(labyrinth, nowX, nowY);
    }

    public boolean isBlank(char[][] labyrinth, int nowX, int nowY) {
        return labyrinth[nowX][nowY] == LabChars.FREE.getValue();
    }

    public boolean isNotBlank(char[][] labyrinth, int nowX, int nowY) {
        return !isBlank(labyrinth, nowX, nowY);
    }

    public boolean isFail(char[][] labyrinth, int nowX, int nowY) {
        return labyrinth[nowX][nowY] == LabChars.FAIL.getValue();
    }

    public boolean isExit(char[][] labyrinth, int nowX, int nowY) {
        if (isBorderOfLine(labyrinth, nowX) || isBorderOfColumn(labyrinth, nowX, nowY)) {
            if (labyrinth[nowX][nowY] == LabChars.FREE.getValue()
                    || labyrinth[nowX][nowY] == LabChars.PATH.getValue()) {
                return true;
            }
        }
        return false;
    }

    public void markFree(char[][] labyrinth, int nowX, int nowY) {
        labyrinth[nowX][nowY] = LabChars.FREE.getValue();
    }

    public void markFail(char[][] labyrinth, int nowX, int nowY) {
        labyrinth[nowX][nowY] = LabChars.FAIL.getValue();
    }

    public void markPath(char[][] labyrinth, int nowX, int nowY) {
        labyrinth[nowX][nowY] = LabChars.PATH.getValue();
    }
}
