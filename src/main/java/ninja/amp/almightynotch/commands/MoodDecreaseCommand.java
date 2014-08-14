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
package ninja.amp.almightynotch.commands;

import ninja.amp.almightynotch.AlmightyNotch;
import ninja.amp.almightynotch.AlmightyNotchPlugin;
import ninja.amp.almightynotch.Message;
import ninja.amp.almightynotch.Mood;
import ninja.amp.amplib.command.Command;
import ninja.amp.amplib.messenger.DefaultMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * A command that decreases Almighty Notch's {@link ninja.amp.almightynotch.Mood}.
 */
public class MoodDecreaseCommand extends Command {
    private final AlmightyNotchPlugin plugin;

    public MoodDecreaseCommand(AlmightyNotchPlugin plugin) {
        super(plugin, "decrease");
        setDescription("Decreases Almighty Notch's mood or mood level.");
        setCommandUsage("/an mood decrease [amount]");
        setPermission(new Permission("almightynotch.mood.decrease", PermissionDefault.OP));
        setArgumentRange(0, 1);
        setPlayerOnly(false);
        this.plugin = plugin;
    }

    @Override
    public void execute(String command, CommandSender sender, String[] args) {
        AlmightyNotch notch = plugin.getNotch();
        if (args.length == 0) {
            Mood mood = notch.getMood();
            if (mood.equals(mood.getDecreasedMood())) {
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_CANT_DECREASE);
            } else {
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_DECREASE);
                notch.setMood(mood.getDecreasedMood());
                notch.save();
            }
        } else {
            try {
                int amount = Integer.valueOf(args[0]);
                plugin.getMessenger().sendMessage(sender, Message.NOTCH_MOOD_DECREASE);
                notch.modifyMoodLevel(-Math.abs(amount));
                notch.save();
            } catch (Exception e) {
                plugin.getMessenger().sendMessage(sender, DefaultMessage.ERROR_NUMBERFORMAT);
            }
        }
    }
}
