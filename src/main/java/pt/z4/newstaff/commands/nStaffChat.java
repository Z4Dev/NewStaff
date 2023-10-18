package pt.z4.newstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pt.z4.newstaff.utils.ChatColor;

public class nStaffChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("newstaff.staffchat")) {
            player.sendMessage(ChatColor.color("&cYou don't have permission to execute this command!"));
            return false;
        }

        if(args.length == 0) {
            player.sendMessage(ChatColor.color("&cUsage: /" + command.getName() +  " <message>"));
            return false;
        }

        StringBuilder message = new StringBuilder();
        for(String arg : args) {
            message.append(arg).append(" ");
        }

        for (Player online : player.getServer().getOnlinePlayers()) {
            if (online.hasPermission("newstaff.staffchat")) {
                online.sendMessage(ChatColor.color("&b[Staff Chat] &f" + player.getDisplayName() + ": " + message));
            }
        }

        return false;
    }
}
