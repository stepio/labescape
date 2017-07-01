package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author istepanov
 */
public class LabCharsTest {

    @Test
    public void getValue_hasCorrectChar() {
        assertThat(LabChars.WALL.getValue()).isEqualTo('O');
        assertThat(LabChars.FREE.getValue()).isEqualTo(' ');
        assertThat(LabChars.PATH.getValue()).isEqualTo('•');
    }
}
