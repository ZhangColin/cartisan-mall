package com.cartisan.mall.system.menu;

import com.cartisan.repository.BaseRepository;

import java.util.List;

/**
 * @author colin
 */
public interface MenuRepository extends BaseRepository<Menu, Long> {
    List<Menu> findByIdIn(List<Long> menuIds);
}