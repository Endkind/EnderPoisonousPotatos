package net.endkind.enderPoisonousPotatos;

import net.endkind.enderCore.platform.papermc.EnderPlugin;
import net.endkind.enderPoisonousPotatos.listener.OnFurnaceSmelt;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmokingRecipe;

public final class EnderPoisonousPotatos extends EnderPlugin {
    private static EnderPoisonousPotatos instance;

    @Override
    public void onPluginEnable() {
        instance = this;

        Bukkit.addRecipe(getFurnaceRecipe());
        Bukkit.addRecipe(getSmokingRecipe());

        registerListener(new OnFurnaceSmelt(config));
    }

    @Override
    public void onPluginDisable() {

    }

    @Override
    public void reload() {

    }

    public static EnderPoisonousPotatos getInstance() {
        return instance;
    }

    private FurnaceRecipe getFurnaceRecipe() {
        NamespacedKey key = new NamespacedKey(this, "poisonous_potato_furnace");
        Material source = Material.BAKED_POTATO;
        ItemStack result = new ItemStack(Material.POISONOUS_POTATO);
        float exp = (float) config.getDouble("experience");
        int cookingTime = config.getInt("cookingTime");

        return new FurnaceRecipe(key, result, source, exp, cookingTime);
    }

    private SmokingRecipe getSmokingRecipe() {
        NamespacedKey key = new NamespacedKey(this, "poisonous_potato_smoking");
        Material source = Material.BAKED_POTATO;
        ItemStack result = new ItemStack(Material.POISONOUS_POTATO);
        float exp = (float) config.getDouble("experience");
        int cookingTime = config.getInt("cookingTime") / 2;

        return new SmokingRecipe(key, result, source, exp, cookingTime);
    }
}
