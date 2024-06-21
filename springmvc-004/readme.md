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
