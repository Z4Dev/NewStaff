package pt.z4.newstaff.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;

public class PlayerPickup implements Listener {

    private final StaffTable staffTable;

    public PlayerPickup(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        Staff staff = staffTable.isOnStaffMode(player.getDisplayName()).join();
        if(staff.isOnStaffMode()) {
            event.setCancelled(true);
        }
    }
}
