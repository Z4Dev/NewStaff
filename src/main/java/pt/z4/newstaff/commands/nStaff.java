package pt.z4.newstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.utils.ChatColor;

public class nStaff implements CommandExecutor {
    private StaffTable staffTable;

    public nStaff(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command!");
            return false;
        }
        Player player = (Player) sender;

        if(!player.hasPermission("newstaff.staff")) {
            player.sendMessage(ChatColor.color("&cYou don't have permission to execute this command!"));
            return false;
        }

        staffTable.isOnStaffMode(player.getDisplayName()).thenAccept(result -> {
            if (result.exists()) {
                staffTable.updateStaffMode(player.getDisplayName(), !result.isOnStaffMode());
                player.sendMessage(ChatColor.color("&bStaff Mode:" + (result.isOnStaffMode() ? " &cOFF" : " &aON")));
            } else {
                staffTable.addStaff(player.getDisplayName());
                player.sendMessage(ChatColor.color("&bStaff Mode: &aON"));
            }
        });

        return false;
    }
}
