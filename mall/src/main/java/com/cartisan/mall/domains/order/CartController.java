package com.cartisan.mall.domains.order;

import com.cartisan.mall.domains.consts.MallConst;
import com.cartisan.mall.domains.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.cartisan.responses.ResponseUtil.success;

/**
 * @author colin
 */
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> list(HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(cartService.list(user.getId()));
    }

    @PostMapping
    public ResponseEntity<CartDTO> add(@Valid @RequestBody AddCartCommand command, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        command.setUserId(user.getId());
        return success(cartService.add(command));
    }

    @PutMapping
    public ResponseEntity<CartDTO> update(@Valid @RequestBody UpdateCartCommand command, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        command.setUserId(user.getId());
        return success(cartService.update(command));
    }

    @DeleteMapping
    public ResponseEntity<CartDTO> delete(@Valid @RequestBody DeleteCartCommand command, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        command.setUserId(user.getId());
        return success(cartService.delete(command));
    }

    @PutMapping("/selectAll")
    public ResponseEntity<CartDTO> selectAll(HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(cartService.selectAll(user.getId()));
    }

    @PutMapping("/unSelectAll")
    public ResponseEntity<CartDTO> unSelectAll(HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(cartService.unSelectAll(user.getId()));
    }

    @PutMapping("/products/sum")
    public ResponseEntity<Integer> sum(HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(cartService.sum(user.getId()));
    }
}
