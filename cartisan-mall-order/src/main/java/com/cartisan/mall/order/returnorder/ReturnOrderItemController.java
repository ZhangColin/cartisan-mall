package com.cartisan.mall.order.returnorder;

import com.cartisan.dto.PageResult;
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

@Api(tags = "订单服务：退货退款订单明细")
@RestController
@RequestMapping("/returnOrderItems")
@Validated
@Slf4j
public class ReturnOrderItemController {
    private final ReturnOrderItemAppService service;

    public ReturnOrderItemController(ReturnOrderItemAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索退货退款订单明细")
    @GetMapping("/search")
    public ResponseEntity<PageResult<ReturnOrderItemDto>> searchReturnOrderItems(
            @ApiParam(value = "查询参数") ReturnOrderItemQuery returnOrderItemQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchReturnOrderItems(returnOrderItemQuery, pageable));
    }

    @ApiOperation(value = "获取退货退款订单明细")
    @GetMapping("/{id}")
    public ResponseEntity<ReturnOrderItemDto> getReturnOrderItem(@ApiParam(value = "退货退款订单明细Id", required = true) @PathVariable Long id){
        return success(service.getReturnOrderItem(id));
    }

    @ApiOperation(value = "添加退货退款订单明细")
    @PostMapping
    public ResponseEntity<ReturnOrderItemDto> addReturnOrderItem(
            @ApiParam(value = "退货退款订单明细信息", required = true) @Validated @RequestBody ReturnOrderItemParam returnOrderItemParam) {
        return success(service.addReturnOrderItem(returnOrderItemParam));
    }

    @ApiOperation(value = "编辑退货退款订单明细")
    @PutMapping("/{id}")
    public ResponseEntity<ReturnOrderItemDto> editReturnOrderItem(
            @ApiParam(value = "退货退款订单明细Id", required = true) @PathVariable Long id,
            @ApiParam(value = "退货退款订单明细信息", required = true) @Validated @RequestBody ReturnOrderItemParam returnOrderItemParam) {
        return success(service.editReturnOrderItem(id, returnOrderItemParam));
    }

    @ApiOperation(value = "删除退货退款订单明细")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeReturnOrderItem(
            @ApiParam(value = "退货退款订单明细Id", required = true) @PathVariable Long id) {
        service.removeReturnOrderItem(id);
        return success();
    }
}
