package com.cartisan.mall.goods.specification;

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

@Api(tags = "商品服务：规格")
@RestController
@RequestMapping("/specifications")
@Validated
@Slf4j
public class SpecificationController {
    private final SpecificationAppService service;

    public SpecificationController(SpecificationAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索规格")
    @GetMapping("/search")
    public ResponseEntity<PageResult<SpecificationDto>> searchSpecifications(
            @ApiParam(value = "查询参数") SpecificationQuery specificationQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchSpecifications(specificationQuery, pageable));
    }

    @ApiOperation(value = "获取规格")
    @GetMapping("/{id}")
    public ResponseEntity<SpecificationDto> getSpecification(@ApiParam(value = "规格Id", required = true) @PathVariable Long id){
        return success(service.getSpecification(id));
    }

    @ApiOperation(value = "添加规格")
    @PostMapping
    public ResponseEntity<SpecificationDto> addSpecification(
            @ApiParam(value = "规格信息", required = true) @Validated @RequestBody SpecificationParam specificationParam) {
        return success(service.addSpecification(specificationParam));
    }

    @ApiOperation(value = "编辑规格")
    @PutMapping("/{id}")
    public ResponseEntity<SpecificationDto> editSpecification(
            @ApiParam(value = "规格Id", required = true) @PathVariable Long id,
            @ApiParam(value = "规格信息", required = true) @Validated @RequestBody SpecificationParam specificationParam) {
        return success(service.editSpecification(id, specificationParam));
    }

    @ApiOperation(value = "删除规格")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSpecification(
            @ApiParam(value = "规格Id", required = true) @PathVariable Long id) {
        service.removeSpecification(id);
        return success();
    }
}
