package com.cartisan.mall.domains.shipping;

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
@RequestMapping("/shippings")
public class ShippingController {
    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @GetMapping
    public ResponseEntity<PageResult<Shipping>> search(@PageableDefault Pageable pageable, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        return success(shippingService.list(user.getId(), pageable));
    }

    @PostMapping
    public ResponseEntity<Long> add(@Valid @RequestBody ShippingParam param, HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        return success(shippingService.add(user.getId(), param));
    }

    @PutMapping("/{shippingId}")
    public ResponseEntity<?> update(@Valid @RequestBody ShippingParam param, @PathVariable Long shippingId,  HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        shippingService.update(user.getId(), shippingId,  param);

        return success();
    }

    @DeleteMapping("/{shippingId}")
    public ResponseEntity<?> delete(@PathVariable Long shippingId,  HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);

        shippingService.delete(user.getId(), shippingId);

        return success();
    }
}
