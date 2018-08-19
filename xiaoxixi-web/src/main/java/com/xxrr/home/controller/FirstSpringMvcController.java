package com.xxrr.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zx")
public class FirstSpringMvcController {
    @RequestMapping("/FirstSpringMvcController.do")
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.getWriter().println("test controller");
    }

    @RequestMapping("/testResponseBody.do")
    @ResponseBody
    public Map testResponseBody(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Map<String,String> testMap=new HashMap<>();
        testMap.put("s1","hello");
        testMap.put("s2","world");
        return testMap;
    }
}
