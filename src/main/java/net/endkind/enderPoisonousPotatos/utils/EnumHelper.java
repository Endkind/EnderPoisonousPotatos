package net.endkind.enderPoisonousPotatos.utils;

import net.endkind.enderPoisonousPotatos.EnderPoisonousPotatos;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class EnumHelper {
    public static Sound getSound(String name) {
        String enumName = name.toUpperCase().replace(".", "_");

        try {
            return Sound.valueOf(enumName);
        } catch (IllegalArgumentException e) {
            EnderPoisonousPotatos.getInstance().getEnderLogger().error("Invalid sound name: " + name);
            return null;
        }
    }

    public static Particle getParticle(String name) {
        String enumName = name.toUpperCase().replace(".", "_");

        try {
            return Particle.valueOf(enumName);
        } catch (IllegalArgumentException e) {
            EnderPoisonousPotatos.getInstance().getEnderLogger().error("Invalid particle name: " + name);
            return null;
        }
    }
}
