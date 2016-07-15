package ru.parsentev.storages;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 14.07.2016
 */
public class Pool {
    private static final Logger log = LoggerFactory.getLogger(Pool.class);
    private final ComboPooledDataSource source;

    private static final Pool instance = new Pool();

    private Pool() {
        this.source = new ComboPooledDataSource();
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("clinic.properties"));
            source.setJdbcUrl(properties.getProperty("url"));
            source.setUser(properties.getProperty("username"));
            source.setPassword(properties.getProperty("password"));
            source.setDriverClass(properties.getProperty("driver"));
            source.setMinPoolSize(5);
            source.setAcquireIncrement(5);
            source.setMaxPoolSize(20);
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    public static DataSource getDataSource() {
        return instance.source;
    }
}
