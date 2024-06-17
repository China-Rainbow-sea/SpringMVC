package com.rainbowsea.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 交给Spring IOC 容器管理
@RequestMapping(value = "/product")
public class ProductController {


    //@RequestMapping(value = "/product/detail")
    @RequestMapping(value = "/detail")
    public String productDetail() {

        return "product_detail";
    }


}
