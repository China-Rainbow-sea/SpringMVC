ViewResolver 接口，视图解析接口（ThymeleafViewResolver 实现了 ViewResolver 接口，InternalResourceViewResolver 也是实现了
ViewResolver接口...）
    这个接口做什么事的 ？
> 这个接口作用是将逻辑视图名称 转换为 物理视图名称
> 并且最终返回一个 view 接口对象

核心方法是？
> View resolveViewName(String viewName，Locale locale) throws Exception;

View 接口，视图接口（ThymeleafView 实现了接口，InternalResourceView 也实现了 View 接口）
这个接口做什么的
> 这个接口负责模板语法的字符串转换成 html代码，并且将html代码响应给浏览器。（渲染）

核心方法是什么？
> void render(@Nullable Map<String,?> model,HttpServletRequest request, HttpServletResponse response) throws Exception;

在 Spring MVC 中是怎么通过代码完成转发的？
@RequestMapping("/a")
public String toA() {
    // 返回的是一个逻辑视图名称
    return "a";  // 转发 ThymeleafView
}

注意，当 return "a"; 的时候，返回一个逻辑视图名称，这种方式跳转到视图，默认采用的就是 forward 方式跳转过去的

怎么转发？语法格式是什么？
> return "forward:/b";转发到 /b ，这是一次请求，底层创建的视图对象是：internalResourceView对象

怎么重定向？语法格式是什么呢？
> return "redirect:/b"；转发到 /b，这是两次请求，底层创建的视图对象是: RedirectView对象
> "forward:/b" 这个已经不是逻辑视图名称了，是以转发的方式跳转，是一个资源路径

怎么重定向？语法格式是什么呢？
> return "redirect:/b";转发到 /b，这是两次请求，底层创建的视图对象是：RedirectView对象
> 注意语法：必须以 : redirect:开始

总结：
转发: return "forward:/b" 底层创建的是InternalResourceView对象
重定向: return "redirect:/b" 底层创建的是 RedirectView对象

> thymeleaf 模板字符串，这个是浏览器不认识的。ThymeleafView进行解析将其转换成html响应给浏览器。

<mvc:view-controller>
这个配置信息，可以在springmvc.xml文件中进行配置，作用是:?
> 如果一个Controller上的方法只是未来完成视图跳转，没有任何业务代码，那么这个Controller可以不写
> 直接在springmvx.xml文件中添加 <mvc:view-controller> 即可。
> <mvc:view-controller path="/test" view-name="test/">
> 表示发送的请求路径是 /test，跳转的视图页面是，前缀 +test后缀


<mvc:anotation-driven/>
xmlns:mvc="http://www.springframework.org/schema/mvc"
这个配置信息叫做：开启注解驱动，在springmvc.xml文件中配置
当你使用了<mvc:view-controller>配置，会让你整个项目中所有的注解全部失效，你需要使用以上的配置来再次开启注解。
> <mvc:anotation-driven/>

关于静态资源处理:
假设我们在 webapp目录下有 static目录，static目录下有 touxiang.jpeg图片
我们可以在浏览器地址栏上直接访问，http://localhost:8080/springmvc/static/img/touxing.jpeg 吗？
不行，因为会走 DispatcherServlet,而我们的图片是没有编写对应的Controller 的，会导致 404错误

怎么办？两种解决方案:
第一种解决方案，开启默认的 Servlet处理
    在Tomcat服务器中提供了一个默认的Servlet，叫做: org.apache.catalina.servlets.DefaultServlet
    在CATALINA_HOME/conf/web.xml文件中，有这个默认的 Servlet的相关配置
    不过，这个默认的Servlet默认情况下是不开启的
    你需要在springmvc.xml文件中使用以下配置进行开启
    <mvc:default-servlet-handler/>
    <mvc:annotation-driven>  开启注解驱动
// 注意：上面这两个配置是一起的，不可以分开来使用。
> 开启之后的作用是：当你访问: http://localhost:8080/springmvc/static/img/touxiang.jpeg 的时候。
> 默认先走:DispatcherServlet,如果发生 404 错误的话，会自动走DefaultServlet,然后 DefaultServlet 帮你定位
> 静态资源

第二种解决方案:配置静态资源处理，在springmvc.xml文件中添加如下配置
<mvc:resources mapping="/static/**" location="/static/"></mvc:resources>
<mvc:annotaiton-driven>  //开启注解驱动
同样这两个是一起配合使用的，不可以分开来
> 作用：当请求路径符合 /static/**的时候，去 /static/位置找资源