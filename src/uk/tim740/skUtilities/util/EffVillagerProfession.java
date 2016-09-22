package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740 on 18/02/2016
 */
public class EffVillagerProfession extends Effect {
    private Expression<Location> loca;
    private Expression<Villager.Profession> prof;
    private Expression<Entity> entity;

    @Override
    protected void execute(Event e) {
        Location loc = loca.getSingle(e);
        if (entity.toString().contains("zombie")) {
            Zombie zom = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            zom.setVillagerProfession(prof.getSingle(e));
        } else if (entity.toString().contains("villager")) {
            Villager vil = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            vil.setProfession(prof.getSingle(e));
        } else {
            skUtilities.prSysE("Only (Villager & Zombie Villager) are supported!", getClass().getSimpleName());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        entity = (Expression<Entity>) e[0];
        prof = (Expression<Villager.Profession>) e[1];
        loca = (Expression<Location>) e[2];
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }
}
