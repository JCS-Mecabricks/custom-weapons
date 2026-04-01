package github.jcsmecabricks.customweapons.entity;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<HatchetProjectileEntity> HATCHET = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "hatchet"),
            EntityType.Builder.<HatchetProjectileEntity>of(HatchetProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 1.15f).build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "hatchet"))));

    public static final EntityType<ElephantEntity> ELEPHANT = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "elephant"),
            EntityType.Builder.of(ElephantEntity::new, MobCategory.CREATURE)
                    .sized(4f, 3.5f).build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "elephant"))));

    public static void registerModEntities() {
    }
}
