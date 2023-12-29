package com.example;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyCache {


    private static Cache<String,String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(20000)
            .build();

    public static Cache<String, String> getCache() {
        return cache;
    }

    private static Map<String, BigInteger> hCash = new HashMap<>();

    public static void getCache(Cache<String, String> cache) {
        MyCache.cache = cache;
    }

    public static Map<String, BigInteger> gethCash() {
        return hCash;
    }

    public static void sethCash(Map<String, BigInteger> hCash) {
        MyCache.hCash = hCash;
    }
}
