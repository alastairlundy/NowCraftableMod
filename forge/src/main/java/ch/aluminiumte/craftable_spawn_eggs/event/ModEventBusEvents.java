package ch.aluminiumte.craftable_spawn_eggs.event;

import ch.aluminiumte.craftable_spawn_eggs.CraftableSpawnEggs;
import ch.aluminiumte.craftable_spawn_eggs.event.loot.SpawnEggFromMobModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = CraftableSpawnEggs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializer(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event){
        event.getRegistry().registerAll(
                new SpawnEggFromMobModifier.Serializer().setRegistryName(
                        new ResourceLocation(CraftableSpawnEggs.MOD_ID, "allay_spawn_egg")
                ),
                new SpawnEggFromMobModifier.Serializer().setRegistryName(
                        new ResourceLocation(CraftableSpawnEggs.MOD_ID, "bat_spawn_egg")
                ),
                new SpawnEggFromMobModifier.Serializer().setRegistryName(
                        new ResourceLocation(CraftableSpawnEggs.MOD_ID, "cave_spider_spawn_egg")
                ),
                new SpawnEggFromMobModifier.Serializer().setRegistryName(
                        new ResourceLocation(CraftableSpawnEggs.MOD_ID, "creeper_spawn_egg")
                )
        );
}
}
