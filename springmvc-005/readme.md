重定向是两次请求。


# request请求域
第一种方式: 在Spring MVC 中使用原生的 Servlet API 可以完成 request 域数据共享
    在处理器方法上添加 HttpServletRequest 参数即可
第二种方式： 在 Spring MVC的处理器方法的参数上添加一个接口类型 Model
@RequestMapping(value = "/testModel")
public String testModel(Model model) {
// 向 request 域当中绑定数据
model.addAttribute("testRequestScope", "在SpringMVC 当中使用 Model 接口完成 request 域数据共享");
// 转发
return "ok";
}

MVC架构模式:
M:Model （模型，本质就是数据）
V: View
C: Controller

第三种方式: 在Spring MVC 的处理方法的参数上添加一个接口类型 : Map
@RequestMapping(value = "/testMap")
public String  testMap(Map<String,Object> map) {

        // 向 request 域当中的存储数据
        map.put("testRequestScope","在Spring MVC 当中使用 Map接口完成 request 域数据共享");
        // 转发
        return "ok";
    }

第四种方式使用： ModelMap
研究一下，Model接口，Map接口，ModelMap类，三者之间的关系？
>表面上使用的是不同的接口和不同的类，实际上底层都使用了同一个对象，org.springframework.validation.support.BindingAwareModelMap
>继承了
> BindingAwareModelMap 继承了 ExtendedModelMap类
> ExtendModelMap继承了 ModelMap类
> ExtendModelMap 实现了Model接口
> ModelMap 类继承了 LinkedMahsMap继承了 HashMap实现了Map接口


第五种方式：

    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
        // 创建模型视图对象
        ModelAndView modelAndView = new ModelAndView();
        // 给模型视图对象绑定数据
        modelAndView.addObject("testRequestScope","在SpringMVC当中使用 ModelAndView 类完成 request 域数据共享");

        // 给模型视图对象 绑定视图（绑定逻辑视图名称）
        modelAndView.setViewName("ok");

        // 返回模型视图对象
        return modelAndView;
    }
view + Model = ModelAndView对象
返回的一定是 ModelAndView对象，然后这个对象给DispatcherServlet

mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
我们聊一聊真相：
对于处理器方法来说，不管你使用的参数是 Model接口，还是Map接口，还是ModelMap类，还是ModelAndView类，最终处理器方法执行结束之前
返回的都是 ModelAndView对象，这个返回的ModelAndView对象给DispatcherServlet类了
当请求路径不是JSP的时候，都会走前端控制器 DispatcherServlet
DispatcherServlet 中有一个核心方法 doDispatch(),这个方法用来通过请求路径找到对应的处理器方法
然后调用处理器方法，处理器方法返回一个逻辑视图名称（可能也会直接返回一个ModelAndView对象）
，返回给DispatcherServlet


Session 会话域
第一种方式使用原生的Servlet API 实现（在处理器方法的参数上添加一个 HttpSession 参数，Spring MVC 会自动将session对象传递给这个参数）
第二种方式: 使用@SessionAttributes注解标注 Controller

application域
这个域使用较少，如果使用的话，一般是采用 ServletAPI的方式使用

    @RequestMapping("/testApplicationScope")
    public String testApplicationScope(HttpServletRequest request) {
        // 将数据存储到application域当中
        // 获取application对象，其实就是获取 ServletContext对象
        // 怎么获取 ServletContext对象/通过 request,通过 session都可以用
        ServletContext application = request.getServletContext();
        application.setAttribute("testApplicationScope", "在Spring MVC 中使用 Servlet API中实现application域共享");
        return "ok";
    }

