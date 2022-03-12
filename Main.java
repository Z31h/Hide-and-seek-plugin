package me.cfg.hide;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		super.onEnable();

		getServer().getPluginManager().registerEvents(this, this);

		getCommand("seek").setExecutor(new SeekCommand());
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (SeekCommand.started == false) {
			if (event.getPlayer() == SeekCommand.seekPlayer) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerTakeDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			Player hitter = (Player) event.getDamager();
			if (hitter == SeekCommand.seekPlayer) {
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage("YOU GOT FOUND" + ChatColor.RED);
			}
		}
	}
}