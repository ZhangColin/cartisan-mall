package com.cartisan.mall.goods.goods.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.goods.goods.domain.SpuAudit;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface SpuAuditConverter extends Converter<SpuAudit, SpuAuditDto> {
    SpuAuditConverter CONVERTER = Mappers.getMapper(SpuAuditConverter.class);

    @Override
    @InheritConfiguration
    @Mapping(source = "createDateTime", target = "auditDate")
    SpuAuditDto convert(SpuAudit spuAudit);
}
