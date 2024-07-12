package com.rainbow.springmvc.config;


import com.rainbow.springmvc.interceptors.MyInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;
import java.util.Properties;

// 以下相当于是 Springmvc.xml 的配置文件
@Configuration  // 表示该类是配置类
@ComponentScan("com.rainbow.springmvc.controller")
// 开启注解驱动
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    // 以下三个方法合并起来就是开启视图解析器
    @Bean
    public ThymeleafViewResolver getViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(springTemplateEngine);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver iTemplateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(iTemplateResolver);
        return templateEngine;
    }

    @Bean
    public ITemplateResolver templateResolver(ApplicationContext applicationContext) {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/thymeleaf/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);//开发时关闭缓存，改动即可生效。发布的时候，则将其开启即可
        return resolver;
    }


    // 开启静态资源处理，开启默认的 Servlet 处理

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    // 不写对应的 Controller 只需要 html页面就可以访问的配置

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
    }

    // 配置异常处理器

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        // 可以配置多个异常处理器，这是其中的一个
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();

        // 将异常处理器添加到List集合中
        resolvers.add(simpleMappingExceptionResolver);
        // 配置异常信息，跳转的内容
        // 设置其中的 exceptionMapping 属性
        Properties properties = new Properties();
        properties.setProperty("java.lang.Exception","tip");
        simpleMappingExceptionResolver.setExceptionMappings(properties);

        // 设置其中的 exceptionAttribute 属性,将错误信息添加到请求域当中
        simpleMappingExceptionResolver.setExceptionAttribute("e");

        // 将异常处理器添加到 List 集合中
        resolvers.add(simpleMappingExceptionResolver);

    }

    // 配置拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor myInterceptor = new MyInterceptor();
        // 除了 /test 请求路径，不拦截，其他的路径都进行拦截处理
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/test");
    }


    /**
     * 在springmvc.xml 文件中都可以配置什么？
     * 1.组件扫描
     * 2.视图解析器
     * 3.静态资源处理 default-servlet-handler
     * 4.视图控制器 view-controller
     * 5.开启注解驱动
     * 6.异常处理器
     * 7.拦截器
     */
}
