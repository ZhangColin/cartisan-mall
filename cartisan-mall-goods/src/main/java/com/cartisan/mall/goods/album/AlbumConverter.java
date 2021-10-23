package com.cartisan.mall.goods.album;

import com.cartisan.dto.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface AlbumConverter extends Converter<Album, AlbumDto> {
    AlbumConverter CONVERTER = Mappers.getMapper(AlbumConverter.class);
}
