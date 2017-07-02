package co.tide.labescape;

/**
 * Wrapper for two-dimensional array with labyrinth.
 *
 * @author istepanov
 */
public class Labyrinth {

    protected char[][] cellArray;

    private Labyrinth(final char[][] cellArray) {
        this.cellArray = cellArray;
    }

    /**
     * Construct {@link Labyrinth} instance from the given 2-dimensional array.
     *
     * @param labyrinth 2-dimensional array of chars
     * @return constructed instance
     */
    public static Labyrinth valueOf(final char[][] labyrinth) {
        char[][] copy = copy(labyrinth);
        return new Labyrinth(copy);
    }

    /**
     * Create a copy of the current array to prevent unexpected modifications of source data.
     *
     * @param labyrinth 2-dimensional array of chars
     * @return new 2-dimensional array of chars
     */
    static char[][] copy(char[][] labyrinth) {
        if (labyrinth == null) {
            return null;
        }
        char[][] result = new char[labyrinth.length][];
        for (int i = 0; i < labyrinth.length; ++i) {
            if (labyrinth[i] == null) {
                result[i] = null;
            } else {
                result[i] = labyrinth[i].clone();
            }
        }
        return result;
    }

    /**
     * Replace "technical" status "FAIL" with initial status "FREE".
     */
    void clearFail() {
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                Area step = Area.valueOf(this, i, j);
                if (step.hasStatus(AreaStatus.FAIL)) {
                    step.setStatus(AreaStatus.FREE);
                }
            }
        }
    }

    /**
     * Check if current labyrinth matches the preconditions, which are given in the task.
     *
     * @return true if current labyrinth is valid
     * @throws NoEscapeException if some of the preconditions is violated
     */
    public boolean validate() throws NoEscapeException {
        if (cellArray == null || cellArray.length == 0) {
            throw new NoEscapeException("Specified cellArray is empty");
        }
        for (int i = 0; i < cellArray.length; ++i) {
            if (cellArray[i] == null || cellArray[i].length == 0) {
                throw new NoEscapeException("Specified cellArray is incorrect, row " + i + " is empty");
            }
            for (int j = 0; j < cellArray[i].length; ++j) {
                if (cellArray[i][j] != AreaStatus.WALL.getValue() && cellArray[i][j] != AreaStatus.FREE.getValue()) {
                    throw new NoEscapeException("Specified cellArray is incorrect, unsupported character " + cellArray[i][j] + " at [" + i + "," + j + "]");
                }
            }
        }
        return true;
    }

    /**
     * Convert current object into a 2-dimensional array to match the predefined interface.
     *
     * @return 2-dimensional array of chars
     */
    public char[][] toArray() {
        return this.cellArray;
    }

    /**
     * Give an area of the current labyrinth, matching the given coordinates.
     *
     * @param nowX current row
     * @param nowY current column
     * @return representation of the current step
     */
    public Area getArea(final int nowX, final int nowY) {
        return Area.valueOf(this, nowX, nowY);
    }

    /**
     * Entry point for drawing path to escape from labyrinth.
     *
     * @param initialArea initial point to start escaping from labyrinth
     * @throws NoEscapeException if no escape from the labyrinth with given initial point
     */
    public void drawPathFrom(final Area initialArea) throws NoEscapeException {
        if (!initialArea.canProceed()) {
            throw new NoEscapeException("Specified starting point at [" + initialArea.getNowX() + "," + initialArea.getNowY() + "] has no access to exit");
        }
        clearFail();
    }
}
