package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AreaStatus}.
 *
 * @author istepanov
 */
public class AreaStatusTest {

    @Test
    public void getValue_hasCorrectChar() {
        assertThat(AreaStatus.WALL.getValue()).isEqualTo('O');
        assertThat(AreaStatus.FREE.getValue()).isEqualTo(' ');
        assertThat(AreaStatus.PATH.getValue()).isEqualTo('•');
        assertThat(AreaStatus.FAIL.getValue()).isEqualTo('X');
    }
}
