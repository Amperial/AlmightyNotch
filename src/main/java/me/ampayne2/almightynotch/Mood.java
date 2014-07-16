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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The range of moods Notch can have and their messages.
 */
public enum Mood implements me.ampayne2.amplib.messenger.Message {
    ECSTATIC("Ecstatic", "&2&oAlmighty Notch is ecstatic!"),
    GENEROUS("Generous", "&2Almighty Notch is feeling generous!"),
    SATISFIED("Satisfied", "&aAlmighty Notch is satisfied."),
    SLEEPY("Sleepy", "Almighty Notch is feeling sleepy..."),
    BORED("Bored", "&eAlmighty Notch is looking for some entertainment."),
    DISPLEASED("Displeased", "&6Almighty Notch is displeased."),
    INFURIATED("Infuriated", "&4&lAlmighty Notch is infuriated. Take cover!");

    private final String name;
    private final Set<Event> events = new HashSet<>();

    private String message;
    private final String path;
    private final String defaultMessage;

    private Mood(String name, String defaultMessage) {
        this.name = name;
        this.message = defaultMessage;
        this.path = "Mood." + name;
        this.defaultMessage = defaultMessage;
    }

    /**
     * Gets the name of the Mood.
     *
     * @return The Mood's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@link me.ampayne2.almightynotch.event.Event}s of the Mood.
     *
     * @return The Mood's {@link me.ampayne2.almightynotch.event.Event}s.
     */
    public Set<Event> getEvents() {
        return events;
    }

    /**
     * Adds an {@link me.ampayne2.almightynotch.event.Event} to the Mood.
     *
     * @param event The {@link me.ampayne2.almightynotch.event.Event} to add.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes an {@link me.ampayne2.almightynotch.event.Event} from the Mood.
     *
     * @param event The {@link me.ampayne2.almightynotch.event.Event} to remove.
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDefault() {
        return defaultMessage;
    }

    @Override
    public String toString() {
        return message;
    }

    private static Map<String, Mood> BY_NAME = new HashMap<>();

    /**
     * Gets a Mood by its name.
     *
     * @param name The Mood's name.
     * @return The Mood.
     */
    public static Mood byName(String name) {
        return BY_NAME.get(name.toLowerCase());
    }

    /**
     * Gets the names of all Moods.
     *
     * @return A list of all Mood names.
     */
    public static List<String> getNames() {
        return new ArrayList<>(BY_NAME.keySet());
    }

    static {
        for (Mood mood : Mood.class.getEnumConstants()) {
            BY_NAME.put(mood.getName().toLowerCase(), mood);
        }
    }
}
