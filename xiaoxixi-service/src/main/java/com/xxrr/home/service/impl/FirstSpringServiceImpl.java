package com.xxrr.home.service.impl;

import com.xxrr.home.aop.TestAopAnot;
import com.xxrr.home.service.FirstSpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstSpringServiceImpl implements FirstSpringService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FirstSpringServiceImpl.class);

    @TestAopAnot
    @Override
    public String xx2rr() {
        LOGGER.info("Wish us happy all life.");
        return "Wish us happy all life.";
    }
}
