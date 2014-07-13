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
package me.ampayne2.randomevents.messaging;

/**
 * RandomEvents plugin messages.
 */
public enum Message {
    PREFIX("Prefix", "&9[&7RandomEvents&9]&7 "),
    RELOAD("Reload", "Reloaded RandomEvents."),

    COMMAND_USAGE("CommandUsage", "&4Usage: %s"),

    START_EVENTS("Start", "Events started."),
    ALREADY_RUNNING("AlreadyStarted", "Events already started."),
    STOP_EVENTS("Stop", "Events stopped."),
    ALREADY_STOPPED("AlreadyStopped", "Events already stopped."),

    EVENT_TRIGGER("Event.Trigger", "Triggered %s."),
    EVENT_NOT_FOUND("Event.NotFound", "Event not found.");

    private String message;
    private final String path;
    private final String defaultMessage;

    private Message(String path, String defaultMessage) {
        this.message = defaultMessage;
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getDefault() {
        return defaultMessage;
    }

    public String toString() {
        return message;
    }
}
