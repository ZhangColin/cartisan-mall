package com.cartisan.mall.goods.brand.controller;

import com.cartisan.mall.goods.brand.application.BrandCategoryAppService;
import com.cartisan.mall.goods.brand.request.BuildBrandCategoryRelationCommand;
import com.cartisan.mall.goods.brand.request.CancelBrandCategoryRelationCommand;
import com.cartisan.mall.goods.brand.response.BrandCategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "商品服务：品牌")
@RestController
@RequestMapping("/brandCategories")
@Validated
@Slf4j
public class BrandCategoryController {
    private final BrandCategoryAppService service;

    public BrandCategoryController(BrandCategoryAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "获取品牌的所有分类关联")
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<BrandCategoryDto>> getByBrand(
            @ApiParam(value = "品牌Id") @Valid @NotNull @PathVariable Long brandId) {
        return success(service.getByBrand(brandId));
    }

    @ApiOperation(value = "获取分类的所有品牌关联")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BrandCategoryDto>> getByCategory(
            @ApiParam(value = "分类Id") @Valid @NotNull @PathVariable Long categoryId) {
        return success(service.getByCategory(categoryId));
    }

    @ApiOperation(value = "建立关联")
    @PostMapping("/buildRelation")
    public ResponseEntity<?> buildRelation(
            @ApiParam(value = "建立品牌分类关联命令", required = true)
            @Validated @RequestBody BuildBrandCategoryRelationCommand command) {
        service.buildRelation(command);
        return success();
    }

    @ApiOperation(value = "取消关联")
    @PostMapping("/cancelRelation")
    public ResponseEntity<?> cancelRelation(
            @ApiParam(value = "建立品牌分类关联命令", required = true)
            @Validated @RequestBody CancelBrandCategoryRelationCommand command) {
        service.cancelRelation(command);
        return success();
    }
}
