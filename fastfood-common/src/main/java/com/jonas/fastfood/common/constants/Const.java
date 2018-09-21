package com.jonas.fastfood.common.constants;

/**
 * 项目中会用到的常量
 */
public final class Const {

    /**
     * 当前模块名
     */
    public static final String MODULE_NAME_USER = "user";

    /**
     * 当前模块说明. 当用在文档中时有用
     */
    public static final String MODULE_INFO_USER = MODULE_NAME_USER + "-用户";


    // ========== load ==========
    /**
     * 当前项目的基本包名
     */
    public static final String BASE_PACKAGE = "com.jonas.fastfood";

    /**
     * 指定模块存放枚举的包名
     */
    public static String enumPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".enums";
    }

    /**
     * 指定模块存放 typeHandler 的包名
     */
    public static String handlerPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".handler";
    }

    /**
     * 指定模块存放 model 的包名
     */
    public static String modelPath(String moduleName) {
        return BASE_PACKAGE + "." + moduleName.replace('-', '.') + ".model";
    }
    // ========== load ==========


    // ========== dubbo ==========
    /**
     * 全局的 dubbo 版本
     */
    public static final String DUBBO_VERSION = "1.0";
    /**
     * 全局的 dubbo 超时时间, 单位: 毫秒
     */
    public static final int DUBBO_TIMEOUT = 60 * 1000;
    /**
     * 全局的 dubbo 超时时间, 单位: 毫秒
     */
    public static final String DUBBO_FILTER = "";// "-exception";
    /**
     * dubbo 传输时单接口的最大字节数是 8M, 在 dubbo:protocol 中增大 payload 并不是那么好, 限制导出条数是相对折衷的一个办法
     */
    public static final int EXPORT_COUNT = 5000;
    // ========== dubbo ==========


    /**
     * <pre>
     * 在 servlet 规范中有 forward 和 redirect 两种页面跳转.
     *   forward 不会改变页面的请求地址, 而且前一个请求的 request 和 response 在下一个请求中还有效.
     *   redirect 正好不同, 要传值得使用 参数拼接 或者放在 session 里(都有利弊, 建议使用前者)
     *
     * 在 spring mvc 的 controller 上返回 String 时
     *   return "forward:/some/one" => 转发到 /some/one 的 controller 方法上去. mvc 内部的异常处理就是基于这种方式
     *   return "some/one"          => 转发到 template-path/some/one.jsp 页面去(如果是 jsp 的话)
     *   return "/some/one"         => 同 "some/one"
     * </pre>
     */
    public static final String FORWARD_PREFIX = "forward:";
    /**
     * <pre>
     * 在 servlet 规范中有 forward 和 redirect 两种页面跳转.
     *   forward 不会改变页面的请求地址, 而且前一个请求的 request 和 response 在下一个请求中还有效.
     *   redirect 正好不同, 要传值得使用 参数拼接 或者放在 session 里(都有利弊, 建议使用前者)
     *
     * 要传递参数, 可以使用 RedirectAttributes 或者直接拼在 url 上. 使用 spring mvc 的 redirect 会将当前上下文内容拼在 url 中
     * </pre>
     *
     * @see org.springframework.web.servlet.mvc.support.RedirectAttributes
     */
    public static final String REDIRECT_PREFIX = "redirect:";

    /**
     * pc 端传过来的 token 的 key
     */
    public static final String TOKEN = "t-n";
    /**
     * pc 端传过来的 version 的 key
     */
    public static final String VERSION = "v-n";

    /**
     * cors 支持的所有方法
     */
    public static final String[] SUPPORT_METHODS = new String[]{"HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS"};
}
