package co.tide.labescape;

/**
 * @author istepanov
 */
public class Validator {

    public void validateInput(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        validateLabyrinth(labyrinth);
        validateStartingPoint(labyrinth, startX, startY);
        checkStartingPointNotWall(labyrinth, startX, startY);
    }

    void validateLabyrinth(char[][] labyrinth) throws NoEscapeException {
        if (labyrinth == null || labyrinth.length == 0) {
            throw new NoEscapeException("Specified labyrinth is empty");
        }
        for (int i = 0; i < labyrinth.length; ++i) {
            if (labyrinth[i] == null || labyrinth[i].length == 0) {
                throw new NoEscapeException("Specified labyrinth is incorrect, row " + i + " is empty");
            }
            for (int j = 0; j < labyrinth[i].length; ++j) {
                if (labyrinth[i][j] != LabChars.WALL.getValue() && labyrinth[i][j] != LabChars.FREE.getValue()) {
                    throw new NoEscapeException("Specified labyrinth is incorrect, unsupported character " + labyrinth[i][j] + " at [" + i + "," + j + "]");
                }
            }
        }
    }

    void validateStartingPoint(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        if (startX < 0) {
            throw new NoEscapeException("Specified 'startX' is " + startX + ", cannot be less than 0");
        }
        if (startY < 0) {
            throw new NoEscapeException("Specified 'startY' is " + startY + ", cannot be less than 0");
        }
        if (startX >= labyrinth.length) {
            throw new NoEscapeException("Specified 'startX' is " + startX + ", cannot be more than " + (labyrinth.length - 1));
        }
        if (startY >= labyrinth[startX].length) {
            throw new NoEscapeException("Specified 'startY' is " + startY + ", cannot be more than " + (labyrinth[startX].length - 1));
        }
    }

    void checkStartingPointNotWall(char[][] labyrinth, int startX, int startY) throws NoEscapeException {
        if (labyrinth[startX][startY] == LabChars.WALL.getValue()) {
            throw new NoEscapeException("Specified starting point at [" + startX + "," + startY + "] is a wall");
        }
    }
}
