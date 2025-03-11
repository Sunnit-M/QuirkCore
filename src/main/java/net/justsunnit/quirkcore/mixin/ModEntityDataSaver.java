package net.justsunnit.quirkcore.mixin;

import net.justsunnit.quirkcore.Util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class ModEntityDataSaver implements IEntityDataSaver {
    private NbtCompound persistentData;

    private static final String DATA_TAG = "quirkcore.data";

    @Override
    public NbtCompound getPersistentData() {
        if(persistentData == null){
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info){
        if(persistentData != null){
            nbt.put(DATA_TAG, persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(DATA_TAG, 10)) {
            persistentData = nbt.getCompound(DATA_TAG);
        }
    }
}
