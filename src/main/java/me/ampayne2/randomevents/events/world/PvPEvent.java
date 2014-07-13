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
package me.ampayne2.randomevents.events.world;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.MinigameEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PvPEvent extends MinigameEvent implements Listener {
    public PvPEvent() {
        super("PvP");
        setProbability(5);
        setDescription("Rewards the first player to kill another player.");
        setOccurMessage("The next person to kill another player will be rewarded!");
    }

    @Override
    public void trigger(RandomEvents plugin) {
        super.trigger(plugin);
        plugin.getMessenger().broadcastEventMessage(this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (isRunning() && event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            // TODO: Reward killer
        }
    }
}
