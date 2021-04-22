package chronosacaria.mcdar.entities;

import chronosacaria.mcdar.init.EntityTypeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class BlastFungusEntity extends Entity {
    private static final TrackedData<Integer> FUSE;
    @Nullable
    public LivingEntity causingEntity;
    public int fuseTimer;

    public BlastFungusEntity(EntityType<? extends BlastFungusEntity> entityType, World world){
        super(entityType, world);
        this.fuseTimer = 80;
        this.inanimate = true;
    }

    public BlastFungusEntity (World world, double x, double y, double z, @Nullable LivingEntity igniter){
        this(EntityTypeRegistry.BLAST_FUNGUS_ENTITY_TYPE, world);
        this.updatePosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(d) * 0.02D, 0.20000000298023224D, -Math.cos(d) * 0.02D);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    protected void initDataTracker(){
        this.dataTracker.startTracking(FUSE, 80);
    }

    protected boolean canClimb(){
        return false;
    }

    public boolean collides(){
        return !this.removed;
    }

    public void tick(){
        if (!this.hasNoGravity()){
            this.setVelocity(this.getVelocity().add(0.0D, -.004D, 0.0D));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98D));
        if (this.onGround){
            this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }

        --this.fuseTimer;
        if (this.fuseTimer <= 0){
            this.remove();
            if (!this.world.isClient){
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.world.isClient){
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D,
                        0.0D);
            }
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F,
                Explosion.DestructionType.NONE);
    }

    protected void writeCustomDataToTag(CompoundTag tag) {
        tag.putShort("Fuse", (short)this.getFuseTimer());
    }

    protected void readCustomDataFromTag(CompoundTag tag) {
        this.setFuse(tag.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 0.15F;
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
        this.fuseTimer = fuse;
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (FUSE.equals(data)) {
            this.fuseTimer = this.getFuse();
        }

    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    public int getFuseTimer() {
        return this.fuseTimer;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    static {
        FUSE = DataTracker.registerData(TntEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
