package org.yunxi.more_module.common.module;

import mekanism.api.gear.ModuleData;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraft.world.item.Rarity;
import org.yunxi.more_module.MoreModule;
import org.yunxi.more_module.common.item.Items;

public class MoreModuleModules {
    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(MoreModule.MODID);

    public static final ModuleRegistryObject<ModuleUndyingUnit> UNDYING_UNIT;

    public static final ModuleRegistryObject<ModuleChaosResistanceUnit> CHAOS_RESISTANCE_UNIT;

    static {
        UNDYING_UNIT = MODULES.register("undying_unit", ModuleUndyingUnit::new, () -> {
            return Items.UNDYING_UNIT_ITEM.asItem();
        }, builder -> {
            return builder.maxStackSize(1).rarity(Rarity.EPIC).noDisable();
        });

        CHAOS_RESISTANCE_UNIT = MODULES.register("chaos_resistance_unit", ModuleChaosResistanceUnit::new, () -> {
            return Items.CHAOS_RESISTANCE_UNIT_ITEM.asItem();
        }, builder -> {
            return builder.maxStackSize(1).rarity(Rarity.EPIC).noDisable();
        }
        );
    }
}
