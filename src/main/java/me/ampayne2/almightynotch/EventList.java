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
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * A special ArrayList that allows you to get a random event by probability.
 */
public class EventList extends ArrayList<Event> {
    private int totalProbability = 0;
    private static final Random RANDOM = new Random();

    public EventList() {
        super();
    }

    public EventList(Collection<? extends Event> c) {
        super(c);
        for (Event event : c) {
            totalProbability += event.getProbability();
        }
    }

    @Override
    public boolean add(Event event) {
        if (super.add(event)) {
            totalProbability += event.getProbability();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            totalProbability -= ((Event) o).getProbability();
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
        totalProbability = 0;
    }

    /**
     * Gets a random event based on the event probabilities.
     *
     * @return The random event chosen.
     */
    public Event getRandomEvent() {
        if (isEmpty()) {
            return null;
        }

        int index = RANDOM.nextInt(totalProbability);
        int sum = 0;
        for (Event event : this) {
            sum += event.getProbability();
            if (index <= sum) {
                return event;
            }
        }
        return null;
    }

    /**
     * Gets the names of all events in the list.
     *
     * @return A list of all event names.
     */
    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        for (Event event : this) {
            list.add(event.getName());
        }
        return list;
    }
}
