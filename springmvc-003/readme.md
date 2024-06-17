# @RequestMapping 注解的属性值


## 1. value值 / path 值，都是数组，都是一样的效果

```java

@Controller // 交给Spring IOC 容器管理
@RequestMapping(value = "/a")
public class ProductController {
    
    @RequestMapping(value = "/b")
    public String productDetail() {

        return "product_detail";
    };
```
> 前端浏览器发送的请求路径是: /a/b 的时候，则执行ProductController#productDetail()方法
> 关于 @RequestMapping 注解的 value 属性
> value属性本身是一个 String[] 字符串数组，说明多个请求路径可以映射同一个处理器方法。
> 如果注解的属性是数组，并且在使用注解的时候，该数组中只有一个元素，大括号可以省略
> 如果使用某个注解的时候，如果只使用了一个 value属性，那么 value 也是可以省略的
>  value 属性的别名是 path
> path 属性的别名是 value
> path 和 value 是同一个东东
> 

RequestMapping 的 value 属性支持 Ant 风格的，支持模糊匹配的路径
> ？ 表示任意一个字符。（除 / ？ 之外的其它字符，注意，一定是一个字符，同时也不可以空着）
> “*” 表示0到 N 个任意字符，（除 / ? 之外的其它字符）
> “**” 表示 0 到 N 个任意子u，并且路径中可以出现 /
> 但是 ** 在使用的时候需要注意，**左边只能是 /
> 同时注意：
>   如果使用Spring5以及之后的版本，这样写是没问题的，@requestMapping(value = "/**/testAntValues")
>   如果使用Spring6以及之后的版本，这样写是报错的。，@requestMapping(value = "/**/testAntValues")
>   在Spring6当中 ，** 通配符只能作为路径的末尾出现
>  @RequestMapping(value = "/testAntValue/**") Spring65都可以
> 
> 关于 @RequestMapping 注解的value属性上的占位符（重点）
> 传统的URL ：
>       /springmvc/login?username = admin&password=123
> 现在的开发比较流行使用RESTFul风格的URL：
>       /springmvc/login/admin/123

在SpringMVC 当中，如果请求的URL使用的是RESTFul风格，那么这个数据应该在Java程序中如何获取？使用占位符方式。
```java
  @RequestMapping(value = "/login/{a}/{b}")
    public String testRESTFulURL(@PathVariable("a") String username, @PathVariable("b") String password) {
        System.out.println("用户名： " + username + " 密码: " + password);
        return "ok";
    }
```

 关于@RequestMapping 注解的 method 属性，通过该属性可以限制前端发送的请求方式，如果前端发送的请求方式
与后端的处理方式不同，则出现405错误。
只要有一个不满足，则无法映射，例如：请求路径对应不上，或者请求方式对应不上你，都是无法映射的。
```java
    @RequestMapping(value = "/user/login",method = {RequestMethod.POST})
    public String userLogin() {
        System.out.println("处理登陆的业务...");
        return "ok";
    }
    // Method 'GET' is not supported.
```

Method 'GET' is not supported.
衍生Mapping
@PostMapping 注解代替的是：@RequestMapping(value="", method=RequestMethod.POST)
@GetMapping 注解代替的是：@RequestMapping(value="", method=RequestMethod.Get)
...
@PutMapping
@DeleteMapping
@PatchMapping

web 的请求方式：
比较常用：
get post put delete hean
get: 适合查询
post: 适合新增
PUT: 适合修改
delete: 适合删除
head: 适合获取响应头信息

注意：使用form表单提交时，如果 method 设置为 put delete head，对不起，发送的请求还是 get 请求
如果发送 put delete head 请求，请发送 ajax请求才可以。

关于 RequestMapping 注解的 params 属性
```java

    // 当请求路径是 /testParams，并且提交的参数包括 username 和 password 时，才能映射成功
    @RequestMapping(value = "/testParams", params = {"username","password"})
    //@RequestMapping(value = "/testParams", params = {"username=zhangsan","password"})
    //@RequestMapping(value = "/testParams", params = {"username!=zhangsan","password"})
    //@RequestMapping(value = "/testParams", params = {"!username","password"})

    public String testParam() {
        return "ok";
        // Invalid request parameters.
    }
```
> 当RequestMapping 注解当中条件了 params ，则表示添加了新的约束条件
> 
> 关于 thymeleaf 中怎么发送请求的时候携带数据：
> <a th:href="@{testParams?username=admin&password=123}">测试RequestMapping 注解的 params 属性</a><br>
<!--注意：单双引号交替使用-->
<a th:href="@{testParams(username='admin',password='123')}">测试RequestMapping 注解的 params 属性2</a>
> 
> 

属性越多，约束条件就越多。

