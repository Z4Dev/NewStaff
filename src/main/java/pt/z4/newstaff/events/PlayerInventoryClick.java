package pt.z4.newstaff.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pt.z4.newstaff.Database.tables.StaffTable;
import pt.z4.newstaff.models.Staff;

public class PlayerInventoryClick implements Listener {

    private final StaffTable staffTable;

    public PlayerInventoryClick(StaffTable staffTable) {
        this.staffTable = staffTable;
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        Staff staff = staffTable.isOnStaffMode(player.getDisplayName()).join();
        if(staff.isOnStaffMode()) {
            event.setCancelled(true);
        }
    }
}
