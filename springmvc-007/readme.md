RESTful 是对 web 服务器接口的一种设计风格，提供了一套约束，可以让 web 服务接口更加简洁，易于理解。
REST对请求方式的约束是这样的 
> 查询 get
> 新增 post
> 删除 delete
> 修改 put

REST对 URL的约束是这样的
GET /user/1 查一个
GET /user 查所有
POST /user 新增
PUT /user 参数
DELETE /user/1 删除

RESTful ，是表述性状态转移
本质上：通过 URL + 请求方式，来控制服务器端数据的变化

RESTful 编程风格中要求，修改的时候，必须提交 PUT 请求，怎么提交 PUT请求呢？
> 第一步: 要想发送PUT请求，首先你必须是一个 POST 请求。(POST 请求是大前提)
> 第二步: 在 POST 请求的表单中添加隐藏域
<!--    隐藏域-->
<input type="hidden" name="_method" value="put">
强调，name必须是 _method，value必须是 put/PUt ，忽略大小写
如果你要发送 delete请求的话，value写 delete即可


第三步: 添加一个过滤器

    <!--    添加一个过滤器，这个过滤器是springmvc提前写好的，直接用就行了，这个过滤器可以帮助你将请求
    POST转换成PUT请求/DELETE请求-->
    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
<!--        表示任意的 请求-->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
