package com.mzd.my_boot_quartz.Controller;

import com.mzd.my_boot_quartz.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorController {
    private final static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Throwable throwable) {
        logger.error(throwable.toString());
        return new Result("500", throwable.toString(), null);
    }
}
