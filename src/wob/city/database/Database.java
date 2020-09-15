package wob.city.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final HikariConfig config = new HikariConfig("db.conf");
    private static final HikariDataSource ds = new HikariDataSource(config);

    private Database(){}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
