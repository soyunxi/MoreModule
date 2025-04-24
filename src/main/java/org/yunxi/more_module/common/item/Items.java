package org.yunxi.more_module.common.item;

import mekanism.common.item.ItemModule;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.yunxi.more_module.common.module.MoreModuleModules;

import static org.yunxi.more_module.MoreModule.MODID;

public class Items {
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister(MODID);

    public static final ItemRegistryObject<ItemModule> UNDYING_UNIT_ITEM;

    public static final ItemRegistryObject<ItemModule> CHAOS_RESISTANCE_UNIT_ITEM;

    static {
        UNDYING_UNIT_ITEM = ITEMS.registerModule(MoreModuleModules.UNDYING_UNIT);

        CHAOS_RESISTANCE_UNIT_ITEM = ITEMS.registerModule(MoreModuleModules.CHAOS_RESISTANCE_UNIT);
    }
}
