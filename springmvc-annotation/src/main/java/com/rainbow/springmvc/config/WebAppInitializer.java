package com.rainbow.springmvc.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


// 在这个配置类当中编写的其实就是 web.xml 文件中的配置
// 用来标注这个类当中的配置文件
@Configuration  // 表示该类是配置类
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * Spring 的配置
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }


    /**
     * 配置过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
       // 配置字符编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceRequestEncoding(true);
        characterEncodingFilter.setForceRequestEncoding(true);

        // 配置 HiddenHttpMethodFilter ???
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter,hiddenHttpMethodFilter};
    }

    /**
     * Spring MVC 的配置
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }


    /**
     * 用来配置 DispatcherServlet 的 <url-pattern></url-pattern>
     *用于配置 DispatcherServlet 的映射路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
