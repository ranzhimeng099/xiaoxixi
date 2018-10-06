package com.xxrr.home.service.impl;

import com.xxrr.home.service.TestAopCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestAopCacheServiceImpl implements TestAopCacheService {

    @Override
    @Cacheable(value = "cmbLocalMap" + "#{20}", key = "'baby'")
    public String testAopCache() {
        System.out.println("I'm in!");
        return "I have a baby.";
    }
}
