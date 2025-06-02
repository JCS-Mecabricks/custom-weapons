package github.jcsmecabricks.customweapons.entity;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<HatchetProjectileEntity> HATCHET = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(CustomWeapons.MOD_ID, "hatchet"),
            EntityType.Builder.<HatchetProjectileEntity>create(HatchetProjectileEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 1.15f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of(CustomWeapons.MOD_ID, "hatchet"))));

    public static final EntityType<ElephantEntity> ELEPHANT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(CustomWeapons.MOD_ID, "elephant"),
            EntityType.Builder.create(ElephantEntity::new, SpawnGroup.CREATURE)
                    .dimensions(4f, 3.5f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                            Identifier.of(CustomWeapons.MOD_ID, "elephant"))));

    public static void registerModEntities() {
    }
}
