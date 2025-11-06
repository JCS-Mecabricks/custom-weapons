package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.custom.*;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.materials.CustomWeaponsToolMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.TypedEntityData;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ItemInit {
    private final NbtCompound nbtCompound;
    public static final Item SILVER = register("silver", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver")))));

    public static final Item SILVER_DETECTOR = register("silver_detector", new SilverDetectorItem(new Item.Settings()
            .maxDamage(600)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_detector")))));

    public static final Item SICKLE = register("sickle", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "sickle")))));

    public static final SwordItem SICKLES = register("sickles",
            new SwordItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS, 8, -2f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "sickles")))));

    public static final SwordItem SCYTHE = register("scythe",
            new SwordItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS2, 10, -2.5f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "scythe")))));

    public static final SwordItem SPEAR = register("spear",
            new SwordItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS, 6, -2f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "spear")))));

    public static final HammerItem SILVER_HAMMER = register("silver_hammer",
            new HammerItem(CustomWeaponsToolMaterials.CUSTOMWEAPONS3, 15, -2f, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_hammer")))));

    public static final Item COMPOUND_BOW = registerItem("compound_bow",
            new CompoundBowItem(new Item.Settings().maxDamage(550)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                            Identifier.of(CustomWeapons.MOD_ID, "compound_bow")))
                    .enchantable(2)));

    public static final Item SILVER_PAXEL = registerItem("silver_paxel",
            new PaxelItem(CustomWeaponsToolMaterials.PAXEL, new Item.Settings()
                    .rarity(Rarity.RARE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                            Identifier.of(CustomWeapons.MOD_ID, "silver_paxel")))));

    public static final Item SILVER_HELMET = register("silver_helmet",
            new Item( new Item.Settings()
                    .armor(ArmorMaterialInit.SILVER, EquipmentType.HELMET)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_helmet")))));

    public static final Item SILVER_CHESTPLATE = register("silver_chestplate",
            new Item( new Item.Settings()
                    .armor(ArmorMaterialInit.SILVER, EquipmentType.CHESTPLATE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_chestplate")))));

    public static final Item SILVER_LEGGINGS = register("silver_leggings",
            new Item(new Item.Settings()
                    .armor(ArmorMaterialInit.SILVER, EquipmentType.LEGGINGS)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_leggings")))));

    public static final Item SILVER_BOOTS = register("silver_boots",
            new Item(new Item.Settings()
                    .armor(ArmorMaterialInit.SILVER, EquipmentType.BOOTS)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_boots")))));

    public static final Item HATCHET = register("hatchet",
            new HatchetItem(new Item.Settings()
                    .maxCount(16)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "hatchet")))));

    public static final Item ELEPHANT_SPAWN_EGG = registerSpawnEggItem("elephant_spawn_egg",
            settings -> new SpawnEggItem(settings.component(
                    DataComponentTypes.ENTITY_DATA,
                    TypedEntityData.create(ModEntities.ELEPHANT, new NbtCompound())
            ))
    );

    public static final Item IRON_ELEPHANT_ARMOR = register("iron_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.IRON, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "iron_elephant_armor")))));
    public static final Item SILVER_ELEPHANT_ARMOR = register("silver_elephant_armor",
            new ElephantArmorItem(ArmorMaterialInit.SILVER, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "silver_elephant_armor")))));
    public static final Item GOLD_ELEPHANT_ARMOR = register("gold_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.GOLD, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "gold_elephant_armor")))));
    public static final Item DIAMOND_ELEPHANT_ARMOR = register("diamond_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.DIAMOND, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "diamond_elephant_armor")))));
    public static final Item NETHERITE_ELEPHANT_ARMOR = register("netherite_elephant_armor",
            new ElephantArmorItem(ArmorMaterials.NETHERITE, new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, "netherite_elephant_armor")))));

    public ItemInit(NbtCompound nbtCompound) {
        this.nbtCompound = nbtCompound;
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CustomWeapons.MOD_ID, name), item);
    }

    private static Item registerSpawnEggItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(CustomWeapons.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, name)))));
    }

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, CustomWeapons.id(name), item);
    }

    public static void load() {}
}
