package com.xxrr.home.cache.caches;

import com.xxrr.home.cache.domain.LocalMapData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CmbLocalCache extends CmbBaseCache implements InitializingBean {
    private Logger LOGGER = LoggerFactory.getLogger(CmbLocalCache.class);

    private String name;

    private ConcurrentMap<String, LocalMapData> store;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getNativeCache() {
        return store;
    }

    @Override
    public ValueWrapper get(Object key) {
        String keyStr = String.valueOf(key);
        if (StringUtils.isEmpty(keyStr)) {
            return null;
        }
        LocalMapData localMapData = store.get(keyStr);
        if (localMapData == null) {
            return null;
        }
        long expiretime = localMapData.getExpiretime();
        long now = System.currentTimeMillis();
        // 判断是否超过缓存时间
        if (now > expiretime) {
            store.remove(keyStr);
            return null;
        }
        Object data = localMapData.getData();
        return new SimpleValueWrapper(data);
    }

    @Override
    public void put(Object key, Object value) {
        // 本地缓存默认5分钟
        long exTime = exTimes.get() == null ? 300L : exTimes.get();
        if (exTime < System.currentTimeMillis()) {
            exTime = System.currentTimeMillis() + exTime * 1000; // 超过这个时间点及失效
        }
        LocalMapData localMapData = new LocalMapData(value, exTime);
        store.put(String.valueOf(key), localMapData);
    }

    @Override
    public void evict(Object key) {
        store.remove(String.valueOf(key));
    }

    @Override
    public void clear() {
        store.clear();
    }

    @Override
    public void afterPropertiesSet() {
        store = new ConcurrentHashMap<>();
    }
}
