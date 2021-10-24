package com.cartisan.mall.goods.template.controller;

import com.cartisan.mall.goods.template.application.ParameterAppService;
import com.cartisan.mall.goods.template.request.ParameterParam;
import com.cartisan.mall.goods.template.response.ParameterDto;
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
@Api(tags = "商品服务：参数")
@RestController
@RequestMapping("/parameters")
@Validated
@Slf4j
public class ParameterController {
    private final ParameterAppService service;

    public ParameterController(ParameterAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "获取模板下所有的参数")
    @GetMapping
    public ResponseEntity<List<ParameterDto>> searchParameters(
            @ApiParam(value = "模板Id") @RequestParam Long templateId) {
        return success(service.getParametersByTemplateId(templateId));
    }

    @ApiOperation(value = "添加参数")
    @PostMapping
    public ResponseEntity<ParameterDto> addParameter(
            @ApiParam(value = "参数信息", required = true) @Validated @RequestBody ParameterParam parameterParam) {
        return success(service.addParameter(parameterParam));
    }

    @ApiOperation(value = "编辑参数")
    @PutMapping("/{id}")
    public ResponseEntity<ParameterDto> editParameter(
            @ApiParam(value = "参数Id", required = true) @PathVariable Long id,
            @ApiParam(value = "参数信息", required = true) @Validated @RequestBody ParameterParam parameterParam) {
        return success(service.editParameter(id, parameterParam));
    }

    @ApiOperation(value = "删除参数")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeParameter(
            @ApiParam(value = "参数Id", required = true) @PathVariable Long id) {
        service.removeParameter(id);
        return success();
    }
}
