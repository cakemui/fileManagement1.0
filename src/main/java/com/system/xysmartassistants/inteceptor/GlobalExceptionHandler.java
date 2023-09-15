package com.system.xysmartassistants.inteceptor;

import com.system.xysmartassistants.common.ResultBean;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 小月
 * 2023/9/4/14点10分
 *
 * 全局异常捕获配置
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 所有异常
     */
    @ExceptionHandler(Exception.class)
    @RequestBody
    public ResultBean<String> handleException(Exception e)
    {
        log.error("发生错误，错误内容如下：",e);
        ResultBean<String> resultBean = new ResultBean<>();
        resultBean.setData(e.getMessage());
        resultBean.setSmg("接口调用发生异常！");
        return resultBean;
    }


//    /**
//     * 基础异常
//     **/
//    @ExceptionHandler(BaseException.class)
//    public Result baseException(BaseException e)
//    {
//        return ReturnResult.error(e.getMessage());
//    }
//
//    /**
//     * 业务异常
//     */
//    @ExceptionHandler(CustomException.class)
//    public Result businessException(CustomException e)
//    {
//        if (e.getCode()==null)
//        {
//            return ReturnResult.error(e.getMessage());
//        }
//        return ReturnResult.error(Integer.valueOf(e.getCode()), e.getMessage());
//    }
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public Result handlerNoFoundException(Exception e)
//    {
//        log.error(e.getMessage(), e);
//        return ReturnResult.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public Result handleAuthorizationException(AccessDeniedException e)
//    {
//        log.error(e.getMessage());
//        return ReturnResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
//    }
//
//    @ExceptionHandler(AccountExpiredException.class)
//    public Result handleAccountExpiredException(AccountExpiredException e)
//    {
//        log.error(e.getMessage(), e);
//        return ReturnResult.error(e.getMessage());
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public Result handleException(RuntimeException e)
//    {
//        log.error(e.getMessage(), e);
//        return ReturnResult.error(e.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e)
//    {
//        log.error(e.getMessage(), e);
//        return ReturnResult.error(e.getMessage());
//    }

}
