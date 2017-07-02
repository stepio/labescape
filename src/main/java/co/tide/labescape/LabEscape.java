package co.tide.labescape;

/**
 * Please implement your solution here
 */
public class LabEscape {

    private Validator validator;
    private PathTools pathTools;

    public LabEscape() {
        this.validator = new Validator();
        this.pathTools = new PathTools();
    }

    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return          A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws          NoEscapeException when no path exists to the outside, from the selected starting point
     */
    @Deprecated
    public static char[][] drawPathForEscape(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        final LabEscape labEscape = new LabEscape();
        return labEscape.drawPath(labyrinth, startX, startY);
    }

    public char[][] drawPath(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        this.validator.validateInput(labyrinth, startX, startY);
        char[][] result = clone(labyrinth);
        if (this.pathTools.isExit(result, startX, startY)) {
            this.pathTools.markPath(result, startX, startY);
        } else {
            if (!traverseRecursively(result, startX, startY)) {
                throw new NoEscapeException("Specified starting point at [" + startX + "," + startY + "] has no access to exit");
            }
            clearFail(result);
        }
        return result;
    }

    boolean traverseRecursively(char[][] labyrinth, int nowX, int nowY) {
        if (this.pathTools.isOutside(labyrinth, nowX, nowY)) {
            return false;
        }
        if (this.pathTools.isNotBlank(labyrinth, nowX, nowY)) {
            return false;
        }

        this.pathTools.markPath(labyrinth, nowX, nowY);
        if (this.pathTools.isExit(labyrinth, nowX, nowY)) {
            return true;
        }

        if (traverseRecursively(labyrinth, nowX + 1, nowY) || traverseRecursively(labyrinth, nowX, nowY + 1) ||
                traverseRecursively(labyrinth, nowX - 1, nowY) || traverseRecursively(labyrinth, nowX, nowY - 1)) {
            return true;
        } else {
            this.pathTools.markFail(labyrinth, nowX, nowY);
            return false;
        }
    }

    void clearFail(char[][] labyrinth) {
        for (int i = 0; i < labyrinth.length; ++i) {
            for (int j = 0; j < labyrinth[i].length; ++j) {
                if (this.pathTools.isFail(labyrinth, i, j)) {
                    this.pathTools.markFree(labyrinth, i, j);
                }
            }
        }
    }

    char[][] clone(char[][] labyrinth) {
        if (labyrinth == null) {
            return null;
        }
        char[][] result = new char[labyrinth.length][];
        for (int r = 0; r < labyrinth.length; r++) {
            result[r] = labyrinth[r].clone();
        }
        return result;
    }
}
