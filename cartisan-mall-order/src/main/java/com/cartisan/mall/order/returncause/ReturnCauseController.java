package com.cartisan.mall.order.returncause;

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
@Api(tags = "订单服务：退货原因")
@RestController
@RequestMapping("/returnCauses")
@Validated
@Slf4j
public class ReturnCauseController {
    private final ReturnCauseAppService service;

    public ReturnCauseController(ReturnCauseAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "获取所有退货原因")
    @GetMapping
    public ResponseEntity<List<ReturnCauseDto>> searchReturnCauses() {
        return success(service.searchReturnCauses());
    }

    @ApiOperation(value = "获取退货原因")
    @GetMapping("/{id}")
    public ResponseEntity<ReturnCauseDto> getReturnCause(@ApiParam(value = "退货原因Id", required = true) @PathVariable Long id){
        return success(service.getReturnCause(id));
    }

    @ApiOperation(value = "添加退货原因")
    @PostMapping
    public ResponseEntity<ReturnCauseDto> addReturnCause(
            @ApiParam(value = "退货原因信息", required = true) @Validated @RequestBody ReturnCauseParam returnCauseParam) {
        return success(service.addReturnCause(returnCauseParam));
    }

    @ApiOperation(value = "编辑退货原因")
    @PutMapping("/{id}")
    public ResponseEntity<ReturnCauseDto> editReturnCause(
            @ApiParam(value = "退货原因Id", required = true) @PathVariable Long id,
            @ApiParam(value = "退货原因信息", required = true) @Validated @RequestBody ReturnCauseParam returnCauseParam) {
        return success(service.editReturnCause(id, returnCauseParam));
    }

    @ApiOperation(value = "删除退货原因")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeReturnCause(
            @ApiParam(value = "退货原因Id", required = true) @PathVariable Long id) {
        service.removeReturnCause(id);
        return success();
    }
}
