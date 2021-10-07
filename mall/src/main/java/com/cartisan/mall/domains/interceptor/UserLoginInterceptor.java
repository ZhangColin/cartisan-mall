package com.cartisan.mall.domains.interceptor;

import com.cartisan.mall.domains.consts.MallConst;
import com.cartisan.mall.domains.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author colin
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
    /**
     * 返回 true 表示继续流程，false 表示中断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        final HttpSession session = request.getSession();
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        if (user == null) {
            log.info("user=null");
            throw new RuntimeException("未登录，请先登录");
        }
        return true;
    }
}
