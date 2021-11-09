package com.cartisan.mall.order.orderitem;

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

@Api(tags = "订单服务：订单明细")
@RestController
@RequestMapping("/orderItems")
@Validated
@Slf4j
public class OrderItemController {
    private final OrderItemAppService service;

    public OrderItemController(OrderItemAppService service) {
        this.service = service;
    }

    @ApiOperation(value = "搜索订单明细")
    @GetMapping("/search")
    public ResponseEntity<PageResult<OrderItemDto>> searchOrderItems(
            @ApiParam(value = "查询参数") OrderItemQuery orderItemQuery,
            @PageableDefault Pageable pageable) {
        return success(service.searchOrderItems(orderItemQuery, pageable));
    }

    @ApiOperation(value = "获取订单明细")
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItem(@ApiParam(value = "订单明细Id", required = true) @PathVariable Long id){
        return success(service.getOrderItem(id));
    }

    @ApiOperation(value = "添加订单明细")
    @PostMapping
    public ResponseEntity<OrderItemDto> addOrderItem(
            @ApiParam(value = "订单明细信息", required = true) @Validated @RequestBody OrderItemParam orderItemParam) {
        return success(service.addOrderItem(orderItemParam));
    }

    @ApiOperation(value = "编辑订单明细")
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> editOrderItem(
            @ApiParam(value = "订单明细Id", required = true) @PathVariable Long id,
            @ApiParam(value = "订单明细信息", required = true) @Validated @RequestBody OrderItemParam orderItemParam) {
        return success(service.editOrderItem(id, orderItemParam));
    }

    @ApiOperation(value = "删除订单明细")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeOrderItem(
            @ApiParam(value = "订单明细Id", required = true) @PathVariable Long id) {
        service.removeOrderItem(id);
        return success();
    }
}
