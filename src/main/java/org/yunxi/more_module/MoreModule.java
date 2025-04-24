package org.yunxi.more_module;

import com.mojang.logging.LogUtils;
import mekanism.api.MekanismIMC;
import mekanism.api.providers.IItemProvider;
import mekanism.common.registries.MekanismCreativeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.yunxi.more_module.common.item.Items;
import org.yunxi.more_module.common.module.MoreModuleModules;

@SuppressWarnings("removal")
@Mod(MoreModule.MODID)
public class MoreModule {

    public static final String MODID = "more_module";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoreModule() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onInterModEnqueue);
        modEventBus.addListener(this::onBuildCreativeModeTabContents);
        MoreModuleModules.MODULES.createAndRegister(modEventBus);
        Items.ITEMS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    public void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MekanismCreativeTabs.MEKANISM.get()) {
            for (IItemProvider allItem : Items.ITEMS.getAllItems()) {
                event.accept(allItem);
            }
        }
    }

    public void onInterModEnqueue(InterModEnqueueEvent event) {
        String modid = "mekanism";
        InterModComms.sendTo(modid, MekanismIMC.ADD_MEKA_SUIT_BODYARMOR_MODULES, MoreModuleModules.UNDYING_UNIT);
        InterModComms.sendTo(modid, MekanismIMC.ADD_MEKA_SUIT_HELMET_MODULES, MoreModuleModules.CHAOS_RESISTANCE_UNIT);
        InterModComms.sendTo(modid, MekanismIMC.ADD_MEKA_SUIT_BODYARMOR_MODULES, MoreModuleModules.CHAOS_RESISTANCE_UNIT);
        InterModComms.sendTo(modid, MekanismIMC.ADD_MEKA_SUIT_PANTS_MODULES, MoreModuleModules.CHAOS_RESISTANCE_UNIT);
        InterModComms.sendTo(modid, MekanismIMC.ADD_MEKA_SUIT_BOOTS_MODULES, MoreModuleModules.CHAOS_RESISTANCE_UNIT);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
