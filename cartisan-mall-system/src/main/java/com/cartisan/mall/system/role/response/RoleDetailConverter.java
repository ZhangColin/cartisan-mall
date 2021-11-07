package com.cartisan.mall.system.role.response;

import com.cartisan.dto.Converter;
import com.cartisan.mall.system.role.domain.Role;
import com.cartisan.mall.system.role.domain.RoleMenu;
import com.cartisan.mall.system.role.domain.RoleResource;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author colin
 */
@Mapper
public interface RoleDetailConverter extends Converter<Role, RoleDetailDto> {
    RoleDetailConverter CONVERTER = Mappers.getMapper(RoleDetailConverter.class);

    @Override
    @InheritConfiguration
    @Mapping(source = "menus", target = "menuIds")
    @Mapping(source = "resources", target = "resourceIds")
    RoleDetailDto convert(Role role);

    default String menuId2String(RoleMenu roleMenu) {
        return roleMenu.getMenuId().toString();
    }

    default String resourceId2String(RoleResource roleResource) {
        return roleResource.getResourceId().toString();
    }
}