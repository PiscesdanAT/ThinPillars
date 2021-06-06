package com.piscesdan.thinpillars.util;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

import static com.piscesdan.thinpillars.ThinPillars.MODID;

public class RecipeCondition implements ICondition
{
    public static final ResourceLocation MOD_FLAG = new ResourceLocation(MODID, "mod_enabled");

    private final ResourceLocation res;
    private final String flag;

    public RecipeCondition(String flag, ResourceLocation loc) {
        this.flag = flag;
        this.res = loc;
    }

    @Override
    public ResourceLocation getID() {
        return res;
    }

    @Override
    public boolean test() {

        return ModList.get().isLoaded(flag);

    }

    public static class Serializer implements IConditionSerializer<RecipeCondition> {
        private final ResourceLocation location;

        public Serializer(ResourceLocation location) {
            this.location = location;
        }

        @Override
        public void write(JsonObject json, RecipeCondition value) {
            json.addProperty("mod_enabled", value.flag);
        }

        @Override
        public RecipeCondition read(JsonObject json) {
            return new RecipeCondition(json.getAsJsonPrimitive("flag").getAsString(), location);
        }

        @Override
        public ResourceLocation getID() {
            return location;
        }
    }
}
