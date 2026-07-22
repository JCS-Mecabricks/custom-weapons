package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.custom.*;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.materials.CustomWeaponsToolMaterials;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;

public class ItemInit {
    private final CompoundTag nbtCompound;
    public static final Item SILVER = register("silver", new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver")))));

    public static final Item SILVER_DETECTOR = register("silver_detector", new SilverDetectorItem(new Item.Properties()
            .durability(600)
            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_detector")))));

    public static final Item SICKLE = register("sickle", new Item(new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "sickle")))));

    public static final SwordItem SICKLES = register("sickles",
            new SwordItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS, 8, -2f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "sickles")))));

    public static final SwordItem SCYTHE = register("scythe",
            new SwordItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS2, 8, -2.5f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "scythe")))));

    public static final Item SILVER_SPEAR = register("silver_spear",
            new Item(new Item.Properties().spear(CustomWeaponsToolMaterials.CUSTOMWEAPONS, 1F, 1F, 0.5F, 2.5F, 7.8F, 6.75F, 5.1F, 11F, 4.6F)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_spear")))));

    public static final HammerItem SILVER_HAMMER = register("silver_hammer",
            new HammerItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS3, 15, -2f, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_hammer")))));

    public static final Item COMPOUND_BOW = registerItem("compound_bow",
            new CompoundBowItem(new Item.Properties().durability(550)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "compound_bow")))
                    .enchantable(2)));

    public static final Item SILVER_PAXEL = registerItem("silver_paxel",
            new PaxelItem(CustomWeaponsToolMaterials.PAXEL, new Item.Properties()
                    .rarity(Rarity.RARE)
                    .setId(ResourceKey.create(Registries.ITEM,
                            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_paxel")))));

    public static final Item SILVER_HELMET = register("silver_helmet",
            new Item( new Item.Properties()
                    .humanoidArmor(ArmorMaterialInit.SILVER, ArmorType.HELMET)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_helmet")))));

    public static final Item SILVER_CHESTPLATE = register("silver_chestplate",
            new Item( new Item.Properties()
                    .humanoidArmor(ArmorMaterialInit.SILVER, ArmorType.CHESTPLATE)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_chestplate")))));

    public static final Item SILVER_LEGGINGS = register("silver_leggings",
            new Item(new Item.Properties()
                    .humanoidArmor(ArmorMaterialInit.SILVER, ArmorType.LEGGINGS)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_leggings")))));

    public static final Item SILVER_BOOTS = register("silver_boots",
            new Item(new Item.Properties()
                    .humanoidArmor(ArmorMaterialInit.SILVER, ArmorType.BOOTS)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_boots")))));

    public static final Item HATCHET = register("hatchet",
            new HatchetItem(new Item.Properties()
                    .stacksTo(16)
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "hatchet")))));

    public static final Item ELEPHANT_SPAWN_EGG = registerSpawnEggItem("elephant_spawn_egg",
            settings -> new SpawnEggItem(settings.component(
                    DataComponents.ENTITY_DATA,
                    TypedEntityData.of(ModEntities.ELEPHANT, new CompoundTag())
            ))
    );

    public static final Item IRON_ELEPHANT_ARMOR = register("iron_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.IRON, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "iron_elephant_armor")))));
    public static final Item SILVER_ELEPHANT_ARMOR = register("silver_elephant_armor",
            new ElephantArmorItem(ArmorMaterialInit.SILVER, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_elephant_armor")))));
    public static final Item GOLD_ELEPHANT_ARMOR = register("gold_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.GOLD, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "gold_elephant_armor")))));
    public static final Item DIAMOND_ELEPHANT_ARMOR = register("diamond_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.DIAMOND, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "diamond_elephant_armor")))));
    public static final Item NETHERITE_ELEPHANT_ARMOR = register("netherite_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.NETHERITE, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "netherite_elephant_armor")))));

    public ItemInit(CompoundTag nbtCompound) {
        this.nbtCompound = nbtCompound;
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name), item);
    }

    private static Item registerSpawnEggItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name)))));
    }

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, CustomWeapons.id(name), item);
    }

    public static void load() {}
}
