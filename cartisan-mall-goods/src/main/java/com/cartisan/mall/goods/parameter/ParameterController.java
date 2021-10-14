package com.cartisan.mall.goods.parameter;

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

    @ApiOperation(value = "搜索参数")
    @GetMapping("/search")
    public ResponseEntity<PageResult<ParameterDto>> searchParameters(
            @ApiParam(value = "查询参数") ParameterQuery parameterQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchParameters(parameterQuery, pageable));
    }

    @ApiOperation(value = "获取参数")
    @GetMapping("/{id}")
    public ResponseEntity<ParameterDto> getParameter(@ApiParam(value = "参数Id", required = true) @PathVariable Long id){
        return success(service.getParameter(id));
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
