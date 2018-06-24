package com.xxrr.home.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Configuration
public class TestAopAnotHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestAopAnotHandler.class);

    @Pointcut("@annotation(com.xxrr.home.aop.TestAopAnot)")
    // @Pointcut("execution(* com.tower.service.towerSpring.impl.TowerSpringServiceImpl.xx2rr(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("I'm a Logger! Yes, life is hard, but we will success!");
    }
}
