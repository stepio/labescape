package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link LabEscape}.
 *
 * @author istepanov
 */
public class LabEscapeTest extends TestBase {

    @Test
    public void drawPathForEscape_trivialCase() throws Exception {
        char[][] array = labyrinth.toArray();
        char[][] copy = LabEscape.drawPathForEscape(array, 4, 9);
        assertThat(copy).isNotEqualTo(labyrinth);
        assertThat(array[4][9]).isEqualTo(AreaStatus.FREE.getValue());
        assertThat(copy[4][9]).isEqualTo(AreaStatus.PATH.getValue());
    }

    @Test
    public void drawPathForEscape_mainCase() throws Exception {
        char[][] array = labyrinth.toArray();
        char[][] copy = LabEscape.drawPathForEscape(array, 3, 1);
        assertThat(copy).isNotEqualTo(labyrinth);
        assertThat(copy[3][1]).isEqualTo(AreaStatus.PATH.getValue());
        assertThat(copy[4][9]).isEqualTo(AreaStatus.PATH.getValue());
        assertThat(copy[3][2]).isEqualTo(array[3][2])
                .isEqualTo(AreaStatus.FREE.getValue());
        assertThat(copy[4][1]).isEqualTo(array[4][1])
                .isEqualTo(AreaStatus.FREE.getValue());
    }

    @Test
    public void drawPathForEscape_noEscape() throws Exception {
        labyrinth.getArea(2, 1).setStatus(AreaStatus.WALL);
        char[][] array = labyrinth.toArray();
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(array, 3, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageContaining("Specified starting point at [3,1] has no access to exit");
    }

    @Test
    public void new_notNull() throws Exception {
        assertThat(new LabEscape()).isNotNull();
    }
}
