package pt.z4.newstaff;

import org.bukkit.plugin.java.JavaPlugin;
import pt.z4.newstaff.Database.DatabaseManager;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.commands.nStaff;

public final class Newstaff extends JavaPlugin {

    private StaffTable staffTable;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        databaseManager = new DatabaseManager(this);
        staffTable = new StaffTable(databaseManager);
        staffTable.createTables();

        getCommand("nstaff").setExecutor(new nStaff(staffTable));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
