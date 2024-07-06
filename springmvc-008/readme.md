在Spring MVC 中如何使用原生 Servlet API 完成AJAX 请求的响应？
@RequestMapping(value = "/ajax", method = RequestMethod.GET)
/*   public String ajax(HttpServletResponse response) throws IOException {
PrintWriter out = response.getWriter();
out.print("hell ajax,my name is Spring MVC");

        return null;
    }*/

    @GetMapping("/ajax")
    public void ajax(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print("hell ajax,my name is Spring MVC");
    }

@ResponseBody 注解(非常重要，使用非常多，因为以后大部分的请求都是 Ajax 请求)

    @GetMapping("/ajax")
    @ResponseBody
    public String ajax()  {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        return "hell ajax,my name is Spring MVC3";
    }

重点：一旦处理器方法添加了 @ResponseBody 注解，那么 return 返回语句，返回的将不是一个“逻辑视图”了，而是直接将
返回结果采用字符串的形式响应给浏览器。
底层实现原理实际上代替的就是：
PrintWriter out = response.getWriter();
out.print("hell ajax,my name is Spring MVC3")

以上程序使用的HTTP消息转换器，StringHttpMessageConverter

***

如下：
当一个处理器方法上哟 @ResponseBody 注解，并且返回的是一个Java对象，例如：user,那么springmvc框架，
会自动将 user 对象转换成 json格式的字符串，响应给浏览器，
当然，你必须在pom.xml 文件中引入一个可以处理Json的依赖，例如：jackson

```xml
        <!--        引入jackson依赖，可以将java对象转换为json格式字符串-->
<!--        专门负责将Java对象转换成JSON格式字符串的组件，
当然，它也可以将JSON格式的字符串转换成Java对象-->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

    @GetMapping("/ajax")
    @ResponseBody
    public User ajax() {
        // 当前处理器方法上添加了 @ResponseBoay 注解，
        // 那么这个方法的返回值不再是逻辑视图名称了
        //return "hell ajax,my name is Spring MVC3";
        //return "id:1213,username:lihua,password:123";

        User user = new User(1122L, "李华", "123");
        return user;


    }

以上程序中使用的消息转换器是:MappingJackson2HttpMessageConvertor

非常好用的注解：@RestController
它出现在“类”上，等于 @Controller + @ResponseBody
@RestController 它是一个复合注解
当一个类上添加 @RestController 注解之后，表示该类上自动添加了 @Controller 注解，并且该类中
所有的方法上都会自动添加 @ResponseBody 注解。

关于 @RequestBody 注解
该注解只能使用在处理器方法的形参上
这个注解的作用是直接将请求体传递给Java程序，在Java程序中可以直接使用一个String 类型的变量接收这个请求体的内容。
底层使用的HTTP消息转换器是：FormHttpMessageConvertor

关于@RequestBody 注解的重要用法，如果前端请求体当中提交的数据是JSON格式，那么@RequestBody 可以将提交的JSON格式
的字符串转换成Java对象/
注意：同样需要使用 jackson的依赖

```xml
        <!--        引入jackson依赖，可以将java对象转换为json格式字符串-->
<!--        专门负责将Java对象转换成JSON格式字符串的组件，
当然，它也可以将JSON格式的字符串转换成Java对象-->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.0</version>
</dependency>
```

然后，要注意 @RequestBody 标注在处理器方法的形参上，也就是说形参只要准备一个user对象就行了
前端提交一个json字符串，直接将其转换成Java对象user
以上前端请求提交JSON格式的字符串，那么后端直接将转换成Java对象，这里使用的消息转换器是:MappingJackson2HttpMessageConverter


RequestEntity
这个类的实例封装了整个请求协议
SpringMVC 自动创建好，传递给处理器方法的参数上
你只需要在处理器方法的参数上加上:(RequestEntity requestEntity)即可，SpringMVC自动创建好该对象，传递
到处理器方法的参数上。
通过它可以获取请求协议中任何信息，包括：请求方法，请求头，请求体

ResponseEntity
ResponseEntity不是注解，是一个类，用该类的实例可以封装响应协议，包括：状态行，响应头，响应体，也就是说
如果你想定制属于自己的响应协议，可以使用该类
注意：如果你要定制属于自己的响应协议，那么处理器方法的返回值类型必须是：ResponseEntity<User> ,泛型为什么是 User,
因为响应体中的内容是 user 对象的信息