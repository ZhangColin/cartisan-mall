package com.cartisan.mall.goods.goods.controller;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.application.SkuAppService;
import com.cartisan.mall.goods.goods.request.SkuParam;
import com.cartisan.mall.goods.goods.request.SkuQuery;
import com.cartisan.mall.goods.goods.response.SkuDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "商品服务：SKU")
@RestController
@RequestMapping("/skus")
@Validated
@Slf4j
public class SkuController {
    private final SkuAppService service;

    public SkuController(SkuAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索SKU")
    @GetMapping("/search")
    public ResponseEntity<PageResult<SkuDto>> searchSkus(
            @ApiParam(value = "查询参数") SkuQuery skuQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchSkus(skuQuery, pageable));
    }

    @ApiOperation(value = "获取SKU")
    @GetMapping("/{id}")
    public ResponseEntity<SkuDto> getSku(@ApiParam(value = "SKUId", required = true) @PathVariable Long id){
        return success(service.getSku(id));
    }

    @ApiOperation(value = "添加SKU")
    @PostMapping
    public ResponseEntity<SkuDto> addSku(
            @ApiParam(value = "SKU信息", required = true) @Validated @RequestBody SkuParam skuParam) {
        return success(service.addSku(skuParam));
    }

    @ApiOperation(value = "编辑SKU")
    @PutMapping("/{id}")
    public ResponseEntity<SkuDto> editSku(
            @ApiParam(value = "SKUId", required = true) @PathVariable Long id,
            @ApiParam(value = "SKU信息", required = true) @Validated @RequestBody SkuParam skuParam) {
        return success(service.editSku(id, skuParam));
    }

    @ApiOperation(value = "删除SKU")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSku(
            @ApiParam(value = "SKUId", required = true) @PathVariable Long id) {
        service.removeSku(id);
        return success();
    }
}
