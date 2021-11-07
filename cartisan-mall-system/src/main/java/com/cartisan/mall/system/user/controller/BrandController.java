package com.cartisan.mall.system.user.controller;

import com.cartisan.dp.IdName;
import com.cartisan.mall.system.user.gateway.BrandClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "系统管理：用户")
@RestController
@RequestMapping("/brands")
@Validated
@Slf4j
public class BrandController {
    private final BrandClient client;

    public BrandController(BrandClient client) {
        this.client = client;
    }

    @ApiOperation(value = "获取用户")
    @GetMapping
    public ResponseEntity<List<IdName<Long, String>>> getUser() {
        return success(client.getAllBrands());
    }
}
