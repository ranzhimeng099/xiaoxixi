package com.xxrr.home.action;

import com.xxrr.home.domain.result.Result;
import com.xxrr.home.service.FirstSpringService;
import org.springframework.beans.factory.annotation.Autowired;

public class FirstSpringAction extends BaseAction {

    @Autowired
    public FirstSpringService firstSpringService;

    public Result execute() {
        Result result = new Result();
        result.put("result", firstSpringService.xx2rr());
        return result;
    }
}
