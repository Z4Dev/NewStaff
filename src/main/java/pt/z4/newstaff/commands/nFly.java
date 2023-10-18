package pt.z4.newstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;

public class nFly implements CommandExecutor {

    private final StaffTable staffTable;

    public nFly(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command!");
            return false;
        }
        Player player = (Player) sender;

        Staff staff = staffTable.isOnStaffMode(player.getDisplayName()).join();

        if(!player.hasPermission("newstaff.fly")) {
            player.sendMessage("You don't have permission to execute this command!");
            return false;
        }

        if(!staff.isOnStaffMode()) {
            player.sendMessage("You must be on staff mode to execute this command!");
            return false;
        }



        return false;
    }
}
