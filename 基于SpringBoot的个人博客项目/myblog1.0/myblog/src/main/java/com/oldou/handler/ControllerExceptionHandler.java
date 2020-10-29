package com.oldou.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常拦截器
 */
@ControllerAdvice  //拦截所有@Controller注解的类
public class ControllerExceptionHandler {
    //获取异常通知
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //这个注解表示这个方法是可以做异常处理的
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //记录异常信息
        logger.error("Request URL: {} , Exception :  {}",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        //记录异常之后需要返回一个异常页面
        ModelAndView mv = new ModelAndView();
        //添加异常的url和异常的信息
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        //添加到哪个页面
        mv.setViewName("error/error");
        return mv;
    }


}
