package com.example.demo.response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.service.ResponseMessage;
import utils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultCode handel(Exception e){

        if(e instanceof BusinessException){
            BusinessException myException =(BusinessException)e;
            return ResultCode.error( myException.getCode(),myException.getMessage());
        }else {
            logger.error("[系统异常] {}",e);
            return ResultCode.error(ResultEnum.SYSTEM_ERROR);
        }
    }


    /**
     * 处理BusinessException异常
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultCode busin(BusinessException e) {
        return ResultCode.error(e.getCode(), e.getErrMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultCode exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return ResultCode.error(ResultEnum.SYSTEM_ERROR);
    }
    /**
     * 处理参数校验异常
     *
     * @param req
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultCode exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException  e) {

        logger.error("参数校验异常:", e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return ResultCode.error(ResultEnum.SYSTEM_ERROR);
    }

    /**
     * 处理参数校验异常 --Json 转换异常
     * @param req
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultCode exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        logger.error("参数校验异常-json转换异常:", e);
        return ResultCode.error(ResultEnum.UNKNOWN_ERROR);
    }

}
