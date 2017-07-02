package co.tide.labescape;

/**
 * A single area in a labyrinth - either wall, or walkable.
 *
 * @author istepanov
 */
public class Area {

    protected Labyrinth labyrinth;
    protected int nowX;
    protected int nowY;

    private Area(Labyrinth labyrinth, int nowX, int nowY) {
        this.labyrinth = labyrinth;
        this.nowX = nowX;
        this.nowY = nowY;
    }

    public int getNowX() {
        return nowX;
    }

    public int getNowY() {
        return nowY;
    }

    /**
     * Construct {@link Area} instance from the given labyrinth and coordinates.
     *
     * @param labyrinth current labyrinth
     * @param nowX number of the row in a labyrinth
     * @param nowY number of the column in a labyrinth
     * @return constructed instance
     */
    public static Area valueOf(Labyrinth labyrinth, int nowX, int nowY) {
        return new Area(labyrinth, nowX, nowY);
    }

    /**
     * Get next area on the bottom.
     */
    public Area nextDown() {
        return valueOf(labyrinth, nowX + 1, nowY);
    }

    /**
     * Get next area on the top.
     */
    public Area nextUp() {
        return valueOf(labyrinth, nowX - 1, nowY);
    }

    /**
     * Get next area on the right.
     */
    public Area nextRight() {
        return valueOf(labyrinth, nowX, nowY + 1);
    }

    /**
     * Get next area on the left.
     */
    public Area nextLeft() {
        return valueOf(labyrinth, nowX, nowY - 1);
    }

    /**
     * Check if current area has specified status.
     */
    public boolean hasStatus(final AreaStatus status) {
        return labyrinth.cellArray[nowX][nowY] == status.getValue();
    }

    /**
     * Mark current area with specified status.
     */
    public void setStatus(final AreaStatus status) {
        labyrinth.cellArray[nowX][nowY] = status.getValue();
    }

    /**
     * Check if current area is inside the labyrinth and walkable.
     */
    public boolean isWalkable() {
        return nowX >= 0 && nowX <= labyrinth.toArray().length - 1
                && nowY >= 0 && nowY <= labyrinth.toArray()[nowX].length - 1
                && hasStatus(AreaStatus.FREE);
    }

    /**
     * Check if current area is an exit from the labyrinth.
     */
    public boolean isExit() {
        if (nowX == 0 || nowX == labyrinth.toArray().length - 1
                || nowY == 0 || nowY == labyrinth.toArray()[nowX].length - 1) {
            if (hasStatus(AreaStatus.PATH)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if current initial step matches the preconditions, which are given in the task.
     * Also checks the cases, which may break the calculation.
     *
     * @return true if current initial step is valid
     * @throws NoEscapeException if some of the preconditions is violated
     */
    public boolean validate() throws NoEscapeException {
        if (nowX < 0) {
            throw new NoEscapeException("Specified 'startX' is " + nowX + ", cannot be less than 0");
        }
        if (nowY < 0) {
            throw new NoEscapeException("Specified 'startY' is " + nowY + ", cannot be less than 0");
        }
        if (nowX >= labyrinth.toArray().length) {
            throw new NoEscapeException("Specified 'startX' is " + nowX + ", cannot be more than " + (labyrinth.toArray().length - 1));
        }
        if (nowY >= labyrinth.toArray()[nowX].length) {
            throw new NoEscapeException("Specified 'startY' is " + nowY + ", cannot be more than " + (labyrinth.toArray()[nowX].length - 1));
        }
        if (labyrinth.toArray()[nowX][nowY] == AreaStatus.WALL.getValue()) {
            throw new NoEscapeException("Specified starting point at [" + nowX + "," + nowY + "] is a wall");
        }
        return true;
    }

    /**
     * Recurrent method to find the exit from labyrinth and draw a path to it.
     *
     * @return true if exit is found, or false if it's unreachable
     */
    public boolean canProceed() {
        if (!isWalkable()) {
            return false;
        }

        setStatus(AreaStatus.PATH);
        if (isExit()) {
            return true;
        }

        if (nextRight().canProceed() || nextDown().canProceed() || nextLeft().canProceed() || nextUp().canProceed()) {
            return true;
        } else {
            setStatus(AreaStatus.FAIL);
            return false;
        }
    }
}
