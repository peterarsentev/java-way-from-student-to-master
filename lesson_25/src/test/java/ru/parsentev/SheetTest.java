package ru.parsentev;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 20.06.2016
 */
@Ignore
public class SheetTest {
    @Test
    public void whenUserTriangeDepsShouldDrawTrangle() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Sheet sheet = new Sheet(new Triange());
        sheet.doDraw();
        assertThat(new String(out.toByteArray()), is("draw triangle\r\n"));
    }
}