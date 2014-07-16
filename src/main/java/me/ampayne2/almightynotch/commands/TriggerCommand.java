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
import me.ampayne2.almightynotch.event.DefaultEvent;
import me.ampayne2.almightynotch.event.Event;
import me.ampayne2.amplib.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

/**
 * A command that triggers a random or specific event.
 */
public class TriggerCommand extends Command {
    private final AlmightyNotchPlugin plugin;

    public TriggerCommand(AlmightyNotchPlugin plugin) {
        super(plugin, "trigger");
        setDescription("Triggers an event.");
        setCommandUsage("/an trigger [event]");
        setPermission(new Permission("almightynotch.trigger", PermissionDefault.OP));
        setArgumentRange(0, 1);
        setPlayerOnly(false);
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(String command, CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (plugin.getNotch().triggerEvent()) {
                plugin.getMessenger().sendMessage(sender, Message.EVENT_TRIGGER, "random event");
            } else {
                plugin.getMessenger().sendMessage(sender, Message.EVENT_NOT_TRIGGERED);
            }
        } else {
            Event event = DefaultEvent.byName(args[0]);
            if (event == null) {
                plugin.getMessenger().sendMessage(sender, Message.EVENT_NOT_FOUND);
            } else {
                if (event.getHandler().triggerEvent(plugin, event)) {
                    plugin.getMessenger().sendMessage(sender, Message.EVENT_TRIGGER, event.getName());
                } else {
                    plugin.getMessenger().sendMessage(sender, Message.EVENT_NOT_TRIGGERED);
                }
            }
        }
    }

    @Override
    public List<String> getTabCompleteList(String[] args) {
        return DefaultEvent.getEventList().getNames();
    }
}
