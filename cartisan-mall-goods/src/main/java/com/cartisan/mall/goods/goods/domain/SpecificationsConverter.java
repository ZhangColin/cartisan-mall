package com.cartisan.mall.goods.goods.domain;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
public class SpecificationsConverter {
    public List<String> convert(String spuSpecifications) {
        if (StringUtils.isEmpty(spuSpecifications)) {
            return asList("{}");
        }

        final Gson gson = new Gson();
        final JsonObject jsonObject = gson.fromJson(spuSpecifications, JsonObject.class);

        if (jsonObject.keySet().size() == 0) {
            return asList("{}");
        }

        return jsonObject.keySet().stream().map(key -> {
            final ArrayList<JsonElement> items = new ArrayList<>();
            jsonObject.getAsJsonArray(key).forEach(items::add);

            return items.stream().map(item -> {
                final JsonObject targetJson = new JsonObject();
                targetJson.add(key, item);

                return targetJson;
            }).collect(toList());
        }).reduce(asList(new JsonObject()),
                (left, right) -> left.stream().flatMap(leftJson -> right.stream().map(rightJson -> {
            final JsonObject targetJson = new JsonObject();
            leftJson.keySet().forEach(key -> targetJson.add(key, leftJson.get(key)));
            rightJson.keySet().forEach(key -> targetJson.add(key, rightJson.get(key)));

            return targetJson;
        })).collect(toList())).stream().map(gson::toJson).collect(toList());
    }
}
