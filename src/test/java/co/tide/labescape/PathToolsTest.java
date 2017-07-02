package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author istepanov
 */
public class PathToolsTest extends TestBase {

    private PathTools pathTools;

    @Override
    public void setUp() {
        super.setUp();
        pathTools = new PathTools();
    }

    @Test
    public void isExit_isTrue() throws Exception {
        // simple case
        assertThat(pathTools.isExit(labyrinth, 4, 9)).isTrue();

        // additional possible cases
        assertThat(pathTools.isExit(labyrinth, 0, 1)).isFalse();
        labyrinth[0][1] = LabChars.FREE.getValue();
        assertThat(pathTools.isExit(labyrinth, 0, 1)).isTrue();

        assertThat(pathTools.isExit(labyrinth, 1, 0)).isFalse();
        labyrinth[1][0] = LabChars.FREE.getValue();
        assertThat(pathTools.isExit(labyrinth, 1, 0)).isTrue();
    }

    @Test
    public void isExit_isFalse() throws Exception {
        assertThat(pathTools.isExit(labyrinth, 0, 0)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 0, 9)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 7, 0)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 7, 9)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 1, 1)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 3, 5)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 5, 5)).isFalse();
        assertThat(pathTools.isExit(labyrinth, 5, 3)).isFalse();
    }
}
