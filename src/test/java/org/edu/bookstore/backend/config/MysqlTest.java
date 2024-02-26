package org.edu.bookstore.backend.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class MysqlTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testConnection() throws SQLException {
        assertTrue(applicationContext.containsBean("dataSource"));
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        assertTrue(dataSource instanceof HikariDataSource);
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
        connection.close();
    }
}
