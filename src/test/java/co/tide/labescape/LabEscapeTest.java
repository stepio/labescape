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
    public void drawPathForEscape_trivialCase() throws Exception {
        char [][] copy = LabEscape.drawPathForEscape(labyrinth, 4, 9);
        assertThat(copy).isNotEqualTo(labyrinth);
        assertThat(labyrinth[4][9]).isEqualTo(LabChars.FREE.getValue());
        assertThat(copy[4][9]).isEqualTo(LabChars.PATH.getValue());
    }

    @Test
    public void drawPathForEscape_mainCase() throws Exception {
        char [][] copy = LabEscape.drawPathForEscape(labyrinth, 3, 1);
        assertThat(copy).isNotEqualTo(labyrinth);
        assertThat(copy[3][1]).isEqualTo(LabChars.PATH.getValue());
        assertThat(copy[4][9]).isEqualTo(LabChars.PATH.getValue());
        assertThat(copy[3][2]).isEqualTo(labyrinth[3][2])
                .isEqualTo(LabChars.FREE.getValue());
        assertThat(copy[4][1]).isEqualTo(labyrinth[4][1])
                .isEqualTo(LabChars.FREE.getValue());
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
