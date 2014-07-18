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
import me.ampayne2.almightynotch.Mood;
import me.ampayne2.amplib.command.Command;
import me.ampayne2.amplib.messenger.DefaultMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that increases Almighty Notch's {@link me.ampayne2.almightynotch.Mood}.
 */
public class MoodIncreaseCommand extends Command {
    private final AlmightyNotchPlugin plugin;

    public MoodIncreaseCommand(AlmightyNotchPlugin plugin) {
        super(plugin, "increase");
        setDescription("Increases Almighty Notch's mood or mood level.");
        setCommandUsage("/an mood increase [amount]");
        setPermission(new Permission("almightynotch.mood.increase", PermissionDefault.OP));
        setArgumentRange(0, 1);
        setPlayerOnly(false);
        this.plugin = plugin;
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        AlmightyNotch notch = plugin.getNotch();
        if (args.length == 0) {
            Mood mood = notch.getMood();
            if (mood.equals(mood.getIncreasedMood())) {
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_CANT_INCREASE);
            } else {
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_INCREASE);
                notch.setMood(mood.getIncreasedMood());
                notch.save();
            }
        } else {
            try {
                int amount = Integer.valueOf(args[0]);
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_INCREASE);
                notch.modifyMoodLevel(Math.abs(amount));
                notch.save();
            } catch (Exception e) {
                plugin.getMessenger().sendMessage(sender, DefaultMessage.ERROR_NUMBERFORMAT);
            }
        }
    }
}
