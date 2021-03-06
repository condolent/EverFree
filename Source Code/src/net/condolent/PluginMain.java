package net.condolent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

	public Permission admin = new Permission("everfree.admin");

	private static Plugin plugin;
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		getLogger().info("Plugin enabled!");

		plugin = this;
		
		this.getServer().getPluginManager().registerEvents(new PluginEvents(this), this);

		this.getConfig().options().copyDefaults(false);
		saveDefaultConfig();

	}

	public void onDisable() {
		getLogger().info("Disabling plugin..");
		saveDefaultConfig();
	}

	PluginDescriptionFile pdf = this.getDescription();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("everfree")) {
			
			if (args.length < 1 || args.length > 1) {
				p.sendMessage("---------------------------------------------");
				p.sendMessage(ChatColor.RED + "Not enough arguments..");
				p.sendMessage(ChatColor.YELLOW + "Correct usage: /everfree <argument>");
				p.sendMessage(ChatColor.YELLOW + "Available arguments: version, reload, info");
				p.sendMessage("---------------------------------------------");
			} else if (args.length == 1) {
				
				if (args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.YELLOW + "This server is running version " + pdf.getVersion() + "of " + pdf.getName());
				}
				
				if (args[0].equalsIgnoreCase("reload")) {
					if (p.hasPermission(admin)) {
						reloadConfig();
						p.sendMessage(ChatColor.GREEN + "Config reloaded!");
					} else {
						p.sendMessage(ChatColor.RED + "You don't have the correct permissions..");
					}
				}
				
				if(args[0].equalsIgnoreCase("info")) {
					p.sendMessage("---------------------------------------------");
					p.sendMessage(ChatColor.YELLOW + "This plugin was made by Condolent for the EverFree Server.");
					p.sendMessage(ChatColor.AQUA + "http://twitter.com/hyprcsgo");
					p.sendMessage("---------------------------------------------");
				}
				
			}
			
			return true;
		}

		return false;
	}

}
