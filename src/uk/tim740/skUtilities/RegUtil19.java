package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import uk.tim740.skUtilities.util.EffVillagerProfession;
import uk.tim740.skUtilities.util.SExprGlideMode;


import javax.annotation.Nullable;

/**
 * Created by tim740 on 12/08/2016
 */
class RegUtil19 {
    static void regU() {
        Skript.registerEffect(EffVillagerProfession.class, "spawn a %entity% with profession %profession% at %location%");
        Skript.registerExpression(SExprGlideMode.class, Boolean.class, ExpressionType.PROPERTY, "glide (state|ability|mode) of %entity%", "%entity%'s glide (state|ability|mode)");

        if (Bukkit.getPluginManager().getPlugin("SkQuery") == null) {
            Classes.registerClass(new ClassInfo<>(Villager.Profession.class, "profession").parser(new Parser<Villager.Profession>() {
                @Override
                @Nullable
                public Villager.Profession parse(String s, ParseContext context) {
                    try {
                        return Villager.Profession.valueOf(s.toUpperCase());
                    } catch (Exception e) {
                        return null;
                    }
                }

                @Override
                public String toString(Villager.Profession vi, int i) {
                    return vi.name().toLowerCase();
                }

                @Override
                public String toVariableNameString(Villager.Profession vi) {
                    return vi.name().toLowerCase();
                }

                @Override
                public String getVariableNamePattern() {
                    return ".+";
                }
            }));
        }

        Skript.registerEvent("CauldronLevelChange", SimpleEvent.class, CauldronLevelChangeEvent.class, "cauldron[ water] level change");
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Integer.class, new Getter<Integer, CauldronLevelChangeEvent>() {
            @Nullable
            @Override
            public Integer get(CauldronLevelChangeEvent e) {
                return e.getNewLevel();
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Player.class, new Getter<Player, CauldronLevelChangeEvent>() {
            @Nullable
            @Override
            public Player get(CauldronLevelChangeEvent e) {
                return ((Player) e.getEntity());
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Entity.class, new Getter<Entity, CauldronLevelChangeEvent>() {
            @Nullable
            @Override
            public Entity get(CauldronLevelChangeEvent e) {
                return e.getEntity();
            }
        }, 0);

        Skript.registerEvent("GlideToggle", SimpleEvent.class, EntityToggleGlideEvent.class, "[elytra ]glide toggle");
    }
}
