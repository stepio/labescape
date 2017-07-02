package co.tide.labescape;

import org.junit.Before;

/**
 * Basic common superclass for tests.
 *
 * @author istepanov
 */
public class TestBase {

    protected Labyrinth labyrinth;

    @Before
    public void setUp() {
        char[][] array = new char[][]{
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'},
                {'O', ' ', 'O', 'O', ' ', 'O', ' ', 'O', ' ', 'O'},
                {'O', ' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ', 'O'},
                {'O', ' ', 'O', 'O', ' ', ' ', ' ', 'O', ' ', ' '},
                {'O', ' ', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        labyrinth = Labyrinth.valueOf(array);
    }
}
