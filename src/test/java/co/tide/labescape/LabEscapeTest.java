package co.tide.labescape;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author istepanov
 */
public class LabEscapeTest extends TestBase {

    private LabEscape labEscape;

    @Override
    public void setUp() {
        super.setUp();
        labEscape = new LabEscape();
    }

    @Test
    public void clone_checkEqualsNotSame() throws Exception {
        char [][] copy = labEscape.clone(labyrinth);
        assertThat(copy).isEqualTo(labyrinth).isNotSameAs(labyrinth);
        for (int i = 0; i < labyrinth.length; ++i) {
            assertThat(copy[i]).isNotSameAs(labyrinth[i]);
        }
    }
}
