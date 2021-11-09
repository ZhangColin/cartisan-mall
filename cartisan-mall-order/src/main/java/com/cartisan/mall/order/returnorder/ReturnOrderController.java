package com.cartisan.mall.order.returnorder;

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

@Api(tags = "订单服务：退货退款订单")
@RestController
@RequestMapping("/returnOrders")
@Validated
@Slf4j
public class ReturnOrderController {
    private final ReturnOrderAppService service;

    public ReturnOrderController(ReturnOrderAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索退货退款订单")
    @GetMapping("/search")
    public ResponseEntity<PageResult<ReturnOrderDto>> searchReturnOrders(
            @ApiParam(value = "查询参数") ReturnOrderQuery returnOrderQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchReturnOrders(returnOrderQuery, pageable));
    }

    @ApiOperation(value = "获取退货退款订单")
    @GetMapping("/{id}")
    public ResponseEntity<ReturnOrderDto> getReturnOrder(@ApiParam(value = "退货退款订单Id", required = true) @PathVariable Long id){
        return success(service.getReturnOrder(id));
    }

    @ApiOperation(value = "添加退货退款订单")
    @PostMapping
    public ResponseEntity<ReturnOrderDto> addReturnOrder(
            @ApiParam(value = "退货退款订单信息", required = true) @Validated @RequestBody ReturnOrderParam returnOrderParam) {
        return success(service.addReturnOrder(returnOrderParam));
    }

    @ApiOperation(value = "编辑退货退款订单")
    @PutMapping("/{id}")
    public ResponseEntity<ReturnOrderDto> editReturnOrder(
            @ApiParam(value = "退货退款订单Id", required = true) @PathVariable Long id,
            @ApiParam(value = "退货退款订单信息", required = true) @Validated @RequestBody ReturnOrderParam returnOrderParam) {
        return success(service.editReturnOrder(id, returnOrderParam));
    }

    @ApiOperation(value = "删除退货退款订单")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeReturnOrder(
            @ApiParam(value = "退货退款订单Id", required = true) @PathVariable Long id) {
        service.removeReturnOrder(id);
        return success();
    }
}
