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
package me.ampayne2.randomevents.api.handlers;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.RandomEvent;

import java.util.Random;

/**
 * Handles attempting to trigger a type of {@link me.ampayne2.randomevents.api.RandomEvent}.
 */
public abstract class EventHandler<E extends RandomEvent> {
    protected static final Random RANDOM = new Random();

    /**
     * Attempts to trigger a {@link me.ampayne2.randomevents.api.RandomEvent}.
     *
     * @param plugin The {@link me.ampayne2.randomevents.RandomEvents} instance.
     * @param event  The {@link me.ampayne2.randomevents.api.RandomEvent} to trigger.
     * @return True if the {@link me.ampayne2.randomevents.api.RandomEvent} was triggered, else false.
     */
    public abstract boolean triggerEvent(RandomEvents plugin, E event);
}
