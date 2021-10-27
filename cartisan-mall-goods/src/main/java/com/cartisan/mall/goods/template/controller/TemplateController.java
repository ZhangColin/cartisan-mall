package com.cartisan.mall.goods.template.controller;

import com.cartisan.dp.IdName;
import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.template.application.TemplateAppService;
import com.cartisan.mall.goods.template.request.TemplateParam;
import com.cartisan.mall.goods.template.request.TemplateQuery;
import com.cartisan.mall.goods.template.response.TemplateDto;
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
@Api(tags = "商品服务：模板")
@RestController
@RequestMapping("/templates")
@Validated
@Slf4j
public class TemplateController {
    private final TemplateAppService service;

    public TemplateController(TemplateAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索模板")
    @GetMapping("/search")
    public ResponseEntity<PageResult<TemplateDto>> searchTemplates(
            @ApiParam(value = "查询参数") TemplateQuery templateQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchTemplates(templateQuery, pageable));
    }

    @ApiOperation(value = "获取所有模板")
    @GetMapping
    public ResponseEntity<List<IdName<Long, String>>> getTemplates() {
        return success(service.getTemplates());
    }



    @ApiOperation(value = "添加模板")
    @PostMapping
    public ResponseEntity<?> addTemplate(
            @ApiParam(value = "模板信息", required = true) @Validated @RequestBody TemplateParam templateParam) {
        service.addTemplate(templateParam);
        return success();
    }

    @ApiOperation(value = "编辑模板")
    @PutMapping("/{id}")
    public ResponseEntity<?> editTemplate(
            @ApiParam(value = "模板Id", required = true) @PathVariable Long id,
            @ApiParam(value = "模板信息", required = true) @Validated @RequestBody TemplateParam templateParam) {
        service.editTemplate(id, templateParam);
        return success();
    }

    @ApiOperation(value = "删除模板")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTemplate(
            @ApiParam(value = "模板Id", required = true) @PathVariable Long id) {
        service.removeTemplate(id);
        return success();
    }
}
