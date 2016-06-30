package lesson_4;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author parsentev
 * @since 14.06.2016
 */
public class InteractRunnerTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayInputStream input = new ByteArrayInputStream(
            "1\n1\nn\nn\n".getBytes()
    );

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(out));
        System.setIn(input);
    }

    @Test
    public void whenTakePlusShouldSummate() {
        MockIO mock = new MockIO(new String[] {"1", "+", "1", "n", "n"});
        new InteractRunner(
                new Calculator(),
                mock
        ).start();
        assertThat(mock.getOut().split("\n")[2], is("1.0 + 1.0 = 2.0"));
    }
}