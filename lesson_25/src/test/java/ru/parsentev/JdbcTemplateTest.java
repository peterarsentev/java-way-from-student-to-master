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
public class JdbcTemplateTest {
    @Test
    public void whenUserTriangeDepsShouldDrawTrangle() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-context.xml");
        UserRepository repository = context.getBean(UserRepository.class);
        User user = repository.save(new User("petr"));
        assertThat(repository.getAll().contains(user), is(true));
    }
}