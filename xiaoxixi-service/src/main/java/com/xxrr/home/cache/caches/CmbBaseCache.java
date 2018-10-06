package com.xxrr.home.cache.caches;

import org.springframework.cache.Cache;

public abstract class CmbBaseCache implements Cache {

    protected ThreadLocal<Long> exTimes = new ThreadLocal<Long>();

    public ThreadLocal<Long> getExTimes() {
        return exTimes;
    }
}
