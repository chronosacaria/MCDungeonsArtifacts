package dev.timefall.mcdar.artifacts;

import dev.timefall.mcdar.api.CleanlinessHelper;
import dev.timefall.mcdar.artifacts.artifact_types.ArtifactDefensiveItem;
import dev.timefall.mcdar.config.McdarArtifactsStatsConfig;
import dev.timefall.mcdar.effect.EnchantmentEffects;
import dev.timefall.mcdx.api.AOEHelper;
import dev.timefall.mcdx.api.AbilityHelper;
import dev.timefall.mcdx.configs.McdxCoreConfig;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WindHornItem extends ArtifactDefensiveItem {

    private static final String INSTRUMENT_KEY = "instrument";
    private final TagKey<Instrument> instrumentTag;


    public WindHornItem(TagKey<Instrument> instrumentTag) {
        super(McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().WIND_HORN_STATS);
        this.instrumentTag = instrumentTag;
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        int maxCooldownEnchantmentTime = McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().WIND_HORN_STATS.mcdar$getMaxCooldownEnchantmentTime();
        int modifiedCooldownEnchantmentTime = EnchantmentEffects.cooldownEffect(maxCooldownEnchantmentTime, user, world);

        Optional<? extends RegistryEntry<Instrument>> optional = this.getInstrument(itemStack);
        if (optional.isPresent()) {
            Instrument instrument = optional.get().value();
            user.setCurrentHand(hand);
            WindHornItem.playSound(world, user, instrument);

            AbilityHelper.applyToNearestNTargets(user, Integer.MAX_VALUE, 5f, McdxCoreConfig.INSTANCE.allyExclusions, (nearbyEntity) -> {
                AOEHelper.knockbackNearbyEnemies(user, nearbyEntity, McdarArtifactsStatsConfig.CONFIG.mcdar$getDefensiveArtifactStats().WIND_HORN_STATS.mcdar$getDamage());
            });

            if (!user.isCreative()) {
                EquipmentSlot equipmentSlot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                itemStack.damage(1, user, equipmentSlot);
            }

            EnchantmentEffects.mcdar$cooldownHelper(
                    user,
                    this,
                    modifiedCooldownEnchantmentTime
            );
            return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);

        }
        return new TypedActionResult<>(ActionResult.FAIL, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext tooltipContext, List<Text> tooltip, TooltipType type){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    private Optional<RegistryEntry<Instrument>> getInstrument(ItemStack stack) {
        RegistryEntry<Instrument> registryEntry = stack.get(DataComponentTypes.INSTRUMENT);
        if (registryEntry != null) {
            return Optional.of(registryEntry);
        } else {
            Iterator<RegistryEntry<Instrument>> iterator = Registries.INSTRUMENT.iterateEntries(this.instrumentTag).iterator();
            return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
        }
    }

    private static void playSound(World world, PlayerEntity player, Instrument instrument) {
        SoundEvent soundEvent = instrument.soundEvent().value();
        float f = instrument.range() / 16.0f;
        world.playSoundFromEntity(player, player, soundEvent, SoundCategory.RECORDS, f, 1.0f);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }
}
