package com.xxrr.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/zx")
public class FirstSpringMvcController {
    @RequestMapping("/FirstSpringMvcController.do")
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.getWriter().println("test controller");
    }
}
