package com.jonas.fastfood.common.utils;


import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.common.utils.json.JsonUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * <span style="color:red;">!!!此工具类请只在 Controller 中调用!!!</span>
 */
public final class RequestUtils {

    private static final String USER_AGENT = "user-agent";
    private static final String REFERRER = "referer";

    /**
     * 获取真实客户端IP
     * 关于 X-Forwarded-For 参考: http://zh.wikipedia.org/wiki/X-Forwarded-For<br>
     * 这一 HTTP 头一般格式如下:
     * X-Forwarded-For: client1, proxy1, proxy2,<br><br>
     * 其中的值通过一个 逗号 + 空格 把多个 IP 地址区分开, 最左边(client1)是最原始客户端的IP地址,
     * 代理服务器每成功收到一个请求，就把请求来源IP地址添加到右边
     */
    public static String getRealIp() {
        HttpServletRequest request = getRequest();

        String ip = request.getHeader("X-Forwarded-For");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为 真实 ip
            return ip.split(",")[0].trim();
        }

        ip = request.getHeader("X-Real-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("WL-Proxy-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("HTTP_CLIENT_IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }

        ip = request.getHeader("X-Cluster-Client-IP");
        if (U.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip.trim();
        }
        return request.getRemoteAddr();
    }

    /*** 是否是本机 */
    public static boolean isLocalRequest() {
        return U.isLocalRequest(getRealIp());
    }

    public static String userAgent() {
        return getRequest().getHeader(USER_AGENT);
    }

    /**
     * 如果是 ie 请求就返回 true
     */
    public static boolean isIeRequest() {
        return userAgent().toUpperCase().contains("MSIE");
    }

    /**
     * 判断当前请求是否来自移动端, 来自移动端则返回 true
     */
    public static boolean isMobileRequest() {
        return U.checkMobile(userAgent());
    }

    /**
     * 判断当前请求是否是 ajax 请求, 是 ajax 则返回 true
     */
    public static boolean isAjaxRequest() {
        HttpServletRequest request = getRequest();

        String requestedWith = request.getHeader("X-Requested-With");
        if (U.isNotBlank(requestedWith) && "XMLHttpRequest".equals(requestedWith)) {
            return true;
        }

        String contentType = request.getHeader("Content-Type");
        return (U.isNotBlank(contentType) && "application/json".startsWith(contentType)) || U.isNotBlank(request.getParameter("_ajax")) || U.isNotBlank(request.getParameter("_json"));
    }

    /**
     * 请求头里的 referer 这个单词拼写是错误的, 应该是 referrer, 历史遗留问题
     */
    public static String getReferrer() {
        return getRequest().getHeader(REFERRER);
    }

    /**
     * 格式化参数, 如果是文件流(form 表单中有 type="multipart/form-data" 这种), 则不打印出参数
     *
     * @return 示例: id=xxx&name=yyy
     */
    public static String formatParam() {
        // return getRequest().getQueryString(); // 没有时将会返回 null

        HttpServletRequest request = getRequest();
        String contentType = request.getContentType();
        boolean upload = U.isNotBlank(contentType) && contentType.startsWith("multipart/");
        return upload ? "uploading file" : U.formatParam(request.getParameterMap());
    }

    /**
     * 先从请求头中查, 为空再从参数中查
     */
    public static String getHeaderOrParam(String param) {
        HttpServletRequest request = getRequest();
        String value = request.getHeader(param);
        if (U.isBlank(value)) {
            value = request.getParameter(param);
        }
        return U.isBlank(value) ? U.EMPTY : value.trim();
    }

    /**
     * 格式化头里的参数: 键值以冒号分隔
     */
    public static String formatHeadParam() {
        HttpServletRequest request = getRequest();

        StringBuilder sbd = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            sbd.append("<");
            String headName = headerNames.nextElement();
            sbd.append(headName).append(" : ").append(request.getHeader(headName));
            sbd.append(">");
        }
        return sbd.toString();
    }


    /**
     * 将「json 字符」以 json 格式输出
     */
    public static void toJson(JsonResult result, HttpServletResponse response) throws IOException {
        render("application/json", result, response);
    }

    private static void render(String type, JsonResult jsonResult, HttpServletResponse response) throws IOException {
        String result = JsonUtil.toJson(jsonResult);
        if (LogUtil.ROOT_LOG.isInfoEnabled()) {
            LogUtil.ROOT_LOG.info("return json: " + result);
        }

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType(type + ";charset=utf-8;");
            response.getWriter().write(result);
        } catch (IllegalStateException e) {
            // 基于 response 调用了 getOutputStream(), 又再调用 getWriter() 会被 web 容器拒绝
            if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                LogUtil.ROOT_LOG.debug("response state exception", e);
            }
        }
    }

    /**
     * 将「json 字符」以 html 格式输出. 不常见! 这种只会在一些特殊的场景用到
     */
    public static void toHtml(JsonResult result, HttpServletResponse response) throws IOException {
        render("text/html", result, response);
    }

    /**
     * 基于请求上下文生成一个日志需要的上下文信息对象
     */
    public static LogUtil.RequestLogContext logContextInfo() {
        HttpServletRequest request = getRequest();

        String ip = getRealIp();
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String param = formatParam();
        String headParam = formatHeadParam();
        return new LogUtil.RequestLogContext(ip, method, url, param, headParam);
    }


    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }
}
