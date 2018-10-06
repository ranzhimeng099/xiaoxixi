package com.xxrr.home.cache.domain;

import java.io.Serializable;

//null对象，预防缓存被击穿的情况
public class NullObject implements Serializable
{
    private static final long serialVersionUID = 1L;
}
