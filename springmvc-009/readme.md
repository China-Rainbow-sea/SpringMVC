文件上传:
文件上传必须是 post 请求，因为文件是大数据。get 请求不够
文件上传的form标签中必须使用 enctype="multipart/form-data"
enctype是用来设置请求头的内容类型的，默认值是: enctype="application/x-www-form-urlencoded"
文件上传的组件是: 文件上传: <input type="file" name="fileName"><br>
注意：如果你用的是:Spring6 ，那么需要在web.xml 文件的 DispatcherServlet中进入如下的配置：
<multipart-config>
<!--设置单个支持最大文件的大小-->
<max-file-size>102400</max-file-size>
<!--设置整个表单所有文件上传的最大值-->
<max-request-size>102400</max-request-size>
<!--设置最小上传文件大小-->
<file-size-threshold>0</file-size-threshold>
</multipart-config>
文件上传：浏览器端向服务器发送文件，最终服务器将文件保存到服务器上