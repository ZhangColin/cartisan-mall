package com.cartisan.mall.system.resource;

import com.cartisan.mall.system.resource.domain.Resource;

public class ResourceFixture {
    public static Resource resourceOf() {
        return new Resource("用户管理", "system:user", "/system/users/**", 1L);
    }
}
