package chronosacaria.mcdar.api;

import chronosacaria.mcdar.entities.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbilityHelper {

    public static boolean isPetOf(LivingEntity self, LivingEntity owner){
        if (self instanceof GolemKitGolemEntity)
            return ((GolemKitGolemEntity) self)
                    .getSummoner() == owner;
        if (self instanceof TastyBoneWolfEntity)
            return ((TastyBoneWolfEntity) self)
                    .getSummoner() == owner;
        if (self instanceof WonderfulWheatLlamaEntity)
            return ((WonderfulWheatLlamaEntity) self)
                    .getSummoner() == owner;
        if (self instanceof EnchantedGrassRedSheepEntity)
            return ((EnchantedGrassRedSheepEntity) self)
                    .getSummoner() == owner;
        if (self instanceof EnchantedGrassGreenSheepEntity)
            return ((EnchantedGrassGreenSheepEntity) self)
                    .getSummoner() == owner;
        if (self instanceof EnchantedGrassBlueSheepEntity)
            return ((EnchantedGrassBlueSheepEntity) self)
                    .getSummoner() == owner;
        return false;
    }

    private static boolean isVillagerOrIronGolem(LivingEntity nearbyEntity) {
        return (nearbyEntity instanceof VillagerEntity) || (nearbyEntity instanceof IronGolemEntity);
    }

    public static boolean canHealEntity(LivingEntity healer, LivingEntity nearbyEntity){
        return nearbyEntity != healer
                && isAllyOf(healer, nearbyEntity)
                && nearbyEntity.isAlive()
                && healer.canSee(nearbyEntity);
    }

    private static boolean isAllyOf(LivingEntity self, LivingEntity other) {
        return self.isTeammate(other)
                || isPetOf(self, other)
                || isVillagerOrIronGolem(other);
    }

    public static boolean isAoeTarget(LivingEntity self, LivingEntity attacker, LivingEntity center) {
        return self != attacker
                && self.isAlive()
                && !isAllyOf(attacker, self)
                && !isUnaffectedByAoe(self)
                && center.canSee(self);
    }

    private static boolean isUnaffectedByAoe(LivingEntity entity) {
        if (entity instanceof PlayerEntity)
            return ((PlayerEntity) entity).isCreative();
        return false;
    }

    public static final List<ItemStack> SATCHEL_OF_ELIXIRS_LIST = Collections.unmodifiableList(Arrays.asList(
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS),
            PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY)));

    public static final List<Item> SATCHEL_OF_SNACKS_LIST = Collections.unmodifiableList(Arrays.asList(
            Items.APPLE, Items.BREAD, Items.COOKED_SALMON, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON,
            Items.COOKED_COD, Items.COOKED_COD, Items.COOKED_RABBIT, Items.COOKED_CHICKEN, Items.COOKED_BEEF,
            Items.MELON_SLICE, Items.CARROT, Items.GOLDEN_CARROT, Items.GOLDEN_APPLE, Items.BAKED_POTATO));
}
