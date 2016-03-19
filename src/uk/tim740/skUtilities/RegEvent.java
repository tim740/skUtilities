package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 13/03/2016
 */
class RegEvent {
    static void regE(){
        Skript.registerEvent("CauldronLevelChange", SimpleEvent.class, CauldronLevelChangeEvent.class, "cauldron[ water] level change");
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Integer.class, new Getter<Integer,CauldronLevelChangeEvent>() {
            @Nullable
            @Override
            public Integer get(CauldronLevelChangeEvent e) {
                return e.getNewLevel();
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Player.class, new Getter<Player, CauldronLevelChangeEvent>(){
            @Nullable
            @Override
            public Player get(CauldronLevelChangeEvent e) {
                return ((Player) e.getEntity());
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Entity.class, new Getter<Entity, CauldronLevelChangeEvent>(){
            @Nullable
            @Override
            public Entity get(CauldronLevelChangeEvent e) {
                return e.getEntity();
            }
        }, 0);

        Skript.registerEvent("GlideToggle", SimpleEvent.class, EntityToggleGlideEvent.class, "[elytra ]glide toggle");
    }
}
