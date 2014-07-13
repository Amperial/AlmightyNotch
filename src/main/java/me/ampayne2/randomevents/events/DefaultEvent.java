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
package me.ampayne2.randomevents.events;

import me.ampayne2.randomevents.api.RandomEvent;
import me.ampayne2.randomevents.events.mob.CreeperExplodeEvent;
import me.ampayne2.randomevents.events.mob.DinnerboneEvent;
import me.ampayne2.randomevents.events.mob.FrightenSheepEvent;
import me.ampayne2.randomevents.events.mob.MobHordeEvent;
import me.ampayne2.randomevents.events.player.AnvilFallEvent;
import me.ampayne2.randomevents.events.player.SoundEvent;
import me.ampayne2.randomevents.events.world.ExpBottleEvent;
import me.ampayne2.randomevents.events.player.FoodCookEvent;
import me.ampayne2.randomevents.events.player.OreSmeltEvent;
import me.ampayne2.randomevents.events.weather.GeomagneticReversalEvent;
import me.ampayne2.randomevents.events.weather.SolarFlareEvent;
import me.ampayne2.randomevents.events.world.FireworksEvent;
import me.ampayne2.randomevents.events.world.PvPEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Default events that come with the RandomEvents plugin.
 */
public enum DefaultEvent {
    // Location Events
    EXP_BOTTLE(new ExpBottleEvent()),
    FIREWORKS(new FireworksEvent()),
    MOB_HORDE(new MobHordeEvent()),

    // Player Events
    ANVIL_FALL(new AnvilFallEvent()),
    FOOD_COOK(new FoodCookEvent()),
    ORE_SMELT(new OreSmeltEvent()),

    // World Events
    CREEPER_EXPLODE(new CreeperExplodeEvent()),
    DINNERBONE(new DinnerboneEvent()),
    FRIGHTEN_SHEEP(new FrightenSheepEvent()),
    GEOMAGNETIC_REVERSAL(new GeomagneticReversalEvent()),
    SOLAR_FLARE(new SolarFlareEvent()),
    SOUND(new SoundEvent()),

    // Minigame Events
    PVP(new PvPEvent());

    private final RandomEvent event;

    private DefaultEvent(RandomEvent event) {
        this.event = event;
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.api.RandomEvent} instance of the DefaultEvent.
     *
     * @return The {@link me.ampayne2.randomevents.api.RandomEvent} instance.
     */
    public RandomEvent getEvent() {
        return event;
    }

    private static final Map<String, DefaultEvent> NAME_MAP = new HashMap<>();

    public static DefaultEvent byName(String name) {
        return NAME_MAP.get(name.toLowerCase());
    }

    static {
        for (DefaultEvent defaultEvent : DefaultEvent.class.getEnumConstants()) {
            NAME_MAP.put(defaultEvent.getEvent().getName().toLowerCase(), defaultEvent);
        }
    }
}
