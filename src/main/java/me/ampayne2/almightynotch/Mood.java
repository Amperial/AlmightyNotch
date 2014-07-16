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

import me.ampayne2.almightynotch.event.DefaultEvent;
import me.ampayne2.almightynotch.event.Event;

import java.util.HashSet;
import java.util.Set;

/**
 * The range of moods Notch can have.
 */
public enum Mood {
    ECSTATIC("Ecstatic"),
    GENEROUS("Generous"),
    SATISFIED("Satisfied"),
    SLEEPY("Sleepy"),
    BORED("Bored"),
    DISPLEASED("Displeased"),
    INFURIATED("Infuriated");

    private final String name;
    private final Set<Event> events = new HashSet<>();

    private Mood(String name) {
        this.name = name;
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

    static {
        // Populates each mood's events.
        for (Mood mood : Mood.class.getEnumConstants()) {
            for (DefaultEvent event : DefaultEvent.class.getEnumConstants()) {
                if (event.getEvent().hasMood(mood)) {
                    mood.addEvent(event.getEvent());
                }
            }
        }
    }
}
