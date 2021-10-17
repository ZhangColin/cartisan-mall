package com.cartisan.mall.system.role;

import com.cartisan.repository.BaseRepository;
import com.cartisan.mall.system.role.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleRepository extends BaseRepository<Role, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    Page<Role> findByNameLike(String name, Pageable pageable);

//    List<Role> fin(List<String> roleCodes);
}
