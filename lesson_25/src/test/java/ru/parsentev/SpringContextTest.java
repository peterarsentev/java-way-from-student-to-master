package ru.parsentev;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 20.06.2016
 */
public class SpringContextTest {
    @Test
    public void whenUserTriangeDepsShouldDrawTrangle() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Sheet sheet = context.getBean(Sheet.class);
        sheet.doDraw();
        assertThat(new String(out.toByteArray()), is("draw triangle\r\n"));
    }
}