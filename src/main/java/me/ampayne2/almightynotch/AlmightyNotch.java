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

import me.ampayne2.almightynotch.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class AlmightyNotch {
    private final AlmightyNotchPlugin plugin;
    private Mood mood = Mood.SLEEPY;

    public AlmightyNotch(AlmightyNotchPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfigManager().getConfig(ConfigType.NOTCH);
        Mood configMood = Mood.byName(config.getString("Mood", "Sleepy"));
        if (configMood != null) {
            mood = configMood;
        }
    }

    /**
     * Gets Almighty Notch's {@link me.ampayne2.almightynotch.Mood}.
     *
     * @return Almighty Notch's {@link me.ampayne2.almightynotch.Mood}.
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Sets Almighty Notch's {@link me.ampayne2.almightynotch.Mood}.
     *
     * @param mood The {@link me.ampayne2.almightynotch.Mood}.
     */
    public void setMood(Mood mood) {
        this.mood = mood;
        plugin.getMessenger().sendMessage(Bukkit.getServer(), mood);
        save();
    }

    @SuppressWarnings("unchecked")
    public boolean triggerEvent() {
        EventList events = new EventList(mood.getEvents());
        while (!events.isEmpty()) {
            System.out.println(events);
            Event event = events.getRandomEvent();
            if (event.getHandler().triggerEvent(plugin, event)) {
                return true;
            } else {
                events.remove(event);
            }
        }
        return false;
    }

    /**
     * Saves Almighty Notch to the config.
     */
    public void save() {
        plugin.getConfigManager().getConfig(ConfigType.NOTCH).set("Mood", mood.getName());
        plugin.getConfigManager().getConfigAccessor(ConfigType.NOTCH).saveConfig();
    }
}
