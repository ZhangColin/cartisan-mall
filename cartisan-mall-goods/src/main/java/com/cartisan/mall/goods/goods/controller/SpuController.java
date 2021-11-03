package com.cartisan.mall.goods.goods.controller;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.goods.application.SpuAppService;
import com.cartisan.mall.goods.goods.request.SpuParam;
import com.cartisan.mall.goods.goods.request.SpuQuery;
import com.cartisan.mall.goods.goods.response.SpuDetailDto;
import com.cartisan.mall.goods.goods.response.SpuDto;
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
@Api(tags = "商品服务：SPU")
@RestController
@RequestMapping("/spus")
@Validated
@Slf4j
public class SpuController {
    private final SpuAppService service;

    public SpuController(SpuAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索SPU")
    @GetMapping("/search")
    public ResponseEntity<PageResult<SpuDto>> searchSpus(
            @ApiParam(value = "查询参数") SpuQuery spuQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchSpus(spuQuery, pageable));
    }

    @ApiOperation(value = "获取SPU")
    @GetMapping("/{id}")
    public ResponseEntity<SpuDetailDto> getSpu(@ApiParam(value = "SPUId", required = true) @PathVariable Long id){
        return success(service.getSpu(id));
    }

    @ApiOperation(value = "添加SPU")
    @PostMapping
    public ResponseEntity<SpuDetailDto> addSpu(
            @ApiParam(value = "SPU信息", required = true) @Validated @RequestBody SpuParam spuParam) {
        return success(service.addSpu(spuParam));
    }

    @ApiOperation(value = "编辑SPU")
    @PutMapping("/{id}")
    public ResponseEntity<SpuDetailDto> editSpu(
            @ApiParam(value = "SPUId", required = true) @PathVariable Long id,
            @ApiParam(value = "SPU信息", required = true) @Validated @RequestBody SpuParam spuParam) {
        return success(service.editSpu(id, spuParam));
    }

    @ApiOperation(value = "删除SPU")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSpu(
            @ApiParam(value = "SPUId", required = true) @PathVariable Long id) {
        service.removeSpu(id);
        return success();
    }
}
