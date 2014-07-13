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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * A special ArrayList that allows you to get a random event by probability.
 */
public class EventList extends ArrayList<RandomEvent> {
    private int totalProbability = 0;
    private static final Random RANDOM = new Random();

    public EventList() {
        super();
    }

    public EventList(Collection<? extends RandomEvent> c) {
        super(c);
        for (RandomEvent event : c) {
            totalProbability += event.getProbability();
        }
    }

    @Override
    public boolean add(RandomEvent event) {
        if (super.add(event)) {
            totalProbability += event.getProbability();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            totalProbability -= ((RandomEvent) o).getProbability();
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
    public RandomEvent getRandomEvent() {
        if (isEmpty()) {
            return null;
        }

        int index = RANDOM.nextInt(totalProbability);
        int sum = 0;
        for (RandomEvent event : this) {
            sum += event.getProbability();
            if (index <= sum) {
                return event;
            }
        }
        return null;
    }
}
