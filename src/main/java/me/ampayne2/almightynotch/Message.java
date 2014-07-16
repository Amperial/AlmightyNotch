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
    EVENT_NOT_FOUND("Event.NotFound", "Event not found.");

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
