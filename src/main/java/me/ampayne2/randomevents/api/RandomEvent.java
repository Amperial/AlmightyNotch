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
package me.ampayne2.randomevents.api;

import me.ampayne2.randomevents.api.handlers.EventHandler;

import java.util.Random;

/**
 * The base class for a random event.
 */
public class RandomEvent {
    private final String name;
    private final EventHandler eventHandler;
    private String description;
    private String occurMessage;
    private int probability = 0;
    protected static final Random RANDOM = new Random();

    public RandomEvent(String name, EventHandler eventHandler) {
        this.name = name;
        this.eventHandler = eventHandler;
    }

    /**
     * Gets the name of the random event.
     *
     * @return The random event's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@link DefaultEventHandler} of the random event.
     *
     * @return The random event's {@link DefaultEventHandler}.
     */
    public EventHandler getHandler() {
        return eventHandler;
    }

    /**
     * Gets the description of the random event.
     *
     * @return The random event's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the random event.
     *
     * @param description The description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the occur message of the random event.
     *
     * @return The random event's occur message.
     */
    public String getOccurMessage() {
        return occurMessage;
    }

    /**
     * Sets the occur message of the random event.
     *
     * @param occurMessage The occur message.
     */
    public void setOccurMessage(String occurMessage) {
        this.occurMessage = occurMessage;
    }

    /**
     * Gets the probability of the random event occuring.
     *
     * @return The random event's probability.
     */
    public int getProbability() {
        return probability;
    }

    /**
     * Sets the probability of the random event.
     *
     * @param probability The probability.
     */
    public void setProbability(int probability) {
        this.probability = probability;
    }
}
