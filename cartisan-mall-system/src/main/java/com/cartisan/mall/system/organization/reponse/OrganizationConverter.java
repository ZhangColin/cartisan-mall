package com.cartisan.mall.system.organization.reponse;

import com.cartisan.dto.Converter;
import com.cartisan.mall.system.organization.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface OrganizationConverter extends Converter<Organization, OrganizationDto> {
    OrganizationConverter CONVERTER = Mappers.getMapper(OrganizationConverter.class);

}
