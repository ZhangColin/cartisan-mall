package com.cartisan.mall.goods.goods.controller;

import com.cartisan.mall.goods.goods.application.GoodsAppService;
import com.cartisan.mall.goods.goods.request.GoodsParam;
import com.cartisan.mall.goods.goods.response.GoodsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "商品服务：SPU")
@RestController
@RequestMapping("/goods")
@Validated
@Slf4j
public class GoodsController {
    private final GoodsAppService service;

    public GoodsController(GoodsAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "获取Goods")
    @GetMapping("/{spuId}")
    public ResponseEntity<GoodsDto> getGoods(@ApiParam(value = "SPUId", required = true) @PathVariable Long spuId) {
        return success(service.getGoodsBySpuId(spuId));
    }

    @ApiOperation(value = "添加Goods")
    @PostMapping
    public ResponseEntity<?> addGoods(
            @ApiParam(value = "Goods信息", required = true) @Validated @RequestBody GoodsParam goodsParam) {
        service.addGoods(goodsParam);
        return success();
    }

    @ApiOperation(value = "编辑Goods")
    @PutMapping("/{id}")
    public ResponseEntity<?> editGoods(
            @ApiParam(value = "SPUId", required = true) @PathVariable Long id,
            @ApiParam(value = "Goods信息", required = true) @Validated @RequestBody GoodsParam goodsParam) {
        service.editGoods(id, goodsParam);
        return success();
    }
}
