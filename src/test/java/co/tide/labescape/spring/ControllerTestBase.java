package co.tide.labescape.spring;

import co.tide.labescape.TestBase;

import java.util.Arrays;
import java.util.List;

/**
 * @author istepanov
 */
public class ControllerTestBase extends TestBase {

    protected List<String> list;

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
    }
}
