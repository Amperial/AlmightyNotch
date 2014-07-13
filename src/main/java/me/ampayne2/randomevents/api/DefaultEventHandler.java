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
import me.ampayne2.randomevents.api.handlers.LocationEventHandler;
import me.ampayne2.randomevents.api.handlers.MinigameEventHandler;
import me.ampayne2.randomevents.api.handlers.PlayerEventHandler;
import me.ampayne2.randomevents.api.handlers.WorldEventHandler;

/**
 * Default event handlers.
 */
public enum DefaultEventHandler {
    LOCATION(new LocationEventHandler()),
    PLAYER(new PlayerEventHandler()),
    WORLD(new WorldEventHandler()),
    MINIGAME(new MinigameEventHandler());

    private final EventHandler eventHandler;

    private DefaultEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.api.handlers.EventHandler} instance.
     *
     * @return The {@link me.ampayne2.randomevents.api.handlers.EventHandler} instance.
     */
    public EventHandler getHandler() {
        return eventHandler;
    }
}
