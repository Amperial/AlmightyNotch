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

/**
 * AlmightyNotch plugin messages.
 */
public enum Message implements me.ampayne2.amplib.messenger.Message {
    START_EVENTS("Start", "Events started."),
    ALREADY_RUNNING("AlreadyStarted", "Events already started."),
    STOP_EVENTS("Stop", "Events stopped."),
    ALREADY_STOPPED("AlreadyStopped", "Events already stopped."),

    EVENT_TRIGGER("Event.Trigger", "Triggered %s."),
    EVENT_NOT_TRIGGERED("Event.NotTriggered", "Event not triggered."),
    EVENT_NOT_FOUND("Event.NotFound", "Event not found."),

    MOOD_SET("Mood.Set", "Set Almighty Notch's mood to %s."),
    MOOD_NOT_FOUND("Mood.NotFound", "Mood not found."),

    // Mob Events
    CREEPER_EXPLODE_EVENT("Event.CreeperExplode", "I blew up all of the creepers."),
    DINNERBONE_EVENT("Event.Dinnerbone", "Who knew skeleton bunnies would look so creepy.."),
    FRIGHTEN_SHEEP_EVENT("Event.FrightenSheep", "Something must have spooked the sheep! They all jumped out of their skin."),
    MOB_HORDE_EVENT("Event.MobHorde", "I spawned a large horde of mobs near x:%s z:%s. Be careful!"),

    // Player Events
    ANVIL_FALL_EVENT("Event.AnvilFall", "Look out below!"),
    FOOD_COOK_EVENT("Event.FoodCook", "I have cooked your food!"),
    ORE_SMELT_EVENT("Event.OreSmelt", "I have smelted your ore!"),
    PVP_EVENT("Event.PvP", "I will reward the first person to kill another player!"),
    SOUND_EVENT("Event.Sound", "Whoah, what was that? You better check outside."),

    // Weather Events
    GEOMAGNETIC_REVERSAL_EVENT("Event.GeomagneticReversal", "I reversed the earth's poles.. your compasses won't work for a while"),
    SOLAR_FLARE_EVENT("Event.SolarFlare", "Feel the flames of my wrath!"),

    // World Events
    EXP_BOTTLE_EVENT("Event.ExpBottle", "I spawned a large amount of experience near x:%s z:%s!"),
    FIREWORKS_EVENT("Event.Fireworks", "It's like the 4th of July!");

    private String message;
    private final String path;
    private final String defaultMessage;

    private Message(String path, String defaultMessage) {
        this.message = defaultMessage;
        this.path = path;
        this.defaultMessage = defaultMessage;
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
}
