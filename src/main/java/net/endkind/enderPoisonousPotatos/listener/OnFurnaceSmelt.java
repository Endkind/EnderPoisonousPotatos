package net.endkind.enderPoisonousPotatos.listener;

import net.endkind.enderPoisonousPotatos.utils.EnumHelper;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OnFurnaceSmelt implements Listener {
    private final Random random = new Random();
    private final Double percent;
    private final Sound success_sound;
    private final Sound fail_sound;
    private final Particle success_particle;
    private final Particle fail_particle;

    public OnFurnaceSmelt(FileConfiguration config) {
        String success_sound_name = config.getString("sound.success");
        String failed_sound_name = config.getString("sound.failed");
        String success_particle_name = config.getString("particle.success");
        String failed_particle_name = config.getString("particle.failed");

        if (success_sound_name != null && !(success_sound_name.isEmpty() || success_sound_name.equals("null"))) {
            this.success_sound = EnumHelper.getSound(success_sound_name);
        } else {
            this.success_sound = null;
        }

        if (failed_sound_name != null && !(failed_sound_name.isEmpty() || failed_sound_name.equals("null"))) {
            this.fail_sound = EnumHelper.getSound(failed_sound_name);
        } else {
            this.fail_sound = null;
        }

        if (success_particle_name != null && !(success_particle_name.isEmpty() || success_particle_name.equals("null"))) {
            this.success_particle = EnumHelper.getParticle(success_particle_name);
        } else {
            this.success_particle = null;
        }

        if (failed_particle_name != null && !(failed_particle_name.isEmpty() || failed_particle_name.equals("null"))) {
            this.fail_particle = EnumHelper.getParticle(failed_particle_name);
        } else {
            this.fail_particle = null;
        }

        this.percent = config.getDouble("percent");
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
        ItemStack source = event.getSource();

        if (source.getType() == Material.BAKED_POTATO) {
            if (random.nextDouble() < this.percent) {

                if (this.success_sound != null) {
                    event.getBlock().getWorld().playSound(event.getBlock().getLocation(), this.success_sound, 1.0f, 1.0f);
                }

                if (this.success_particle != null) {
                    event.getBlock().getWorld().spawnParticle(this.success_particle, event.getBlock().getLocation().add(0.5, 1, 0.5), 10);
                }
            } else {
                event.setResult(new ItemStack(Material.AIR));

                if (this.fail_sound != null) {
                    event.getBlock().getWorld().playSound(event.getBlock().getLocation(), this.fail_sound, 1.0f, 1.0f);
                }

                if (this.fail_particle != null) {
                    event.getBlock().getWorld().spawnParticle(this.fail_particle, event.getBlock().getLocation().add(0.5, 1, 0.5), 10);
                }
            }
        }
    }
}
