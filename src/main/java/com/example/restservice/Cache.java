package com.example.restservice;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Cache {

    private final HashMap<CacheKey, Integer> map = new HashMap<>();

    public void add(CacheKey key, Integer value) {
        map.put(key, value);
        MyLogger.info("Cache: " + key + ", " + value + " added to cache");
    }

    public boolean isCached(CacheKey key) {
        boolean result = map.containsKey(key);
        MyLogger.info("Cache contains " + key + ": " + result);
        return result;
    }

    public Integer get(CacheKey key) throws Exception {
        try {
            Integer value = map.get(key);
            MyLogger.info("Cache get \"" + key + "\": " + value);
            return value;
        } catch (Exception exception) {
            MyLogger.warn("Key \"" + key + "\" not found in cache");
            throw new Exception("Not found in cache");
        }
    }
}

