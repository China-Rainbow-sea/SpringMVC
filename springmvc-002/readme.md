Spring MVC 中的配置文件，名字是可以指定的，位置也是可以指定的，怎么指定？
设置 DispatcherServlet 的初始化参数

```xml

  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
<!--        通过 Servlet 的初始化参数来指定Spring MVC 配置文件的名字和位置-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
<!--            指定Spring MVC 配置文件的名字是：springmvc.xml-->
<!--            指定了Spring MVC 文件存放的路径是：类的根路径 classpath-->
<!--            这里爆红是：idea的误报-->
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
```
建议: 在 web 服务器启动的时候，初始化 DispatcherServlet，这样用户第一次请求时，效率较高。体验好