package co.tide.labescape;

/**
 * Possible statuses of a single area (point, position, cell) in labyrinth.
 *
 * @author istepanov
 */
public enum AreaStatus {

    /**
     * A wall, not walkable.
     */
    WALL('O'),

    /**
     * Empty area, walkable.
     */
    FREE(' '),

    /**
     * Part of the pass to escape.
     */
    PATH('•'),

    /**
     * A walkable area, which does not lead to escape (already checked) - technical status.
     */
    FAIL('X');

    private char value;

    AreaStatus(final char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
