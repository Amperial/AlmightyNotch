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

import ninja.amp.almightynotch.event.Event;
import ninja.amp.almightynotch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Almighty Notch's brain.
 */
public class AlmightyNotch {
    private final AlmightyNotchPlugin plugin;
    private Mood mood = Mood.SLEEPY;
    private int moodLevel;
    private final int moodInterval;

    public AlmightyNotch(AlmightyNotchPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfigManager().getConfig(ConfigType.NOTCH);
        Mood configMood = Mood.byName(config.getString("Mood", "Sleepy"));
        if (configMood != null) {
            mood = configMood;
        }
        moodLevel = config.getInt("MoodLevel", 0);
        moodInterval = plugin.getConfig().getInt("MoodInterval", 25);
        plugin.getMessenger().debug("Loaded Notch with mood " + mood.getName() + " and mood level " + moodLevel);
    }

    /**
     * Gets Almighty Notch's {@link Mood}.
     *
     * @return Almighty Notch's {@link Mood}.
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Sets Almighty Notch's {@link Mood}.
     *
     * @param mood The {@link Mood}.
     */
    public void setMood(Mood mood) {
        this.mood = mood;
        this.moodLevel = 0;
        plugin.getMessenger().sendMessage(Bukkit.getServer(), mood);
        plugin.getMessenger().debug("Set Notch's mood to " + mood.getName());
    }

    /**
     * Gets Almighty Notch's mood level.
     *
     * @return Almighty Notch's mood level.
     */
    public int getMoodLevel() {
        return moodLevel;
    }

    /**
     * Sets Almighty Notch's mood level.
     *
     * @param moodLevel The mood level.
     */
    public void setMoodLevel(int moodLevel) {
        this.moodLevel = Util.clamp(moodLevel, 0, moodInterval);
        plugin.getMessenger().debug("Set Notch's mood level to " + this.moodLevel);
    }

    /**
     * Modifies Almighty Notch's mood level.
     *
     * @param amount The amount to increase or decrease the mood level by.
     */
    public void modifyMoodLevel(int amount) {
        plugin.getMessenger().debug("Modifying mood level " + moodLevel + " by " + amount);
        Mood newMood = mood;
        int newMoodLevel = moodLevel + amount;
        if (newMoodLevel > moodInterval) {
            while (newMoodLevel > moodInterval) {
                if (newMood.equals(newMood.getIncreasedMood())) {
                    newMoodLevel = moodInterval;
                } else {
                    newMood = newMood.getIncreasedMood();
                    newMoodLevel -= moodInterval;
                }
            }
        } else if (newMoodLevel < 0) {
            while (newMoodLevel < 0) {
                if (newMood.equals(newMood.getDecreasedMood())) {
                    newMoodLevel = 0;
                } else {
                    newMood = newMood.getDecreasedMood();
                    newMoodLevel += moodInterval;
                }
            }
        }
        setMood(newMood);
        setMoodLevel(newMoodLevel);
    }

    @SuppressWarnings("unchecked")
    public boolean triggerEvent() {
        EventList events = new EventList(mood.getEvents());
        while (!events.isEmpty()) {
            Event event = events.getRandomEvent();
            plugin.getMessenger().debug("Attempting to trigger event " + event.getName());
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
        FileConfiguration config = plugin.getConfigManager().getConfig(ConfigType.NOTCH);
        config.set("Mood", mood.getName());
        config.set("MoodLevel", moodLevel);
        plugin.getConfigManager().getConfigAccessor(ConfigType.NOTCH).saveConfig();
        plugin.getMessenger().debug("Saved Notch to the config.");
    }
}
