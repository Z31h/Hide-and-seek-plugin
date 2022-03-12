package me.cfg.hide;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SeekCommand implements CommandExecutor{
	
	public static Player seekPlayer;
	public static boolean started = false;

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		
		if(arg3[0].toLowerCase().contains("start")) {
			arg0.sendMessage("ran");
			seekPlayer.getInventory().addItem(new ItemStack(Material.STICK, 1));
			seekPlayer.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
			started = true;
			arg0.sendMessage("started");
			return true;
		}
		if(arg3[0].toLowerCase().contains("set")) {
			for(Player p : arg0.getServer().getOnlinePlayers()) {
				arg0.sendMessage(p.getName());
				if(p.getName().toLowerCase().contains(arg3[1].toLowerCase())) {
					seekPlayer = p;
					arg0.sendMessage("set player");
					return true;
				}
			}
			arg0.sendMessage("set");
		}		
		if(arg3[0].toLowerCase().contains("end")) {
			started = false;
			seekPlayer = null;
			for(Player p : arg0.getServer().getOnlinePlayers()) {
				p.getInventory().clear();
				p.getEquipment().setHelmet(null);
			}
			arg0.sendMessage("ended");
			return true;
		}
		arg0.sendMessage(arg3[0].toLowerCase());
		return false;
	}
}
