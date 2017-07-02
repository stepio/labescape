package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link Area}.
 *
 * @author istepanov
 */
public class AreaTest extends TestBase {

    @Test
    public void isExit_isTrue() throws Exception {
        Area step = labyrinth.getArea(4, 9);
        step.setStatus(AreaStatus.PATH);
        assertThat(step.isExit()).isTrue();

        // additional possible cases
        step = labyrinth.getArea(0, 1);
        step.setStatus(AreaStatus.PATH);
        assertThat(step.isExit()).isTrue();

        step = labyrinth.getArea(1, 0);
        step.setStatus(AreaStatus.PATH);
        assertThat(step.isExit()).isTrue();
    }

    @Test
    public void isExit_isFalse() throws Exception {
        assertThat(labyrinth.getArea(0, 0).isExit()).isFalse();
        assertThat(labyrinth.getArea(0, 9).isExit()).isFalse();
        assertThat(labyrinth.getArea(7, 0).isExit()).isFalse();
        assertThat(labyrinth.getArea(7, 9).isExit()).isFalse();
        assertThat(labyrinth.getArea(1, 1).isExit()).isFalse();
        assertThat(labyrinth.getArea(3, 5).isExit()).isFalse();
        assertThat(labyrinth.getArea(5, 5).isExit()).isFalse();
        assertThat(labyrinth.getArea(5, 3).isExit()).isFalse();
    }

    @Test
    public void validate_incorrectStartingPoint() throws Exception {
        assertThatThrownBy(labyrinth.getArea(-13, 0)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is -13, cannot be less than 0");
        assertThatThrownBy(labyrinth.getArea(0, -42)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is -42, cannot be less than 0");
        assertThatThrownBy(labyrinth.getArea(8, 0)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is 8, cannot be more than 7");
        assertThatThrownBy(labyrinth.getArea(42, 0)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startX' is 42, cannot be more than 7");
        assertThatThrownBy(labyrinth.getArea(0, 10)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is 10, cannot be more than 9");
        assertThatThrownBy(labyrinth.getArea(0, 13)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified 'startY' is 13, cannot be more than 9");
    }

    @Test
    public void validate_startFromWall() throws Exception {

        assertThatThrownBy(labyrinth.getArea(0, 0)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [0,0] is a wall");
        assertThatThrownBy(labyrinth.getArea(3, 0)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [3,0] is a wall");
        assertThatThrownBy(labyrinth.getArea(7, 9)::validate)
                .isInstanceOf(NoEscapeException.class)
                .hasMessage("Specified starting point at [7,9] is a wall");
    }
}
