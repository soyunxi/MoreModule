package org.yunxi.more_module.common.module;

import mekanism.api.gear.ICustomModule;
import mekanism.api.gear.IModule;
import mekanism.api.gear.IModuleHelper;
import net.minecraft.world.damagesource.DamageSource;
import org.jetbrains.annotations.Nullable;

public class ModuleUndyingUnit implements ICustomModule<ModuleUndyingUnit>{
    public ModuleUndyingUnit() {
    }

    @Nullable
    @Override
    public ModuleDamageAbsorbInfo getDamageAbsorbInfo(IModule<ModuleUndyingUnit> module, DamageSource damageSource) {
        return ICustomModule.super.getDamageAbsorbInfo(module, damageSource);
    }
}
