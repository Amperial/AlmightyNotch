/*
 * This file is part of RandomEvents.
 *
 * Copyright (c) 2014 <http://dev.bukkit.org/server-mods/randomevents//>
 *
 * RandomEvents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RandomEvents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RandomEvents.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.ampayne2.randomevents.commands;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.RandomEvent;
import me.ampayne2.randomevents.messaging.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * The command controller for RandomEvents.
 */
public class CommandController implements CommandExecutor {
    private RandomEvents plugin;

    public CommandController(RandomEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("randomevents")) {
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.BLUE + "RandomEvents Commands:");
            sender.sendMessage(ChatColor.GRAY + "/re start");
            sender.sendMessage(ChatColor.GRAY + "- Starts the random events");
            sender.sendMessage(ChatColor.GRAY + "/re stop");
            sender.sendMessage(ChatColor.GRAY + "- Stops the random events");
            sender.sendMessage(ChatColor.GRAY + "/re reload");
            sender.sendMessage(ChatColor.GRAY + "- Reloads the plugin");
            sender.sendMessage(ChatColor.GRAY + "/re trigger <event>");
            sender.sendMessage(ChatColor.GRAY + "- Manually triggers an event");
            return true;
        } else {
            switch (args[0].toLowerCase()) {
                case "start":
                    if (plugin.getEventManager().isRunning()) {
                        plugin.getMessenger().sendMessage(sender, Message.ALREADY_RUNNING);
                    } else {
                        plugin.getEventManager().startEvents();
                        plugin.getMessenger().sendMessage(sender, Message.START_EVENTS);
                    }
                    break;
                case "stop":
                    if (plugin.getEventManager().isRunning()) {
                        plugin.getEventManager().stopEvents();
                        plugin.getMessenger().sendMessage(sender, Message.STOP_EVENTS);
                    } else {
                        plugin.getMessenger().sendMessage(sender, Message.ALREADY_STOPPED);
                    }
                    break;
                case "reload":
                    plugin.onDisable();
                    plugin.onEnable();
                    plugin.getMessenger().sendMessage(sender, Message.RELOAD);
                    break;
                case "trigger":
                    if (args.length == 2) {
                        RandomEvent event = plugin.getEventManager().getEvent(args[1]);
                        if (event == null) {
                            plugin.getMessenger().sendMessage(sender, Message.EVENT_NOT_FOUND);
                        } else {
                            plugin.getMessenger().sendMessage(sender, Message.EVENT_TRIGGER, event.getName());
                            event.getHandler().triggerEvent(plugin, event);
                        }
                    } else {
                        plugin.getMessenger().sendMessage(sender, Message.COMMAND_USAGE, "/re trigger <event name>");
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}
