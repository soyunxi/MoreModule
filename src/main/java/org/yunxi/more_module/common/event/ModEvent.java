package org.yunxi.more_module.common.event;

import mekanism.api.MekanismAPI;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.ModuleData;
import mekanism.api.math.FloatingLong;
import mekanism.common.Mekanism;
import mekanism.common.content.gear.ModuleHelper;
import mekanism.common.integration.energy.IEnergyCompat;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import mekanism.common.service.EnergyConversionHelper;
import mekanism.common.util.UnitDisplayUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.yunxi.more_module.common.module.ModuleUndyingUnit;
import org.yunxi.more_module.common.module.MoreModuleModules;
import net.minecraftforge.energy.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvent {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            ItemStack stack = player.getInventory().armor.get(2);
            if (ModuleHelper.get().isEnabled(stack, MoreModuleModules.UNDYING_UNIT)) {
                if (stack.getItem() instanceof ItemMekaSuitArmor mekaSuitArmor) {
                    IModule<ModuleUndyingUnit> module = mekaSuitArmor.getModule(stack, MoreModuleModules.UNDYING_UNIT);
                    if (module != null) {
                        if (module.canUseEnergy(player, FloatingLong.create(2500000))) {
                            module.useEnergy(player, FloatingLong.create(2500000), true);
                            event.setCanceled(true);
                            player.setHealth(1);
                        }
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player) {
            float damage = 1;
            for (ItemStack stack : player.getInventory().armor) {
                if (ModuleHelper.get().isEnabled(stack, MoreModuleModules.CHAOS_RESISTANCE_UNIT)) damage -= 0.25f;
            }

            if (damage <= 0) {
                event.setCanceled(true);
            } else event.setAmount(event.getAmount() * damage);
        }
    }
}
