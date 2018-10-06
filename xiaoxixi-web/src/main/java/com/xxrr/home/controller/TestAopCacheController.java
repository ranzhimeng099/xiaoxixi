package com.xxrr.home.controller;

import com.xxrr.home.service.TestAopCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/testCache")
public class TestAopCacheController {

    @Autowired
    private TestAopCacheService testAopCacheService;

    @RequestMapping("/testCache.do")
    @ResponseBody
    public String testCache(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return testAopCacheService.testAopCache();
    }
}
