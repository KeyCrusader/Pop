package keycrusader.pop.mixin;

import keycrusader.pop.PopConfig;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Particle.class)
public class ParticleMixin {
    @Shadow protected double x;
    @Shadow protected double y;
    @Shadow protected double z;
    @Shadow @Final protected ClientWorld world;

    @Inject(at = @At("HEAD"), method = "markDead")
    private void markDeadMixin(CallbackInfo info) {
        particleRemove((Particle) (Object) this, this.x, this.y, this.z, this.world);
    }

    private static void particleRemove(Particle particle, Double x, Double y, Double z, ClientWorld clientWorld) {
        boolean normal = particle instanceof WaterBubbleParticle;
        boolean column = particle instanceof BubbleColumnUpParticle;
        int randomNumber = (int) (Math.random() * 100 + 1);

        if (normal || column) {
            if (randomNumber < PopConfig.INSTANCE.chanceToPop) {
                if (normal && PopConfig.INSTANCE.splashesPlaySounds || column && PopConfig.INSTANCE.columnsPlaySounds)
                    clientWorld.playSound(x, y, z, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.AMBIENT, 1.0F, 1.0F, false);

                clientWorld.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0, 0, 0);
            }
        }
    }
}
