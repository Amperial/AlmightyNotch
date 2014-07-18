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

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.Message;
import me.ampayne2.almightynotch.Mood;
import me.ampayne2.amplib.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

/**
 * A command that sets Almighty Notch's {@link me.ampayne2.almightynotch.Mood}.
 */
public class MoodSetCommand extends Command {
    private final AlmightyNotchPlugin plugin;

    public MoodSetCommand(AlmightyNotchPlugin plugin) {
        super(plugin, "set");
        setDescription("Sets Almighty Notch's mood.");
        setCommandUsage("/an mood set <mood>");
        setPermission(new Permission("almightynotch.mood.set", PermissionDefault.OP));
        setArgumentRange(1, 1);
        setPlayerOnly(false);
        this.plugin = plugin;
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        Mood mood = Mood.byName(args[0]);
        if (mood == null) {
            plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_NOT_FOUND);
        } else {
            plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_SET, mood.getName());
            plugin.getNotch().setMood(mood);
            plugin.getNotch().save();
        }
    }

    @Override
    public List<String> getTabCompleteList(String[] args) {
        return Mood.getNames();
    }
}
