package com.cartisan.mall.goods.template.controller;

import com.cartisan.mall.goods.template.application.SpecificationAppService;
import com.cartisan.mall.goods.template.request.SpecificationParam;
import com.cartisan.mall.goods.template.response.SpecificationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
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

    @ApiOperation(value = "获取模板下所有的规格")
    @GetMapping()
    public ResponseEntity<List<SpecificationDto>> getSpecificationsByTemplateId(
            @ApiParam(value = "模板Id") @RequestParam Long templateId) {
        return success(service.getSpecificationsByTemplateId(templateId));
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
