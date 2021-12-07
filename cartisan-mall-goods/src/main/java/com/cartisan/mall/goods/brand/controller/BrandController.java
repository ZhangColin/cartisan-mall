package com.cartisan.mall.goods.brand.controller;

import com.cartisan.dp.IdName;
import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.brand.application.BrandAppService;
import com.cartisan.mall.goods.brand.request.BrandParam;
import com.cartisan.mall.goods.brand.request.BrandQuery;
import com.cartisan.mall.goods.brand.response.BrandDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "商品服务：品牌")
@RestController
@RequestMapping("/brands")
@Validated
@Slf4j
public class BrandController {
    private final BrandAppService service;

    public BrandController(BrandAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索品牌")
    @GetMapping("/search")
    public ResponseEntity<PageResult<BrandDto>> searchBrands(
            @ApiParam(value = "查询参数") BrandQuery brandQuery,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return success(service.searchBrands(brandQuery, pageable));
    }

    @ApiOperation(value = "获取所有品牌")
    @GetMapping
    public ResponseEntity<List<IdName<Long, String>>> getBrands(
            @ApiParam(value = "查询参数") BrandQuery brandQuery) {
        return success(service.getBrands(brandQuery));
    }

    @ApiOperation(value = "获取品牌")
    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrand(@ApiParam(value = "品牌Id", required = true) @PathVariable Long id) {
        return success(service.getBrand(id));
    }

    @ApiOperation(value = "添加品牌")
    @PostMapping
    public ResponseEntity<BrandDto> addBrand(
            @ApiParam(value = "品牌信息", required = true) @Validated @RequestBody BrandParam brandParam) {
        return success(service.addBrand(brandParam));
    }

    @ApiOperation(value = "编辑品牌")
    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> editBrand(
            @ApiParam(value = "品牌Id", required = true) @PathVariable Long id,
            @ApiParam(value = "品牌信息", required = true) @Validated @RequestBody BrandParam brandParam) {
        return success(service.editBrand(id, brandParam));
    }

    @ApiOperation(value = "删除品牌")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBrand(
            @ApiParam(value = "品牌Id", required = true) @PathVariable Long id) {
        service.removeBrand(id);
        return success();
    }
}
