Spring MVC 框架
1. 第一个Spring MVC 程序的开发流程
> 1. 第一步:创建一个空的工程Springmvc
> 2. 设置 JDK 的版本
> 3. 第三步:设置Maven
> 4. 第四步:创建Maven模块(我这里创建的是一个普通的Maven模块)
> 5. 第五步:在pow文件中设置打包方式: war
> 6. 第六步:引入依赖:
>    1. Springmvc依赖
>    2. logback依赖
>    3. thymeleaf和spring6整合依赖
>    4. servlet依赖(scope设置为 provided,表示这个依赖最终由第三方容器来提供)
>


2. 给Maven 模块添加 web 支持
> 在模块下的 src\main 目录下新建 webapp目录(默认是代伊欧小蓝点的,没有小蓝点,自己添加web支持就有小蓝点了)
> 另外,在添加web支持的时候,需要添加web.xml文件,注意添加的路径
> 

3.在 web.xml 文件中配置,前端控制器(springmvc框架内置的一个类,DispatcherServlet),所有的请求都应该
经过这个DispatcherServlet 的处理.
> 重点:<url-pattern>/<url-pattern>
> 这里的 / 表示,除 xx.jsp 结尾的请求路径之外的所有请求路径.
> 也就是说,只要不是 jsp请求路径,那么一定会走 dispatcherServlet
> 

4. 编写FirsController ,在类上标注 @Controller 注解,纳入IOC容器的管理
> 当然,也可以采用 @Component注解 进行标注, @Controller 只是 @Component 注解的别名
> 

5. 配置/编写 Springmvc框架自己的配置文件
> 这个配置文件由默认的名字:<servlet-name>-servlet.xml
> 这个配置文件有默认的存储位置:WEB-INF目录下
> 

6. 提供视图:
> 在/WEB-INF/templates目录下新建 first.thymeleaf 文件
> 在该文件中编写符合 thymeleaf 语法格式的字符串(编写thymeleaf的模板语句)


7.提供请求映射
```java
@RequestMapping(value = "/test")
public String hehe() {
// 返回一个逻辑视图名称
return "first";
}
```

> 最终会将逻辑视图名称转换为物理视图名称
> 逻辑视图名称:first
> 物理视图名称: 前缀 + first + 后缀
> 最终路径是:/WEB-INF/templates/first.thymeleaf
> 使用Thymeleaf 模板引擎,将/WEB-INF/templates/first.thymeleaf转换成 html代码,最终响应到浏览器端
> 
8. 测试:
> 配置 Tomcat 服务器 ,这里要是 tomcat 9以上
> 解决tomcat服务器控制台日志乱码问题
> 启动tomcat 服务器,在浏览器地址上直接发送请求:http://localhost:8080/springmvc/test

