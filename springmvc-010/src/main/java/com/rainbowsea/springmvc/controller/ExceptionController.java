package com.rainbowsea.springmvc.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 自定义异常
 */
@ControllerAdvice  // 标注类的异常
public class ExceptionController {

    @ExceptionHandler  // 标注 处理器异常方法
    public String tip (Exception e, Model model) {
        // 将异常信息存储到请求域当中
        model.addAttribute("yichang",e);
        // 逻辑视图
        return "tip";
    }


    // 其他的异常处理视图  ClassCastException
    @ExceptionHandler  // 标注 处理器异常方法
    public String tip (ClassCastException e, Model model) {
        // 将异常信息存储到请求域当中
        model.addAttribute("yichang",e);
        // 逻辑视图
        return "tip";
    }

    // 可以自定义多个异常信息

    //@ExceptionHandler
    public String tip2 (Exception e, Model model) {

        return null;
    }


    //@ExceptionHandler
    public String tip3 (Exception e, Model model) {

        return null;
    }
}
