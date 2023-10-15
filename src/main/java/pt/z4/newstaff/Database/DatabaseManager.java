package pt.z4.newstaff.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import pt.z4.newstaff.Newstaff;

public class DatabaseManager {
    private final Newstaff plugin;
    private final HikariDataSource dataSource;

    public DatabaseManager(Newstaff plugin) {
        this.plugin = plugin;

        HikariConfig config = new HikariConfig();
        config.setPoolName("NewStaff MySQL Pool");
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + plugin.getConfig().getString("host") + ":" + plugin.getConfig().getInt("port") + "/" + plugin.getConfig().getString("database"));
        config.setUsername(plugin.getConfig().getString("username"));
        config.setPassword(plugin.getConfig().getString("password"));
        config.setMaximumPoolSize(2);
        config.setMinimumIdle(2);
        config.setIdleTimeout(120000);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
