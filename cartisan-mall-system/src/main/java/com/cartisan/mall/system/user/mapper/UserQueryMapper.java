package com.cartisan.mall.system.user.mapper;


import com.cartisan.mall.system.menu.MenuDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author colin
 */
public interface UserQueryMapper {
    @Select("select resource.code\n" +
            "        from sys_resources as resource\n" +
            "          inner join sys_role_resources as roleResource on resource.id = roleResource.resource_id\n" +
            "          inner join sys_roles as role on role.id = roleResource.role_id and role.status = 1\n" +
            "          inner join sys_user_roles as userRole on role.id = userRole.role_id and userRole.user_id = ${userId}")
    List<String> getUserAuthorities(@Param(value = "userId") Long userId);

    @Select("select menu.id, menu.parent_id as parentId, menu.title, menu.`name`, menu.icon, menu.hidden, menu.sort\n" +
            "    from sys_menus as menu\n" +
            "           inner join sys_role_menus as roleMenu on menu.id = roleMenu.menu_id\n" +
            "           inner join sys_roles as role on role.id = roleMenu.role_id and role.status = 1\n" +
            "           inner join sys_user_roles as userRole on role.id = userRole.role_id and userRole.user_id = ${userId}")
    List<MenuDto> getUserMenus(@Param(value = "userId") Long userId);
}
