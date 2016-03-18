package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740 on 18/02/2016
 */
public class EffVillagerProfession extends Effect{
    private int prof;
    private Expression<Location> loca;
    private Expression<Entity> entity;

    @Override
    protected void execute(Event arg0) {
        Location loc = loca.getSingle(arg0);
        Villager.Profession[] s = Villager.Profession.values();
        if (entity.toString().contains("zombie")){
            Zombie zom = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            zom.setVillagerProfession(s[prof]);
        }else if (entity.toString().contains("villager")){
            Villager vil = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            vil.setProfession(s[prof]);
        }else{
            skUtilities.prEW("Only (Villager & Zombie Villager) are supported!", getClass().getSimpleName(), 0);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        prof = arg3.mark;
        entity = (Expression<Entity>) arg0[0];
        loca = (Expression<Location>) arg0[1];
        return true;
    }
    @Override
    public String toString(Event event, boolean b) {
        return getClass().getName();
    }
}
