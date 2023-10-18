package pt.z4.newstaff.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;

public class PlayerJoin implements Listener {

    private final StaffTable staffTable;

    public PlayerJoin(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Staff staff = staffTable.isOnStaffMode(event.getPlayer().getDisplayName()).join();

        if(staff.isOnStaffMode()) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().setFlying(true);
        }
    }
}
