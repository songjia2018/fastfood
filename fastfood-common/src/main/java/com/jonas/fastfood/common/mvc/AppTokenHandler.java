package com.jonas.fastfood.common.mvc;


import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.encrypt.Encrypt;
import com.jonas.fastfood.common.utils.A;
import com.jonas.fastfood.common.utils.RequestUtils;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.common.utils.json.JsonUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class AppTokenHandler {

    /**
     * 生成 token 的过期时间
     */
    private static final Long TOKEN_EXPIRE_TIME = 15L;
    /**
     * 生成 token 的过期时间单位
     */
    private static final TimeUnit TOKEN_EXPIRE_TIME_UNIT = TimeUnit.DAYS;

    /**
     * 基于存进 session 的数据生成 token 返回
     */
    @SuppressWarnings("unchecked")
    public static <T> String generateToken(T sessionModel) {
        if (U.isNotBlank(sessionModel)) {
            Map<String, Object> session = JsonUtil.convert(sessionModel, Map.class);
            if (A.isNotEmpty(session)) {
                return Encrypt.jwtEncode(session, TOKEN_EXPIRE_TIME, TOKEN_EXPIRE_TIME_UNIT);
            }
        }
        return U.EMPTY;
    }

    /**
     * 重置 token 的过期时间
     */
    public static String resetTokenExpireTime() {
        String token = RequestUtils.getHeaderOrParam(Const.TOKEN);
        if (U.isNotBlank(token)) {
            Map<String, Object> session = Encrypt.jwtDecode(token);
            if (A.isNotEmpty(session)) {
                return Encrypt.jwtEncode(session, TOKEN_EXPIRE_TIME, TOKEN_EXPIRE_TIME_UNIT);
            }
        }
        return U.EMPTY;
    }

    /**
     * 从 token 中读 session 信息
     */
    public static <T> T getSessionInfoWithToken(Class<T> clazz) {
        String token = RequestUtils.getHeaderOrParam(Const.TOKEN);
        if (U.isNotBlank(token)) {
            Map<String, Object> session = Encrypt.jwtDecode(token);
            if (A.isNotEmpty(session)) {
                return JsonUtil.convert(session, clazz);
            }
        }
        return null;
    }
}
