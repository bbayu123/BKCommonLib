package com.bergerkiller.bukkit.common.map.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import com.bergerkiller.bukkit.common.map.util.BlockModelState;
import com.bergerkiller.bukkit.common.wrappers.BlockData;
import com.bergerkiller.bukkit.common.wrappers.BlockDataRegistry;
import com.bergerkiller.bukkit.common.wrappers.BlockRenderOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ConditionalDeserializer implements JsonDeserializer<BlockModelState.Condition> {

    private static BlockModelState.Condition createSelfCondition(String key, String value) {
        String[] parts = value.split("\\|");
        if (parts.length > 1) {
            // Multiple conditions or'd together
            BlockModelState.Condition orCondition = new BlockModelState.Condition();
            orCondition.mode = BlockModelState.Condition.Mode.OR;
            orCondition.conditions = new ArrayList<>(parts.length);
            for (String part : parts) {
                BlockModelState.Condition condition = new BlockModelState.Condition();
                condition.mode = BlockModelState.Condition.Mode.SELF;
                condition.conditions = Collections.emptyList();
                condition.key = key;
                condition.value = part;
                orCondition.conditions.add(condition);
            }
            return orCondition;
        } else {
            // Single condition
            BlockModelState.Condition condition = new BlockModelState.Condition();
            condition.mode = BlockModelState.Condition.Mode.SELF;
            condition.conditions = Collections.emptyList();
            condition.key = key;
            condition.value = value;
            return condition;
        }
    }

    @Override
    public BlockModelState.Condition deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        BlockModelState.Condition result = new BlockModelState.Condition();
        result.mode = BlockModelState.Condition.Mode.AND;
        result.conditions = new ArrayList<>(1);

        if (jsonElement.isJsonPrimitive()) {
            // Options stored in a single String token
            String options_str = jsonElement.getAsString();

            // If empty, it is actually a normal distribution selector (1.13)
            if (options_str.isEmpty()) {
                options_str = "normal";
            }

            // Parse
            Map<String, String> options = new BlockRenderOptions(BlockDataRegistry.AIR, options_str);
            for (Map.Entry<String, String> option : options.entrySet()) {
                result.conditions.add(createSelfCondition(option.getKey(), option.getValue()));
            }
        } else {
            // Handle operator types in the condition structure
            JsonObject obj = jsonElement.getAsJsonObject();
            for (Map.Entry<String,JsonElement> entry : obj.entrySet()) {
                // Start a new sub-condition, defaulting to mode SELF
                BlockModelState.Condition subCondition = new BlockModelState.Condition();
                subCondition.mode = BlockModelState.Condition.Mode.SELF;
                for (BlockModelState.Condition.Mode mode : BlockModelState.Condition.Mode.values()) {
                    if (entry.getKey().equals(mode.name())) {
                        subCondition.mode = mode;
                        break;
                    }
                }

                if (subCondition.mode == BlockModelState.Condition.Mode.SELF) {
                    // Self: store key:value pair
                    subCondition = createSelfCondition(entry.getKey(), entry.getValue().getAsString());
                } else {
                    // Create a sub-tree with this operator mode
                    if (entry.getValue().isJsonArray()) {
                        // Array of Object conditions
                        JsonArray condArr = entry.getValue().getAsJsonArray();
                        subCondition.conditions = new ArrayList<>(condArr.size());
                        for (JsonElement condElem : condArr) {
                            subCondition.conditions.add(deserialize(condElem, type, jsonDeserializationContext));
                        }
                    } else {
                        // Single Object condition
                        subCondition.conditions = Arrays.asList(deserialize(entry.getValue(), type, jsonDeserializationContext));
                    }
                }
                result.conditions.add(subCondition);
            }
        }

        // Simplify when 0/1 elements
        switch (result.conditions.size()) {
        case 0:
            result.mode = BlockModelState.Condition.Mode.SELF;
            result.conditions = Collections.emptyList();
            result.key = "normal";
            result.value = "";
            return result;
        case 1:
            return result.conditions.get(0);
        default:
            return result;
        }
    }
    
}
