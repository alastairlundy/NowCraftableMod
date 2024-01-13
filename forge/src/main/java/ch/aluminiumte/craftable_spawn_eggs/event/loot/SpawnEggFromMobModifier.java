package ch.aluminiumte.craftable_spawn_eggs.event.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpawnEggFromMobModifier extends LootModifier {

    private final Item addition;

    private final double DROP_CHANCE = 0.02f;

    protected SpawnEggFromMobModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> list, LootContext lootContext) {
         /*
            Drop a spawn egg blood 2% of the time
         */

        int countToDrop = lootContext.getRandom().nextInt(1, 2);

        if(lootContext.getRandom().nextFloat() > DROP_CHANCE){
            list.add(new ItemStack(addition, countToDrop));
        }

        return list;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SpawnEggFromMobModifier> {

        @Override
        public SpawnEggFromMobModifier read(ResourceLocation resourceLocation, JsonObject jsonObject, LootItemCondition[] lootItemConditions) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(jsonObject, "addition")));
            return new SpawnEggFromMobModifier(lootItemConditions, addition);
        }

        @Override
        public JsonObject write(SpawnEggFromMobModifier fishBloodFromFishAdditionModifier) {
            JsonObject json = makeConditions(fishBloodFromFishAdditionModifier.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(fishBloodFromFishAdditionModifier.addition).toString());
            return json;
        }
    }
}
