package com.cartisan.mall.system.user.gateway;

import com.cartisan.dp.IdName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author colin
 */
@FeignClient("cartisan-mall-goods")
public interface BrandClient {
    @GetMapping("/brands")
    List<IdName<Long, String>> getAllBrands();
}
