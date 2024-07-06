package com.rainbowsea.springmvc.controller;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.VariableElement;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

@Controller
public class FileController {


    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws IOException {
        File file = new File(request.getServletContext().getRealPath("/upload") + "xiaomu.jpeg");

        // 创建响应头对象
        HttpHeaders httpHeaders = new HttpHeaders();

        // 设置响应内容类型
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置下载文件的名称
        httpHeaders.setContentDispositionFormData("attachment",file.getName());

        // 下载文件
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()), httpHeaders, HttpStatus.OK);

        return entity;
    }





    @RequestMapping(value = "/fileup",method = RequestMethod.POST)
    public String fileup(@RequestParam("fileName")MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        // 获取请求参数的名字
        String name = multipartFile.getName();
        System.out.println(name);  // fileName (前端    文件上传: <input type="file" name="fileName"><br> )


        // 获取的是文件真实的名字
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);  // xiaomu


        // 接下来就是看在 Java SE 当中的IO 功底了
        // 一边读，一边写
        //读什么？写什么
        // 读客户端传过来的文件，写倒服务器上
        // 输入流
        InputStream in = multipartFile.getInputStream(); // 输入流，负责读客户端的文件
        BufferedInputStream bis = new BufferedInputStream(in); // 封装成带有缓冲区的输入流

        // 输出流
        //BufferedOutputStream 文件的路径 = new BufferedOutputStream((new FileOutputStream("文件的路径")));
        ServletContext application = request.getServletContext();
        String realPath = application.getRealPath("/upload");
        File file = new File(realPath);
        // 文件不存在，就创建，存在不创建
        if(!file.exists()) {
            file.mkdirs();
        }

        File destFile = new File(file.getAbsoluteFile() + "/" + originalFilename);
        //BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFile));

        // 可以采用UUID来生成文件名，防止服务器上传文件时产生覆盖
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath() + "/" + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."))));


        // 一边读一边写
        byte[] bytes = new byte[1024 * 10];
        int readCount = 0;
        while ((readCount = bis.read(bytes))!=-1) {
            bufferedOutputStream.write(bytes,0,readCount);
        }

        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bis.close();
        return "ok";
    }



    //@RequestMapping(value = "/fileup", method = RequestMethod.POST)
   /* public String fileUp(@RequestParam("fileName") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String name = multipartFile.getName();
        System.out.println(name);
        // 获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        // 将文件存储到服务器中
        // 获取输入流
        InputStream in = multipartFile.getInputStream();
        // 获取上传之后的存放目录
        File file = new File(request.getServletContext().getRealPath("/upload"));
        // 如果服务器目录不存在则新建
        if(!file.exists()){
            file.mkdirs();
        }
        // 开始写
        //BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath() + "/" + originalFilename));
        // 可以采用UUID来生成文件名，防止服务器上传文件时产生覆盖
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file.getAbsolutePath() + "/" + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."))));
        byte[] bytes = new byte[1024 * 100];
        int readCount = 0;
        while((readCount = in.read(bytes)) != -1){
            out.write(bytes,0,readCount);
        }
        // 刷新缓冲流
        out.flush();
        // 关闭流
        in.close();
        out.close();

        return "ok";
    }*/
}
