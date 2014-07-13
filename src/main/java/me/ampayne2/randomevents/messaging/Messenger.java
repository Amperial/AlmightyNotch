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
package me.ampayne2.randomevents.messaging;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.RandomEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles sending messages, debugging and logging for the RandomEvents plugin.
 */
public class Messenger {
    private RandomEvents plugin;
    private boolean debug;
    private Logger log;

    public Messenger(RandomEvents plugin) {
        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();
        this.debug = config.getBoolean("Debug", false);
        this.log = plugin.getLogger();

        for (Message message : Message.class.getEnumConstants()) {
            message.setMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Messages." + message.getPath())));
        }
    }

    /**
     * Sends a message to a command sender.
     *
     * @param commandSender  The command sender.
     * @param message The message.
     * @param replace Strings to replace occurences of %s in the message with.
     */
    public void sendMessage(CommandSender commandSender, Message message, String... replace) {
        commandSender.sendMessage(formatMessage(message, replace));
    }

    /**
     * Sends an event message to a command sender.
     *
     * @param commandSender  The command sender.
     * @param event   The event.
     * @param replace Strings to replace occurences of %s in the message with.
     */
    public void sendEventMessage(CommandSender commandSender, RandomEvent event, String... replace) {
        commandSender.sendMessage(formatEventMessage(event, replace));
    }

    /**
     * Broadcasts a message to the server.
     *
     * @param message The message.
     * @param replace Strings to replace occurences of %s in the message with.
     */
    public void broadcastMessage(Message message, String... replace) {
        Bukkit.getServer().broadcastMessage(formatMessage(message, replace));
    }

    /**
     * Broadcasts an event message to the server.
     *
     * @param event   The event.
     * @param replace Strings to replace occurences of %s in the message with.
     */
    public void broadcastEventMessage(RandomEvent event, String... replace) {
        Bukkit.getServer().broadcastMessage(formatEventMessage(event, replace));
    }

    /**
     * Formats a message and adds the prefix.
     *
     * @param message The message.
     * @param replace Strings to replace occurences of %s in the message with.
     * @return The formatted message.
     */
    public String formatMessage(Message message, String... replace) {
        return Message.PREFIX + (replace == null ? message.getMessage() : String.format(message.getMessage(), (Object[]) replace));
    }

    /**
     * Formats an event message and adds the prefix.
     *
     * @param event   The event.
     * @param replace Strings to replace occurences of %s in the message with.
     * @return The formatted event message.
     */
    public String formatEventMessage(RandomEvent event, String... replace) {
        String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Events." + event.getName() + ".Message"));
        return Message.PREFIX + (replace == null ? message : String.format(message, (Object[]) replace));
    }

    /**
     * Logs one or more messages to the console.
     *
     * @param level    the level to log the message at.
     * @param messages the message(s) to log.
     */
    public void log(Level level, String... messages) {
        for (String message : messages) {
            log.log(level, message);
        }
    }

    /**
     * Decides whether or not to print the stack trace of an exception.
     *
     * @param e the exception to debug.
     */
    public void debug(Exception e) {
        if (debug) {
            e.printStackTrace();
        }
    }

    /**
     * Decides whether or not to print a debug message.
     *
     * @param message the message to debug.
     */
    public void debug(String message) {
        if (debug) {
            log.log(Level.INFO, message);
        }
    }

    /**
     * Gets the logger.
     *
     * @return The logger.
     */
    public Logger getLogger() {
        return log;
    }

    /**
     * Destroys the messenger. Do not use after calling this method.
     */
    public void destroy() {
        plugin = null;
        debug = false;
        log = null;
    }
}
