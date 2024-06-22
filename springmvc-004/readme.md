# 获取请求提交的数据


1. 第一种方式使用原生的 Servlet API
在处理器的方法参数上提供，HttpServletRequest
SpringMVC 框架会自动将 Tomcat 服务器创建 request 对象传递给处理器方法
我们直接在处理器方法中使用request对象即可
当然，HttpServletResponse，HttpSession 有需要的话，也可以采用这种方式注入


2. 第二种方式：使用SpringMVC框架提供的一个注解：@RequestParam(请求参数)
@RequestParam注解中的属性:
> value 属性：value 属性可以使用 name 属性代替
> name 属性: name 属性可以已使用 value 属性代替
> required 属性: 用来设置参数是否为必须的，默认值是 true，默认情况下这个参数是必须要传递过来的，如果前端没有提交这个参数，报错，400错误。
> 这个属性有点类似于 @RequestMapping 注解中的 params 属性的作用
> @RequestMapping(value="/testParams",params={"username","password})
> public String testParams() {
>   return "ok"
> }
> required 属性可以设置为 false,这样这个参数就不是必须的了，如果前端没有提供，则不会报400错误。
> defaultValue 属性，通过defaultValue属性可以给参数赋默认值，如果前端没有提供这样的参数，参数的默认值就会起作用，
> 如果前端提供了值，那么默认值就会失效。
> 

3. 第三种方式：依靠控制器方法上的形参名来接收
> 如果请求参数名 和控制器方法上的参数名保持一致，那么@RequestParam注解可以省略。
> 如果你使用的是Spring6+版本，则需要在 pom.xml 文件中添加如下编译配置，（Spring5以及之前的版本不需要）
> 
```xml



    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.12.1</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <compilerArgs>
                        <arg>-parameters</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>

```
>  注意：如果控制器方法上的形参名和请求参数名，没有对应上，那么控制器方法中的形参默认值是 null
> 

4. 第四种方式，使用POJO类/javaBean 接收请求参数（这是最常用的）
> 底层是原理：反射机制
> 不过，使用着这种方式的前提是，POJO类的属性名必须和请求参数的参数名保持一致
> 实现原理？
>   前端提交了一个请求，参数名是：username，那么要求POJO类当中必须有一个属性名也叫做：username
>   Class Clazz = Class.forName("com.rainbowsea.springmvc.pojo.User");
>   User user = (User)clazz.newInstance();
>   String fieldName = "username"
>   String setMethodName = "setUsername"
>   Method setMethod = clazz.getDeclaredMethod(setMethodName,...)
>   setMethod.invoke(user,"zhaoliu")
> 
> 重点：底层通过反射机制调用set方法给属性赋值，所以 set 方法的方法名非常重要
> 如果前端提交了参数是，username=zhangsan
> 那么必须保证POJO类当中有一个方法名叫做：setUserName
> 如果前端提交参数是：email=zhangsna@rainbowsea.com
> 那么必须保证POJO类当中有一个方法名叫做：setEmail
> 如果没有对应的set方法，将无法给对应的属性赋值。
> 

Spring MVc 当中向获取到请求头相关的信息
>@RequestHeader 注解和 @RequestParam类似
```java
@PostMapping("/user/reg")
    public String register(User user,
                           @RequestHeader(value = "Referer",required = false,defaultValue = "") String referer) {

        System.out.println(user);
        System.out.println(referer); // Referer: http://localhost:8080/springmvc/
        return "ok";

    }
```

关于Javaweb项目中，get请求的乱码问题？
get请求，提交的数据是在浏览器的地址栏上回显，在请求行上提交数据，例如：/springmvc/login?username=张三&password=123
怎么解决get请求乱码问题？
 对url进行编码设置，在哪里可以设置URL的编码方式呢
> 在Tomcat 服务器的配置（CATALINA_HOME/conf/server.xml文件中）
> 对于Tomcat 10来说，get请求没有乱码，也就是说 Tomcat10已经自动对URI进行编码，并且默认的编码方式就是UTR-8
>           <Connector port=:"8080" protocol =HTTP/1.1
>                   connectionTimeout="20000"
>                   redirectPort = "8443"
>                   maxParameterCount="1000"
>                   URIEncoding="UTF-8"
>                   />
> 对于Tomcat10 和 Tomcat9 来说，get请求没有乱码，也就是说Tomcat10或者Tomcat9已经自动对URI进行编码，并且默认的编码方式就是UTF-8
> 但是对于Tomcat8来说，URIEncoding的默认值是ISO-8859-1编码方式，所以在Tomcat8中，get请求是存在中文乱码问题的，怎么解决，如上述所述
> 

关于JavaWeb项目中，Post请求的乱码问题？
post请求乱码如何解决？
request.setCharacterEncoding("UTF-8")
但是有一个前提
 request.setCharacterEncoding("UTF-8") ; 这一行代码必须在 request.getParameter("") 方法之前执行，才有效。
在Tomcat10当中，我们是不需要考虑post请求乱码问题，因为Tomcat10，已经自动帮助我们执行了,request.setCharacterEncoding("UTF-8")
在哪里可以看到呢？
> 在 CATALINA_HOME/cont/web.xml 文件中有这样的配置
> <request-character-encoding>UTF-8</request-character-encoding>
> <response-character-encoding>UTF-8</response-character-encoding>
> 这个配置信息表示，请求体采用UTF-8的方式，另外响应的时候也采用UTF-8的方式，所以POST请求无乱码，响应也没有乱码
> 注意了，这个Tomcat9以及之前的版本来说，没有以上的配置，Post请求乱码问题，响应的乱码问题需要自行解决
> 那么如果遇到Tomcat9版本，那么Post请求乱码应该怎么解决呢？对于SpringMVC来说，有什么好办法呢？
>  在request.getParamer()方法执行之前，执行 request.setCharacterEncoding("UTF-8");这样问题就解决了
>  
>第一种方案，自己编写一个过滤器，过滤器Filter在Servlet执行之前执行。
> 第二种方案：使用SpringMVC框架内置的字符编码过滤器即可，CharacterEncodingFilter.
```java
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String encoding = this.getEncoding();
        if (encoding != null) {
            if (this.isForceRequestEncoding() || request.getCharacterEncoding() == null) {
                request.setCharacterEncoding(encoding);
            }

            if (this.isForceResponseEncoding()) {
                response.setCharacterEncoding(encoding);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```