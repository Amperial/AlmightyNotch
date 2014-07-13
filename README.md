RandomEvents
============

RandomEvents is a continuation of my submission for the 2014 ten.java contest.
Theme: What random events can occur in Minecraft?

The RandomEvents plugin's main purpose is to trigger random events on the server every few minutes.
It includes a wide variety of default events but was designed to be extended - events can be added easily through the API.

---------------------------------------

Features
--------

- Configure the delay between events are triggered and more
- Set the probabilities of an event being triggered, to make certain events more common than others
- Configurable Events and Messages

---------------------------------------

Built-in Events
---------------

Descriptions and code of the following events can be found in [this package](https://github.com/ampayne2/RandomEvents/tree/master/src/main/java/me/ampayne2/randomevents/events)
- Anvil Fall Event
- Creeper Explode Event
- Dinnerbone Event
- Food Cook Event
- Geomagnetic Reversal Event
- Ore Smelt Event
- Solar Flare Event
- Sound Event

Events Coming Soon:
- Acid Rain Event
- Angry Chicken Event
- Blizzard Event
- Buried Treasure Event
- Exp Bottle Event
- Fireworks Event
- Frighten Sheep Event
- Hail Storm Event
- Lightning Storm Event
- Loot Crate Event
- Meteor Event
- Mob Horde Event
- Piranha Event
- PvP Event
- Quicksand Event
- Sinkhole Event
- Tornado Event

Ideas for the future:
- More Minigame Events - First to collect a certain item, First to perform certain actions
- Collapsing Mine Events

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/ampayne2/RandomEvents`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install and configure plugin - it's ready to go!

---------------------------------------

Commands
--------

/randomevents start
- Starts the random events
/randomevents stop
- Stops the random events
/randomevents reload
- Reloads the plugin
/randomevents trigger <event>
- Manually triggers an event

Aliases for /randomevents: /re, /revents

---------------------------------------

API
---

Included with the plugin are four types of events which should be sufficient for anything you want to make:
- Location events, which occur at random locations.
- Player events, which occur to random players.
- World events, which occur to a world.
- Minigame events, which occur to a server and can be completed for a reward.

---

Creating an event
- Extend the type of event it will be (for example [WorldEvent](https://github.com/ampayne2/RandomEvents/blob/master/src/main/java/me/ampayne2/randomevents/api/WorldEvent.java)).
Then implement the methods and add your functionality. Don't forget to broadcast the occur message!
```
public class SolarFlareEvent extends WorldEvent {
    public SolarFlareEvent() {
        super("SolarFlare"); // The name of the event. Will be used to find the settings for the event in the config.
        setProbability(3); // The probability of the event occurring.
        setDescription("Sets all mobs in the world on fire."); // The description of the event.
        setOccurMessage("It's so hot the chickens are laying hard-boiled eggs..."); // The message sent when the event occurs.
    }

    @Override
    public void trigger(RandomEvents plugin, World world) {
        // The actual event functionality
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.setFireTicks(plugin.getConfig().getInt("Events.SolarFlare.Duration", 5) * 20);
        }
        // Broadcasts the occur message
        plugin.getMessenger().broadcastEventMessage(this);
    }
}
```
Events are also able to extend Listener if you need more functionality.

---

Creating a new type of event
- Extend [RandomEvent](https://github.com/ampayne2/RandomEvents/blob/master/src/main/java/me/ampayne2/randomevents/api/RandomEvent.java).
- Extend [EventHandler](https://github.com/ampayne2/RandomEvents/blob/master/src/main/java/me/ampayne2/randomevents/api/handlers/EventHandler.java) for your new type of event - it will take care of triggering the event.
For examples of creating the event type and handler, take a look at some of the built in event types and their handlers.

---

Adding your event to the manager
- Get the RandomEvents plugin instance:
```
Plugin plugin = Bukkit.getPluginManager().getPlugin("RandomEvents");
if (plugin != null && plugin instanceof RandomEvents) {
    RandomEvents randomEvents = (RandomEvents) plugin;
}
```
- Add the event to the manager
```
randomEvents.getEventManager().addEvent(new YourCustomEvent());
```

---

Getting an event by its name
```
randomEvents.getEventManager().getEvent("YourCustomEventName");
```

---

Triggering an event
```
yourCustomEvent.getHandler().trigger(randomEvents, yourCustomEvent);
```