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
package ninja.amp.almightynotch;

import ninja.amp.almightynotch.commands.MoodDecreaseCommand;
import ninja.amp.almightynotch.commands.MoodIncreaseCommand;
import ninja.amp.almightynotch.commands.MoodInfoCommand;
import ninja.amp.almightynotch.commands.MoodSetCommand;
import ninja.amp.almightynotch.commands.TriggerCommand;
import ninja.amp.almightynotch.event.DefaultEvent;
import ninja.amp.almightynotch.event.Event;
import ninja.amp.amplib.AmpJavaPlugin;
import ninja.amp.amplib.command.Command;
import ninja.amp.amplib.command.CommandGroup;
import ninja.amp.amplib.command.commands.AboutCommand;
import ninja.amp.amplib.command.commands.HelpCommand;
import ninja.amp.amplib.command.commands.ReloadCommand;
import ninja.amp.amplib.messenger.DefaultMessage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.ArrayList;
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
        getMessenger().debug("Setup AmpLib");

        FileConfiguration config = getConfigManager().getConfig(ConfigType.EVENTS);
        // Load event settings from the config
        for (Event event : new ArrayList<>(DefaultEvent.getEventList())) {
            if (config.isConfigurationSection(event.getName())) {
                ConfigurationSection section = config.getConfigurationSection(event.getName());
                if (config.getBoolean("Enabled", true)) {
                    event.load(config.getConfigurationSection(event.getName()));
                } else {
                    event.remove();
                }
            } else {
                event.save(config.createSection(event.getName()));
            }
        }
        getMessenger().debug("Loaded event settings");

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
                .addChildCommand(new CommandGroup(this, "mood")
                        .addChildCommand(new MoodInfoCommand(this))
                        .addChildCommand(new MoodSetCommand(this))
                        .addChildCommand(new MoodIncreaseCommand(this))
                        .addChildCommand(new MoodDecreaseCommand(this)));
        almightyNotch.setPermission(new Permission("almightynotch.admin", PermissionDefault.OP));
        getCommandController().addCommand(almightyNotch);
        getMessenger().debug("Added commands");
    }

    @Override
    public void onDisable() {
        notch.save();
        notch = null;

        disableAmpLib();
    }

    /**
     * Gets the {@link AlmightyNotch} instance.
     *
     * @return The {@link AlmightyNotch} instance.
     */
    public AlmightyNotch getNotch() {
        return notch;
    }
}
