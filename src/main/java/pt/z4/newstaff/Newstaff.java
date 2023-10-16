package pt.z4.newstaff;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pt.z4.newstaff.Database.DatabaseManager;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.commands.nStaff;
import pt.z4.newstaff.commands.nStaffChat;
import pt.z4.newstaff.commands.nTpTo;
import pt.z4.newstaff.events.PlayerDrop;
import pt.z4.newstaff.events.PlayerInventoryClick;
import pt.z4.newstaff.events.PlayerPickup;

public final class Newstaff extends JavaPlugin {

    private StaffTable staffTable;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        databaseManager = new DatabaseManager(this);
        staffTable = new StaffTable(databaseManager);
        staffTable.createTables();

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerDrop(staffTable), this);
        pluginManager.registerEvents(new PlayerPickup(staffTable), this);
        pluginManager.registerEvents(new PlayerInventoryClick(staffTable), this);

        getCommand("nstaff").setExecutor(new nStaff(staffTable));
        getCommand("staffchat").setExecutor(new nStaffChat());
        getCommand("tpto").setExecutor(new nTpTo(staffTable));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
