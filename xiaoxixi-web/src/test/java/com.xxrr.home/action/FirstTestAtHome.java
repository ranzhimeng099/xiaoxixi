package com.xxrr.home.action;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstTestAtHome {

    private final static Logger LOGGER = LoggerFactory.getLogger(FirstTestAtHome.class);

    @Test
    public void firstTestMethod() {

        System.out.println("junit success");

        LOGGER.info("I'm logger! {}, {}", "我是占位符", "我也是占位符");

    }
}
