package pt.z4.newstaff;

import org.bukkit.plugin.java.JavaPlugin;
import pt.z4.newstaff.Database.DatabaseManager;
import pt.z4.newstaff.Database.tables.DefaultTable;
import pt.z4.newstaff.commands.nStaff;

public final class Newstaff extends JavaPlugin {

    private DefaultTable defaultTable;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        databaseManager = new DatabaseManager(this);
        defaultTable = new DefaultTable(databaseManager);
        defaultTable.createTables();

        getCommand("nstaff").setExecutor(new nStaff());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
