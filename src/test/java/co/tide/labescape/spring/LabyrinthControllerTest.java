package co.tide.labescape.spring;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link LabyrinthController}.
 *
 * @author istepanov
 */
public class LabyrinthControllerTest extends ControllerTestBase {

    private LabyrinthController controller;

    @Override
    public void setUp() {
        super.setUp();
        controller = new LabyrinthController();
    }

    @Test
    public void valueOf_withDummy() throws Exception {
        assertThat(controller.valueOf(null).toArray()).isNull();
        assertThat(controller.valueOf(list).toArray()).isEqualTo(labyrinth.toArray());
    }

    @Test
    public void toList_withDummy() throws Exception {
        assertThat(controller.toList(labyrinth)).isEqualTo(list);
    }
}
