package com.zlkj.train.exception;

import com.zlkj.train.result.CodeMsg;
import com.zlkj.train.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
//        e.printStackTrace();
//        if (e instanceof BindException) {
//            BindException bindException = (BindException) e;
//            String message = bindException.getMessage();
//            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
//        } else
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return Result.error(exception.getCodeMsg());
        }
        return Result.error(CodeMsg.VISIT_MANAGER_ERR);
    }
}
