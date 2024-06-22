package com.rainbowsea.springmvc.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //设置请求体的字符集
        request.setCharacterEncoding("UTF-8");

        // 设置响应的字符集
        response.setContentType("text/html;charset=UTF-8");
        // 执行下一个资源
        filterChain.doFilter(request,response);
    }
}
