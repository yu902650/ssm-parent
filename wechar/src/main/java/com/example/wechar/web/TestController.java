package com.example.wechar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bo bo
 * @date 2019/6/17 13:22
 * @desc
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @RequestMapping("/a")
    public String hello() {
        return  "Welcome to West World : ☁ ☀ 😊 😄 🌏   我来自3101端口" +
                "==================================================="+
                "Welcome to West World : ☁ ☀ 😊 😄 🌏   我来自3101端口" ;
    }
}
