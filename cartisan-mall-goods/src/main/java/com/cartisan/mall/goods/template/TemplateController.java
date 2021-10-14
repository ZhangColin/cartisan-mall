package com.cartisan.mall.goods.template;

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

    @ApiOperation(value = "获取模板")
    @GetMapping("/{id}")
    public ResponseEntity<TemplateDto> getTemplate(@ApiParam(value = "模板Id", required = true) @PathVariable Long id){
        return success(service.getTemplate(id));
    }

    @ApiOperation(value = "添加模板")
    @PostMapping
    public ResponseEntity<TemplateDto> addTemplate(
            @ApiParam(value = "模板信息", required = true) @Validated @RequestBody TemplateParam templateParam) {
        return success(service.addTemplate(templateParam));
    }

    @ApiOperation(value = "编辑模板")
    @PutMapping("/{id}")
    public ResponseEntity<TemplateDto> editTemplate(
            @ApiParam(value = "模板Id", required = true) @PathVariable Long id,
            @ApiParam(value = "模板信息", required = true) @Validated @RequestBody TemplateParam templateParam) {
        return success(service.editTemplate(id, templateParam));
    }

    @ApiOperation(value = "删除模板")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTemplate(
            @ApiParam(value = "模板Id", required = true) @PathVariable Long id) {
        service.removeTemplate(id);
        return success();
    }
}
