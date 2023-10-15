package pt.z4.newstaff.utils;

public class ChatColor {

    public static String color(final String string) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', string);
    }
}
