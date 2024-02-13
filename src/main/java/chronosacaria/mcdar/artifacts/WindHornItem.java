package chronosacaria.mcdar.artifacts;

import chronosacaria.mcdar.Mcdar;
import chronosacaria.mcdar.api.AOEHelper;
import chronosacaria.mcdar.api.AbilityHelper;
import chronosacaria.mcdar.api.CleanlinessHelper;
import chronosacaria.mcdar.api.McdarEnchantmentHelper;
import chronosacaria.mcdar.enums.DefensiveArtifactID;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Instrument;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WindHornItem extends ArtifactDefensiveItem{

    private static final String INSTRUMENT_KEY = "instrument";
    private final TagKey<Instrument> instrumentTag;


    public WindHornItem(TagKey<Instrument> instrumentTag) {
        super(
                DefensiveArtifactID.WIND_HORN,
                Mcdar.CONFIG.mcdarArtifactsStatsConfig.DEFENSIVE_ARTIFACT_STATS
                        .get(DefensiveArtifactID.WIND_HORN).mcdar$getDurability()
        );
        this.instrumentTag = instrumentTag;
    }

    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        Optional<? extends RegistryEntry<Instrument>> optional = this.getInstrument(itemStack);
        if (optional.isPresent()) {
            Instrument instrument = optional.get().value();
            user.setCurrentHand(hand);
            WindHornItem.playSound(world, user, instrument);
            for (LivingEntity nearbyEntity : AOEHelper.getEntitiesByPredicate(user, 5,
                    (nearbyEntity) -> nearbyEntity != user && !AbilityHelper.isPetOf(nearbyEntity, user) && nearbyEntity.isAlive())) {
                AOEHelper.knockbackNearbyEnemies(user, nearbyEntity, 2.0F);
            }

            if (!user.isCreative())
                itemStack.damage(1, user, (entity) -> entity.sendToolBreakStatus(hand));

            McdarEnchantmentHelper.mcdar$cooldownHelper(
                    user,
                    this
            );
            return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);

        }
        return new TypedActionResult<>(ActionResult.FAIL, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        CleanlinessHelper.createLoreTTips(stack, tooltip);
    }

    private Optional<? extends RegistryEntry<Instrument>> getInstrument(ItemStack stack) {
        Identifier identifier;
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && nbtCompound.contains(INSTRUMENT_KEY, NbtElement.STRING_TYPE) && (identifier = Identifier.tryParse(nbtCompound.getString(INSTRUMENT_KEY))) != null) {
            return Registries.INSTRUMENT.getEntry(RegistryKey.of(RegistryKeys.INSTRUMENT, identifier));
        }
        Iterator<RegistryEntry<Instrument>> iterator = Registries.INSTRUMENT.iterateEntries(this.instrumentTag).iterator();
        if (iterator.hasNext()) {
            return Optional.of(iterator.next());
        }
        return Optional.empty();
    }

    private static void playSound(World world, PlayerEntity player, Instrument instrument) {
        SoundEvent soundEvent = instrument.soundEvent().value();
        float f = instrument.range() / 16.0f;
        world.playSoundFromEntity(player, player, soundEvent, SoundCategory.RECORDS, f, 1.0f);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }
}
