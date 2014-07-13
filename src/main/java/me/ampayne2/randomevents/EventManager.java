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
package me.ampayne2.randomevents;

import me.ampayne2.randomevents.api.RandomEvent;
import me.ampayne2.randomevents.events.DefaultEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

/**
 * Manages the {@link me.ampayne2.randomevents.api.RandomEvent} in the RandomEffects plugin.
 */
public class EventManager {
    private RandomEvents plugin;
    private EventList events = new EventList();
    private int eventTask = -1;

    public EventManager(RandomEvents plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("Events");
        for (DefaultEvent defaultEvent : DefaultEvent.class.getEnumConstants()) {
            RandomEvent event = defaultEvent.getEvent();
            if (section.getBoolean(event.getName() + ".Enabled", true)) {
                event.setProbability(section.getInt(event.getName() + ".Probability", 1));
                if (section.contains(event.getName() + ".Message")) {
                    event.setOccurMessage(section.getString(event.getName() + ".Message"));
                }
                addEvent(event);
            }
        }
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.api.RandomEvent}s currently loaded in the manager.
     *
     * @return The {@link me.ampayne2.randomevents.api.RandomEvent}s.
     */
    public EventList getEvents() {
        return events;
    }

    /**
     * Gets a {@link me.ampayne2.randomevents.api.RandomEvent} by its name.
     *
     * @param name The name.
     * @return The {@link me.ampayne2.randomevents.api.RandomEvent}, null if not found.
     */
    public RandomEvent getEvent(String name) {
        for (RandomEvent event : events) {
            if (event.getName().equalsIgnoreCase(name)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Adds a {@link me.ampayne2.randomevents.api.RandomEvent} to the manager.
     *
     * @param event The {@link me.ampayne2.randomevents.api.RandomEvent} to add.
     */
    public void addEvent(RandomEvent event) {
        if (!events.contains(event)) {
            events.add(event);
            if (event instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener) event, plugin);
            }
        }
    }

    /**
     * Removes a {@link me.ampayne2.randomevents.api.RandomEvent} from the manager.
     *
     * @param event The {@link me.ampayne2.randomevents.api.RandomEvent} to remove.
     */
    public void removeEvent(RandomEvent event) {
        events.remove(event);
    }

    /**
     * Starts the task that triggers random events.
     */
    public void startEvents() {
        if (eventTask == -1) {
            long delay = plugin.getConfig().getLong("OccurenceDelay", 60) * 20;
            final int maxTries = plugin.getConfig().getInt("MaxTries", 10);
            eventTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                @SuppressWarnings("unchecked")
                public void run() {
                    if (!events.isEmpty()) {
                        for (int i = 0; i < maxTries; i++) {
                            RandomEvent event = events.getRandomEvent();
                            if (event.getHandler().triggerEvent(plugin, event)) {
                                break;
                            }
                        }
                    }
                }
            }, delay, delay);
        }
    }

    /**
     * Stops the task that triggers random events.
     */
    public void stopEvents() {
        if (eventTask != -1) {
            Bukkit.getScheduler().cancelTask(eventTask);
        }
    }

    /**
     * Checks if the event task is running.
     *
     * @return True if the event task is running, else false.
     */
    public boolean isRunning() {
        return eventTask != -1;
    }

    /**
     * Destroys the event manager. Do not use after calling this method.
     */
    public void destroy() {
        Bukkit.getScheduler().cancelTask(eventTask);
        events = null;
        plugin = null;
    }
}
