package co.tide.labescape;

import org.junit.Before;

/**
 * @author istepanov
 */
public class TestBase {

    protected char labyrinth[][];

    @Before
    public void setUp() {
        labyrinth = new char[][]{
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'O'},
                {'O', ' ', 'O', 'O', ' ', 'O', ' ', 'O', ' ', 'O'},
                {'O', ' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ', 'O'},
                {'O', ' ', 'O', 'O', ' ', ' ', ' ', 'O', ' ', ' '},
                {'O', ' ', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
    }
}
