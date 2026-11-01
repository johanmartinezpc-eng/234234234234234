package com.anclaje.mixin;

import com.anclaje.Grapple;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public abstract class FishingHookMixin {

    /**
     * Con cualquier entidad enganchada (mob u objeto, endermite incluido) al
     * recoger la caña: en vez de jalar a la entidad, impulsa al jugador hacia
     * ella. Como vanilla, el gancho se suelta después.
     */
    @Inject(method = "pullEntity", at = @At("HEAD"), cancellable = true)
    private void anclaje$pullPlayer(Entity hooked, CallbackInfo ci) {
        ci.cancel(); // anula el comportamiento original (jalar al mob)
        FishingHook self = (FishingHook) (Object) this;
        if (self.getPlayerOwner() instanceof ServerPlayer player) {
            Grapple.pullPlayer(player, hooked);
        }
    }

    /**
     * Silverfish: en cuanto el anzuelo lo engancha, la caña se recoge sola
     * y el jugador sale disparado hacia arriba (gran salto).
     */
    @Inject(method = "onHitEntity", at = @At("TAIL"))
    private void anclaje$silverfishJump(EntityHitResult result, CallbackInfo ci) {
        FishingHook self = (FishingHook) (Object) this;
        if (Grapple.isSilverfish(result.getEntity())
                && self.getPlayerOwner() instanceof ServerPlayer player) {
            Grapple.launchUp(player);
            self.discard(); // la caña se recoge al instante
        }
    }
}
