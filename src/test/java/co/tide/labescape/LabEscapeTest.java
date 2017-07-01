package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author istepanov
 */
public class LabEscapeTest {

    @Test
    public void clone_checkEqualsNotSame() throws Exception {
        char [][] input = {
                {'O', 'O', 'O', 'O'},
                {'O', ' ', ' ', 'O'},
                {'O', ' ', ' ', ' '},
                {'O', 'O', 'O', 'O'}
        };
        LabEscape labEscape = new LabEscape();
        char [][] copy = labEscape.clone(input);
        assertThat(copy).isEqualTo(input).isNotSameAs(input);
        for (int i = 0; i < input.length; ++i) {
            assertThat(copy[i]).isNotSameAs(input[i]);
        }
    }
}
