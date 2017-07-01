package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author istepanov
 */
public class ValidatorTest extends TestBase {

    @Test
    public void validateInput_valid() throws Exception {
        assertThat(LabEscape.drawPathForEscape(labyrinth, 1, 1)).isNotEmpty();
    }

    @Test
    public void validateInput_invalidLabyrinth() throws Exception {
        labyrinth[3][6] = '?';
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageContaining("Specified labyrinth is incorrect, unsupported character");

        labyrinth[3] = new char[]{};
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified labyrinth is incorrect, row 3 is empty");
        labyrinth[3] = null;
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified labyrinth is incorrect, row 3 is empty");

        assertThatThrownBy(() -> LabEscape.drawPathForEscape(new char[][]{{}}, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified labyrinth is incorrect, row .* is empty");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(new char[][]{null}, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified labyrinth is incorrect, row .* is empty");

        assertThatThrownBy(() -> LabEscape.drawPathForEscape(new char[][]{}, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified labyrinth is empty");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(null, 1, 1))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified labyrinth is empty");
    }

    @Test
    public void validateInput_incorrectStartingPoint() throws Exception {
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, -13, 0))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is -13, cannot be less than 0");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 0, -42))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is -42, cannot be less than 0");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 8, 0))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is 8, cannot be more than 7");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 42, 0))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is 42, cannot be more than 7");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 0, 10))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is 10, cannot be more than 9");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 0, 13))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is 13, cannot be more than 9");
    }

    @Test
    public void validateInput_startFromWall() throws Exception {
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 0, 0))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [0,0] is a wall");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 3, 0))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [3,0] is a wall");
        assertThatThrownBy(() -> LabEscape.drawPathForEscape(labyrinth, 7, 9))
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [7,9] is a wall");
    }
}
