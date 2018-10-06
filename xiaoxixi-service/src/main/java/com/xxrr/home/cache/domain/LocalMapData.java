package com.xxrr.home.cache.domain;

public class LocalMapData {
    private Object data;
    private Long expiretime;

    public LocalMapData(Object data, long expiretime) {
        this.data = data;
        this.expiretime = expiretime;
    }

    public Long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Long expiretime) {
        this.expiretime = expiretime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
