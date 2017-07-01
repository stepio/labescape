package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author istepanov
 */
public class PathFinderTest extends TestBase {

    private PathFinder pathFinder;

    @Override
    public void setUp() {
        super.setUp();
        pathFinder = new PathFinder();
    }

    @Test
    public void isExit_isTrue() throws Exception {
        // simple case
        assertThat(pathFinder.isExit(labyrinth, 4, 9)).isTrue();

        // additional possible cases
        assertThat(pathFinder.isExit(labyrinth, 0, 1)).isFalse();
        labyrinth[0][1] = LabChars.FREE.getValue();
        assertThat(pathFinder.isExit(labyrinth, 0, 1)).isTrue();

        assertThat(pathFinder.isExit(labyrinth, 1, 0)).isFalse();
        labyrinth[1][0] = LabChars.FREE.getValue();
        assertThat(pathFinder.isExit(labyrinth, 1, 0)).isTrue();
    }

    @Test
    public void isExit_isFalse() throws Exception {
        assertThat(pathFinder.isExit(labyrinth, 0, 0)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 0, 9)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 7, 0)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 7, 9)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 1, 1)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 3, 5)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 5, 5)).isFalse();
        assertThat(pathFinder.isExit(labyrinth, 5, 3)).isFalse();
    }
}
