package com.cartisan.mall.goods.album;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author colin
 */
@Converter
public class ImageItemsConverter implements AttributeConverter<List<String>, String> {
    private final Gson gson = new Gson();
    @Override
    public String convertToDatabaseColumn(List<String> imageItems) {
        return gson.toJson(imageItems);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        if (!StringUtils.hasLength(data)){
            return new ArrayList<>();
        }
        return gson.fromJson(data, ArrayList.class);
    }
}
