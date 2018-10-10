package com.jonas.fastfood.controller.config;


import com.jonas.fastfood.common.mvc.BackendSessionUtil;
import com.jonas.fastfood.common.utils.RequestUtils;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.commonservice.commons.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackendWebBean {

    private static final String LOGIN_SIGN_PREFIX = "login-";

    @Autowired
    private CacheService cacheService;

    /**
     * 获取 session 用户存到缓存中的登录标识
     */
    public String getLoginSignInCache() {
/*        if (RequestUtils.isMobileRequest()) {
            Long userId = BackendSessionUtil.getUserId();
            if (U.greater0(userId)) {
                return cacheService.get(LOGIN_SIGN_PREFIX + userId);
            }
        }
        return U.EMPTY;*/
        Long userId = BackendSessionUtil.getUserId();
        if (U.greater0(userId)) {
            return cacheService.get(LOGIN_SIGN_PREFIX + userId);
        }
        return U.EMPTY;
    }

    /**
     * 添加登录标识到缓存(登录成功之后, 将 session 生成 token 返回之前调用此方法)并将标识返回
     *
     * @see BackendSessionUtil#whenLogin(Object, String)
     */
    public String addLoginSignInCache(Long userId) {
        /*if (RequestUtils.isMobileRequest()) {
            String loginSign = U.uuid();
            cacheService.set(LOGIN_SIGN_PREFIX + userId, loginSign);
            return loginSign;
        }
        return U.EMPTY;*/
        String loginSign = U.uuid();
        cacheService.set(LOGIN_SIGN_PREFIX + userId, loginSign);
        return loginSign;
    }
}
