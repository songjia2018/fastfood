package com.jonas.fastfood.common.mvc;


import com.jonas.fastfood.common.exception.NotLoginException;
import com.jonas.fastfood.common.utils.LogUtil;
import com.jonas.fastfood.common.utils.RequestUtils;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.common.utils.json.JsonUtil;

/**
 * !!! 操作 session 都基于此, 其他地方不允许操作! 避免 session 被滥用 !!!
 */
public class BackendSessionUtil {

    /**
     * 放在 session 里的图片验证码 key
     */
    private static final String CODE = BackendSessionUtil.class.getName() + "-CODE";
    /**
     * 放在 session 里的用户 的 key
     */
    private static final String USER = BackendSessionUtil.class.getName() + "-USER";


    /**
     * 将图片验证码的值放入 session
     */
    public static void putImageCode(String code) {
        RequestUtils.getSession().setAttribute(CODE, code);
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug("put image code({}) in session({})", code, RequestUtils.getSession().getId());
        }
    }

    /**
     * 验证图片验证码
     */
    public static boolean checkImageCode(String code) {
        if (U.isBlank(code)) {
            return false;
        }

        Object securityCode = RequestUtils.getSession().getAttribute(CODE);
        return securityCode != null && code.equalsIgnoreCase(securityCode.toString());
    }

    /**
     * 登录之后调用此方法, 将 用户信息 放入 session, app 需要将返回的数据保存到本地, 后面每次请求都要带过来
     */
    public static <T> String whenLogin(T user, String loginSign) {
        if (U.isNotBlank(user)) {
            BackendSessionModel sessionModel = BackendSessionModel.assemblyData(user, loginSign);
            if (U.isNotBlank(sessionModel)) {
                if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                    LogUtil.ROOT_LOG.debug("put ({}) in session({})",
                            JsonUtil.toJson(sessionModel), RequestUtils.getSession().getId());
                }

                RequestUtils.getSession().setAttribute(USER, sessionModel);
                return AppTokenHandler.generateToken(sessionModel);
            }
        }
        return U.EMPTY;
    }


    /**
     * 获取用户信息. 没有则使用默认信息
     */
    private static BackendSessionModel getSessionInfo() {
        // 先从 session 读, 没有就从 token 读
        BackendSessionModel sessionModel = (BackendSessionModel) RequestUtils.getSession().getAttribute(USER);
        if (U.isBlank(sessionModel)) {
            sessionModel = AppTokenHandler.getSessionInfoWithToken(BackendSessionModel.class);
        }
        // 为空则使用默认值
        return sessionModel == null ? BackendSessionModel.defaultUser() : sessionModel;
    }

    /**
     * 从 session 中获取用户 id
     */
    public static Long getUserId() {
        return getSessionInfo().getId();
    }

    /**
     * 从 session 中获取用户名
     */
    public static String getUserName() {
        return getSessionInfo().getName();
    }

    /**
     * 验证登录, 未登录则抛出异常
     */
    public static void checkLogin(String loginSignInCache) {
        boolean isMobile = RequestUtils.isMobileRequest();
        if (!getSessionInfo().wasLogin(isMobile, loginSignInCache)) {
            throw new NotLoginException();
        }
    }

    /**
     * 退出登录时调用. 清空 session
     */
    public static void signOut() {
        // 如果用 token 要客户端将对应的值删除才行
        RequestUtils.getSession().invalidate();
    }
}
