package com.cartisan.mall.domains.order;

import com.cartisan.dto.PageResult;
import com.cartisan.mall.domains.consts.MallConst;
import com.cartisan.mall.domains.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.cartisan.responses.ResponseUtil.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<PageResult<OrderInfo>> list(@PageableDefault Pageable pageable, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(orderService.list(user.getId(), pageable));
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<OrderDTO> detail(@PathVariable Long orderNo, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(orderService.detail(user.getId(), orderNo));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> add(@Valid @RequestBody PlaceOrderCommand command, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        command.setUserId(user.getId());
        return success(orderService.create(user.getId(), command.getShippingId()));
    }

    @PutMapping("/{orderNo}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long orderNo, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        orderService.cancel(user.getId(), orderNo);
        return success();
    }
}
