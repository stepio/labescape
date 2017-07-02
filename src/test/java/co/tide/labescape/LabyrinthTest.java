package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link Labyrinth}.
 *
 * @author istepanov
 */
public class LabyrinthTest extends TestBase {

    @Test
    public void copy_checkEqualsNotSame() throws Exception {
        char[][] array = labyrinth.toArray();
        Labyrinth copy = Labyrinth.valueOf(array);
        assertThat(copy.toArray()).isEqualTo(array).isNotSameAs(array);
        for (int i = 0; i < labyrinth.toArray().length; ++i) {
            assertThat(copy.toArray()[i]).isNotSameAs(array[i]);
        }
    }

    @Test
    public void validate_valid() throws Exception {
        assertThat(labyrinth.validate()).isTrue();
    }

    @Test
    public void validate_invalidLabyrinth() throws Exception {
        labyrinth.toArray()[3][6] = '?';
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessageContaining("Specified cellArray is incorrect, unsupported character");

        labyrinth.toArray()[3] = new char[]{};
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified cellArray is incorrect, row 3 is empty");
        labyrinth.toArray()[3] = null;
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified cellArray is incorrect, row 3 is empty");

        labyrinth = Labyrinth.valueOf(new char[][]{{}});
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified cellArray is incorrect, row .* is empty");
        labyrinth = Labyrinth.valueOf(new char[][]{null});
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessageMatching("Specified cellArray is incorrect, row .* is empty");

        labyrinth = Labyrinth.valueOf(new char[][]{});
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified cellArray is empty");
        labyrinth = Labyrinth.valueOf(null);
        assertThatThrownBy(labyrinth::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified cellArray is empty");
    }
}
