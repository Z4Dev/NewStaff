package pt.z4.newstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;
import pt.z4.newstaff.utils.ChatColor;

public class nTpTo implements CommandExecutor {

    private final StaffTable staffTable;

    public nTpTo(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("newstaff.tpto")) {
            player.sendMessage(ChatColor.color("&cYou don't have permission to execute this command!"));
            return false;
        }

        Staff staff = staffTable.isOnStaffMode(player.getDisplayName()).join();

        if(!staff.isOnStaffMode()) {
            player.sendMessage(ChatColor.color("&cYou must be on staff mode to execute this command!"));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(ChatColor.color("&cUsage: /" + command.getName() +  " <player>"));
            return false;
        }

        Player target = player.getServer().getPlayer(args[0]);

        if(target == null) {
            player.sendMessage(ChatColor.color("&cPlayer not found!"));
            return false;
        }

        if(target == player) {
            player.sendMessage(ChatColor.color("&cYou can't teleport to yourself!"));
            return false;
        }

        player.teleport(target.getLocation());
        player.sendMessage(ChatColor.color("&aYou have been teleported to &f" + target.getName() + "&a!"));

        return false;
    }
}
