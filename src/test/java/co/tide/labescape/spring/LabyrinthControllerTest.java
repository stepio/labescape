package co.tide.labescape.spring;

import co.tide.labescape.TestBase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link LabyrinthController}.
 *
 * @author istepanov
 */
public class LabyrinthControllerTest extends TestBase{

    private LabyrinthController controller;
    private List<String> list;

    @Override
    public void setUp() {
        super.setUp();
        list = Arrays.asList(
                "OOOOOOOOOO",
                "O    O   O",
                "O OO O O O",
                "O  O O O O",
                "O OO   O  ",
                "O OOOOOOOO",
                "O        O",
                "OOOOOOOOOO");
        controller = new LabyrinthController();
    }

    @Test
    public void valueOf_withDummy() throws Exception {
        assertThat(controller.valueOf(list).toArray()).isEqualTo(labyrinth.toArray());
    }

    @Test
    public void toList_withDummy() throws Exception {
        assertThat(controller.toList(labyrinth)).isEqualTo(list);
    }
}
