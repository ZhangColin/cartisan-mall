package com.cartisan.mall.goods.template.repository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> imageItems) {
        return String.join(",", imageItems);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        return Arrays.stream(data.split(",")).collect(Collectors.toList());
    }
}
