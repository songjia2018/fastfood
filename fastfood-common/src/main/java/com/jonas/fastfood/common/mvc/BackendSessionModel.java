package com.jonas.fastfood.common.mvc;

import com.jonas.fastfood.common.exception.NotLoginException;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.common.utils.json.JsonUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
class BackendSessionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 默认未登录用户的 id
     */
    private static final Long DEFAULT_ID = 0L;
    /**
     * 默认未登录用户的 name
     */
    private static final String DEFAULT_NAME = "未登录用户";


    // ========== 存放在 session 中的数据 ==========

    /**
     * 存放在 session 中的(用户 id)
     */
    private Long id;
    /**
     * 存放在 session 中的(用户名)
     */
    private String name;

    /**
     * 登录标识
     * <p>
     * 这个值会放入缓存中, 当后一台设置再次登录时会更新缓存中的值, 前面的设备基于原来的标识再请求时将会当成没有登录
     */
    private String loginSign;

    // ========== 存放在 session 中的数据 ==========


    /**
     * 当前用户在指定域名下是否已登录. 已登录就返回 true
     */
    boolean wasLogin(boolean isMobile, String loginSignInCache) {
        // 如果是移动端, 当前 session 中的 登录标识值 必须要跟 缓存 中的一样
        if (isMobile) {
            if (U.isBlank(loginSignInCache) || U.isBlank(loginSign) || !loginSignInCache.equals(loginSign)) {
                throw new NotLoginException("您的账号已经由别的设备登录, 当前账号已经退出");
            }
        }
        return !Objects.equals(DEFAULT_ID, id) && !Objects.equals(DEFAULT_NAME, name);
    }


    // 以下为静态方法


    /**
     * 组装数据, 将用户对象跟存进 session 中的数据进行转换
     */
    static <T> BackendSessionModel assemblyData(T user, String loginSign) {
        BackendSessionModel model = JsonUtil.convert(user, BackendSessionModel.class);
        if (U.isNotBlank(model)) {
            model.setLoginSign(loginSign);
        }
        return model;
    }


    /**
     * 未登录时的默认用户信息
     */
    static BackendSessionModel defaultUser() {
        return new BackendSessionModel().setId(DEFAULT_ID).setName(DEFAULT_NAME);
    }
}
