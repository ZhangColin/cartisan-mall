package com.cartisan.mall.domains.user;

import com.cartisan.repositories.BaseRepository;

import java.util.Optional;

/**
 * @author colin
 */
public interface UserRepository  extends BaseRepository<User, Long> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

    Optional<User> findByUserName(String userName);
}
