package com.zpy.bilibili.service.handler;

import com.zpy.bilibili.domain.JsonResponse;
import com.zpy.bilibili.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: zpy-bilibili
 * @description: 全局异常处理
 * @author: 张鹏宇
 * @create: 2022-04-16 02:28
 **/

@Controller
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExceptionHandler(HttpServletRequest request, Exception e) {
        String errorMsg = e.getMessage();
        if (e instanceof ConditionException) {
            String errorCode = ((ConditionException) e).getCode();
            return new JsonResponse<>(errorCode, errorMsg);
        } else {
            return new JsonResponse<>("500", errorMsg);
        }
    }

}
