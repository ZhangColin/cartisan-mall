package com.cartisan.mall.goods.spu;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
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
    public ResponseEntity<SpuDto> getSpu(@ApiParam(value = "SPUId", required = true) @PathVariable Long id){
        return success(service.getSpu(id));
    }

    @ApiOperation(value = "添加SPU")
    @PostMapping
    public ResponseEntity<SpuDto> addSpu(
            @ApiParam(value = "SPU信息", required = true) @Validated @RequestBody SpuParam spuParam) {
        return success(service.addSpu(spuParam));
    }

    @ApiOperation(value = "编辑SPU")
    @PutMapping("/{id}")
    public ResponseEntity<SpuDto> editSpu(
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
