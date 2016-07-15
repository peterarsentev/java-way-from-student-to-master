package ru.parsentev.storages;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public abstract class DbInit {
    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    @Before
    public void initDb() {
        try (Connection collection = Pool.getDataSource().getConnection();
             Statement statement = collection.createStatement()) {
            statement.execute(
                    IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("schema.sql"))
            );
        } catch (final IOException | SQLException e) {
            log.error("error", e);
        }
    }
}
