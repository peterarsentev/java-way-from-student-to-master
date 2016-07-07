package ru.parsentev;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 20.06.2016
 */
@Ignore
public class HibernateTemplateTest {
    @Test
    public void whenUserTriangeDepsShouldDrawTrangle() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-context.xml");
        HibernateRepository repository = context.getBean(HibernateRepository.class);
        User user = repository.save(new User("petr"));
        assertThat(repository.getAll().contains(user), is(true));
    }
}