AlmightyNotch
=============

AlmightyNotch is a continuation of my submission for the 2014 ten.java contest.
Theme: What random events can occur in Minecraft?

The AlmightyNotch plugin's main purpose is to trigger random events on the server every few minutes.
Depending on Notch's mood, the events could be rewarding or they could destroy your entire world.

---------------------------------------

Features
--------

- Configure the delay between when events are triggered and more.
- Set the probabilities of an event being triggered, to make certain events more common than others.
- Configurable Events and Messages.
- Increase Notch's happiness by offering items, voting for the server, and more!

---------------------------------------

Built-in Events
---------------

Descriptions and code of the following events can be found in [this package](https://github.com/ampayne2/AlmightyNotch/tree/master/src/main/java/ninja/amp/almightynotch/event)
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
- Clone the repository: `git clone https://github.com/ampayne2/AlmightyNotch`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install and configure plugin - it's ready to go!

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
- Extend the type of event it will be (for example [WorldEvent](https://github.com/ampayne2/AlmightyNotch/blob/master/src/main/java/ninja/amp/almightynotch/event/WorldEvent.java)).
Then implement the methods and add your functionality.
```
public class SolarFlareEvent extends WorldEvent {
    public SolarFlareEvent() {
        super("SolarFlare"); // The name of the event. Will be used to find the settings for the event in the config.
        setProbability(3); // The probability of the event occurring.
        setDescription("Sets all mobs in the world on fire."); // The description of the event.
        setOccurMessage("It's so hot the chickens are laying hard-boiled eggs..."); // The message sent when the event occurs.
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        // The actual event functionality
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.setFireTicks(plugin.getConfig().getInt("Events.SolarFlare.Duration", 5) * 20);
        }
    }
}
```
Events are also able to extend Listener if you need more functionality.

---

Triggering an event
```
yourCustomEvent.getHandler().trigger(randomEvents, yourCustomEvent);
```