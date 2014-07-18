/*
 * This file is part of AlmightyNotch.
 *
 * Copyright (c) 2014 <http://dev.bukkit.org/server-mods/almightynotch//>
 *
 * AlmightyNotch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlmightyNotch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AlmightyNotch.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.ampayne2.almightynotch.commands;

import me.ampayne2.almightynotch.AlmightyNotch;
import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.Message;
import me.ampayne2.amplib.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that gets Almighty Notch's {@link me.ampayne2.almightynotch.Mood} and mood level.
 */
public class MoodInfoCommand extends Command {
    private final AlmightyNotchPlugin plugin;

    public MoodInfoCommand(AlmightyNotchPlugin plugin) {
        super(plugin, "info");
        setDescription("Gets Almighty Notch's mood and mood level.");
        setCommandUsage("/an mood info");
        setPermission(new Permission("almightynotch.mood.info", PermissionDefault.TRUE));
        setPlayerOnly(false);
        this.plugin = plugin;
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        AlmightyNotch notch = plugin.getNotch();
        plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_INFO, notch.getMood().getName(), String.valueOf(notch.getMoodLevel()));
    }
}
