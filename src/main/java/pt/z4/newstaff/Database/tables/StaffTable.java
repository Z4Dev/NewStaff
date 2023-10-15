package pt.z4.newstaff.Database.tables;

import pt.z4.newstaff.Database.Database;
import pt.z4.newstaff.Database.DatabaseManager;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StaffTable extends Database {
    String query = "CREATE TABLE IF NOT EXISTS staff (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(16), staff BOOLEAN, PRIMARY KEY (id))";

    public StaffTable(DatabaseManager databaseManager) {
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
