package com.hosea.wiki.api;

import com.hosea.wiki.exception.ConditionalException;
import com.hosea.wiki.resp.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 校验异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public JsonResponse validExceptionHandler(BindException e) {
        JsonResponse commonResp = new JsonResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), 500);
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    /**
     * 校验异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ConditionalException.class)
    @ResponseBody
    public JsonResponse validExceptionHandler(ConditionalException e) {
        JsonResponse commonResp = new JsonResponse(e.getMessage(), e.getCode());
        LOG.warn("业务异常：{}", e.getMessage());
//        commonResp.setSuccess(false);
//        commonResp.setMessage(e.getCode().getDesc());
        return commonResp;
    }

    /**
     * 校验异常统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse validExceptionHandler(Exception e) {
        JsonResponse commonResp = new JsonResponse("系统出现异常，请联系管理员",500);
        LOG.error("系统异常：", e);
        return commonResp;
    }
}
