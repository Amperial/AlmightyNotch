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
import me.ampayne2.randomevents.api.PlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles attempting to trigger a {@link me.ampayne2.randomevents.api.PlayerEvent}.
 */
public class PlayerEventHandler extends EventHandler<PlayerEvent> {

    @Override
    public boolean triggerEvent(RandomEvents plugin, PlayerEvent event) {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        while (!players.isEmpty()) {
            Player player = players.get(RANDOM.nextInt(players.size()));
            if (event.canOccur(player)) {
                event.trigger(plugin, player);
                return true;
            } else {
                players.remove(player);
            }
        }
        return false;
    }
}
