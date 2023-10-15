package pt.z4.newstaff.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public abstract class Database {

    protected static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);
    private final DatabaseManager databaseManager;

    protected Database(DatabaseManager databaseManager, String... queries) {
        this.databaseManager = databaseManager;
        loadQueries(queries);
    }

    private void loadQueries(String[] queries) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            for (String query : queries)
                statement.addBatch(query);

            statement.executeBatch();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, EXECUTOR);
    }

    public CompletableFuture<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, EXECUTOR);
    }

    public Connection getConnection() throws SQLException {
        return databaseManager.getConnection();
    }
}
