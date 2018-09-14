package com.jonas.fastfood.controller.config;


import com.jonas.fastfood.common.exception.ForbiddenException;
import com.jonas.fastfood.common.exception.NotLoginException;
import com.jonas.fastfood.common.exception.ServiceException;
import com.jonas.fastfood.common.utils.A;
import com.jonas.fastfood.common.utils.LogUtil;
import com.jonas.fastfood.common.utils.RequestUtils;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.common.utils.json.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 处理全局异常的控制类
 *
 * @see org.springframework.boot.autoconfigure.web.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 */
@RestControllerAdvice
public class BackendGlobalException {

    private static final String SERVICE = ServiceException.class.getName();
    private static final String FORBIDDEN = ForbiddenException.class.getName();
    private static final String NOT_LOGIN = NotLoginException.class.getName();

    @Value("${online:false}")
    private boolean online;

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<JsonResult> service(ServiceException e) {
        String msg = e.getMessage();
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug(msg);
        }
        return fail(msg);
    }

    /**
     * 未登录
     */
    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<JsonResult> notLogin(NotLoginException e) {
        String msg = e.getMessage();
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug(msg);
        }
        return notLogin(msg);
    }

    /**
     * 无权限
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<JsonResult> forbidden(ForbiddenException e) {
        String msg = e.getMessage();
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            LogUtil.ROOT_LOG.debug(msg);
        }
        return notPermission(msg);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<JsonResult> noHandler(NoHandlerFoundException e) {
       // bindAndPrintLog(e);

        String msg = String.format("没找到(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return new ResponseEntity<>(JsonResult.notFound(msg), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<JsonResult> missParam(MissingServletRequestParameterException e) {
        //bindAndPrintLog(e);

        String msg = String.format("缺少必须的参数(%s), 类型(%s)", e.getParameterName(), e.getParameterType());
        return new ResponseEntity<>(JsonResult.badRequest(msg), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<JsonResult> notSupported(HttpRequestMethodNotSupportedException e) {
        //bindAndPrintLog(e);

        String msg = "不支持此种请求方式.";
        if (!online) {
            msg += String.format(" 当前方式(%s), 支持方式(%s)", e.getMethod(), A.toStr(e.getSupportedMethods()));
        }
        return fail(msg);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<JsonResult> uploadSizeExceeded(MaxUploadSizeExceededException e) {
       // bindAndPrintLog(e);

        // 右移 20 位相当于除以两次 1024, 正好表示从字节到 Mb
        String msg = String.format("上传文件太大! 请保持在 %sM 以内", (e.getMaxUploadSize() >> 20));
        return fail(msg);
    }

    /**
     * 未知的所有其他异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<JsonResult> exception(Throwable e) {
        String msg = e.getMessage();
        if (U.isNotBlank(msg)) {
            // x.xxException: abc\nx.xxException: abc\n
            msg = msg.split("\n")[0].trim();
            if (msg.startsWith(NOT_LOGIN)) {
                // 没登录
                if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                    LogUtil.ROOT_LOG.debug(msg, e);
                }
                return notLogin(msg.substring(NOT_LOGIN.length() + 2));
            } else if (msg.startsWith(SERVICE)) {
                // 业务异常
                if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                    LogUtil.ROOT_LOG.debug(msg, e);
                }
                return fail(msg.substring(SERVICE.length() + 2));
            } else if (msg.startsWith(FORBIDDEN)) {
                // 没权限
                if (LogUtil.ROOT_LOG.isDebugEnabled()) {
                    LogUtil.ROOT_LOG.debug(msg, e);
                }
                return notPermission(msg.substring(FORBIDDEN.length() + 2));
            }
        }

        if (LogUtil.ROOT_LOG.isErrorEnabled()) {
            LogUtil.ROOT_LOG.error("request has exception!", e);
        }
        return fail(U.returnMsg(e, online));
    }

    // ==================================================

    /*private void bindAndPrintLog(Exception e) {
        if (LogUtil.ROOT_LOG.isDebugEnabled()) {
            // 当没有进到全局拦截器就抛出的异常, 需要这么处理才能在日志中输出整个上下文信息
            LogUtil.bind(RequestUtils.logContextInfo()
                    .setId(String.valueOf(BackendSessionUtil.getUserId()))
                    .setName(BackendSessionUtil.getUserName()));
            try {
                LogUtil.ROOT_LOG.debug(e.getMessage(), e);
            } finally {
                LogUtil.unbind();
            }
        }
    }*/

    private ResponseEntity<JsonResult> notLogin(String msg) {
        return new ResponseEntity<>(JsonResult.notLogin(msg), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<JsonResult> notPermission(String msg) {
        return new ResponseEntity<>(JsonResult.notPermission(msg), HttpStatus.FORBIDDEN);
    }

    private ResponseEntity<JsonResult> fail(String msg) {
        return new ResponseEntity<>(JsonResult.fail(msg), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
