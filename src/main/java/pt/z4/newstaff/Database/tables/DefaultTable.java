package pt.z4.newstaff.Database.tables;

import pt.z4.newstaff.Database.Database;
import pt.z4.newstaff.Database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.CompletableFuture;

public class DefaultTable extends Database {
    String query = "CREATE TABLE IF NOT EXISTS example (name VARCHAR(16), PRIMARY KEY (name))";

    public DefaultTable(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    public CompletableFuture<Void> createTables() {
        return supplyAsync(() -> {
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.execute();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        });
    }
}
