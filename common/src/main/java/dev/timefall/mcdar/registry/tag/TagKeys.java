package dev.timefall.mcdar.registry.tag;

import dev.timefall.mcdar.ModConstants;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class TagKeys {
    public static final TagKey<Item> VILLAGER_ARTIFACT = TagKey.of(RegistryKeys.ITEM, ModConstants.id("villager_artifacts"));
    public static final TagKey<Item> ILLAGER_ARTIFACT = TagKey.of(RegistryKeys.ITEM, ModConstants.id("illager_artifacts"));
    public static final TagKey<Item> VOID_ARTIFACT = TagKey.of(RegistryKeys.ITEM, ModConstants.id("void_artifacts"));
    public static final TagKey<Item> ALL_ARTIFACT = TagKey.of(RegistryKeys.ITEM, ModConstants.id("all_artifacts"));
    public static final TagKey<StatusEffect> IS_VALID_FOR_BEAST_EFFECTS = TagKey.of(RegistryKeys.STATUS_EFFECT, ModConstants.id("is_valid_for_beast_effects"));

}
