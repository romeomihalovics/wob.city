package wob.city.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DatabaseTest {
    @Test
    @DisplayName("Test Database connection")
    void testDatabaseConnection() {
        try {
            Connection connection = Database.getConnection();
            assertTrue(connection.isValid(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
