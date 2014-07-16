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
package me.ampayne2.almightynotch;

import me.ampayne2.almightynotch.commands.SetMoodCommand;
import me.ampayne2.almightynotch.commands.TriggerCommand;
import me.ampayne2.almightynotch.event.DefaultEvent;
import me.ampayne2.almightynotch.event.Event;
import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.command.Command;
import me.ampayne2.amplib.command.CommandGroup;
import me.ampayne2.amplib.command.commands.AboutCommand;
import me.ampayne2.amplib.command.commands.HelpCommand;
import me.ampayne2.amplib.command.commands.ReloadCommand;
import me.ampayne2.amplib.messenger.DefaultMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.EnumSet;

/**
 * The main class of the AlmightyNotch plugin.
 */
public class AlmightyNotchPlugin extends AmpJavaPlugin {
    private AlmightyNotch notch;

    @Override
    public void onEnable() {
        DefaultMessage.PREFIX.setMessage("&9[&7AlmightyNotch&9]&7 ");
        DefaultMessage.RELOAD.setMessage("Reloaded AlmightyNotch.");
        enableAmpLib();
        getConfigManager().registerConfigTypes(EnumSet.allOf(ConfigType.class));
        getMessenger().registerMessages(EnumSet.allOf(Message.class));
        getMessenger().registerMessages(EnumSet.allOf(Mood.class));

        FileConfiguration config = getConfig();
        // Remove all events that are disabled in the config
        for (DefaultEvent defaultEvent : DefaultEvent.class.getEnumConstants()) {
            Event event = defaultEvent.getEvent();
            if (!config.getBoolean("Events." + event.getName() + ".Enabled", true)) {
                event.remove();
            }
        }

        notch = new AlmightyNotch(this);

        Command about = new AboutCommand(this);
        about.setCommandUsage("/an");
        Command help = new HelpCommand(this);
        help.setCommandUsage("/an help");
        Command reload = new ReloadCommand(this);
        reload.setCommandUsage("/an reload");
        CommandGroup almightyNotch = new CommandGroup(this, "almightynotch")
                .addChildCommand(about)
                .addChildCommand(help)
                .addChildCommand(reload)
                .addChildCommand(new TriggerCommand(this))
                .addChildCommand(new SetMoodCommand(this));
        almightyNotch.setPermission(new Permission("almightynotch.admin", PermissionDefault.OP));
        getCommandController().addCommand(almightyNotch);
    }

    @Override
    public void onDisable() {
        notch.save();
        notch = null;

        disableAmpLib();
    }

    /**
     * Gets the {@link me.ampayne2.almightynotch.AlmightyNotch} instance.
     *
     * @return The {@link me.ampayne2.almightynotch.AlmightyNotch} instance.
     */
    public AlmightyNotch getNotch() {
        return notch;
    }
}
