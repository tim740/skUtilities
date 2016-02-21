package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Main;

/**
 * Created by tim740 on 18/02/2016
 */
public class EffVillagerProfession extends Effect{
    private int prof;
    private Expression<Location> location;
    private Expression<Entity> entity;

    @Override
    protected void execute(Event arg0) {
        Location loc = this.location.getSingle(arg0);
        Villager.Profession[] s = Villager.Profession.values();
        if (entity.toString().contains("zombie villager")){
/*            Villager zom = (Villager) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            if (zom.isVillager() != true){
                zom.setVillager(true);
            }
            zom.setProfession(s[prof]);*/
        }else if (entity.toString().contains("villager")){
            Villager vil = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
            vil.setProfession(s[prof]);
        }else{
            Main.prErr("Only (Villager & Zombie Villager) are supported!");
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return this.getClass().getName();
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        prof = arg3.mark;
        this.entity = (Expression<Entity>) arg0[0];
        this.location = (Expression<Location>) arg0[1];
        return true;
    }
}
