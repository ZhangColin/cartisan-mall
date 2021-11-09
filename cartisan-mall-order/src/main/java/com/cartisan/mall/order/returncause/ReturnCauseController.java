package com.cartisan.mall.order.returncause;

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

@Api(tags = "订单服务：退货原因表")
@RestController
@RequestMapping("/returnCauses")
@Validated
@Slf4j
public class ReturnCauseController {
    private final ReturnCauseAppService service;

    public ReturnCauseController(ReturnCauseAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索退货原因表")
    @GetMapping("/search")
    public ResponseEntity<PageResult<ReturnCauseDto>> searchReturnCauses(
            @ApiParam(value = "查询参数") ReturnCauseQuery returnCauseQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchReturnCauses(returnCauseQuery, pageable));
    }

    @ApiOperation(value = "获取退货原因表")
    @GetMapping("/{id}")
    public ResponseEntity<ReturnCauseDto> getReturnCause(@ApiParam(value = "退货原因表Id", required = true) @PathVariable Long id){
        return success(service.getReturnCause(id));
    }

    @ApiOperation(value = "添加退货原因表")
    @PostMapping
    public ResponseEntity<ReturnCauseDto> addReturnCause(
            @ApiParam(value = "退货原因表信息", required = true) @Validated @RequestBody ReturnCauseParam returnCauseParam) {
        return success(service.addReturnCause(returnCauseParam));
    }

    @ApiOperation(value = "编辑退货原因表")
    @PutMapping("/{id}")
    public ResponseEntity<ReturnCauseDto> editReturnCause(
            @ApiParam(value = "退货原因表Id", required = true) @PathVariable Long id,
            @ApiParam(value = "退货原因表信息", required = true) @Validated @RequestBody ReturnCauseParam returnCauseParam) {
        return success(service.editReturnCause(id, returnCauseParam));
    }

    @ApiOperation(value = "删除退货原因表")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeReturnCause(
            @ApiParam(value = "退货原因表Id", required = true) @PathVariable Long id) {
        service.removeReturnCause(id);
        return success();
    }
}
