package pt.z4.newstaff.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;

public class PlayerDrop implements Listener {

        private final StaffTable staffTable;

        public PlayerDrop(StaffTable staffTable) {
            this.staffTable = staffTable;
        }

        @EventHandler
        public void onPlayerDrop(PlayerDropItemEvent event) {
            Staff staff = staffTable.isOnStaffMode(event.getPlayer().getDisplayName()).join();

            if(staff.isOnStaffMode()) {
                event.setCancelled(true);
            }
        }
}
