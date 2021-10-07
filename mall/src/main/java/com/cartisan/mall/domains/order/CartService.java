package com.cartisan.mall.domains.order;

/**
 * @author colin
 */
public interface CartService {
    CartDTO add(AddCartCommand command);

    CartDTO list(Long userId);

    CartDTO update(UpdateCartCommand command);

    CartDTO delete(DeleteCartCommand command);

    CartDTO selectAll(Long userId);

    CartDTO unSelectAll(Long userId);

    Integer sum(Long userId);
}
