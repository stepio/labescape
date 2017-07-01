package co.tide.labescape;

/**
 * Please implement your solution here
 */
public class LabEscape {

    private Validator validator;
    private PathFinder pathFinder;

    public LabEscape() {
        this.validator = new Validator();
        this.pathFinder = new PathFinder();
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
        if (this.pathFinder.isExit(labyrinth, startX, startY)) {
            this.pathFinder.select(result, startX, startY);
        } else {
            // TODO
        }
        return result;
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
