package pt.z4.newstaff.Database.tables;

import pt.z4.newstaff.Database.Database;
import pt.z4.newstaff.Database.DatabaseManager;
import pt.z4.newstaff.models.Staff;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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

    public CompletableFuture<Staff> isOnStaffMode(String username) {
        return supplyAsync(() -> {
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM staff WHERE name = ?")) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    boolean staff = resultSet.getBoolean("staff");
                    return new Staff(name,staff,name != null);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        });
    }

    public CompletableFuture<Void> updateStaffMode(String username, boolean staff) {
        return supplyAsync(() -> {
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE staff SET staff = ? WHERE name = ?")) {
                statement.setBoolean(1, staff);
                statement.setString(2, username);
                statement.executeUpdate();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        });
    }

    public CompletableFuture<Void> addStaff(String username) {
        return supplyAsync(() -> {
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO staff (name, staff) VALUES (?, ?)")) {
                statement.setString(1, username);
                statement.setBoolean(2, true);
                statement.executeUpdate();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return null;
        });
    }
}
