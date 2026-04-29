package com.onur.currencyconverter.service;

import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

public class CachedRateService {

    private final Map<String, CachedRate> cache = new HashMap<>();

    public CachedRate get(String from, String to) {
        String key = (from + "-" + to).toUpperCase();
        CachedRate value = cache.get(key);

        if (value != null && value.isExpired()) {
            cache.remove(key);
            return null;
        }
        return value;
    }

    public void put(String from, String to, BigDecimal rate) {
        cache.put((from + "-" + to).toUpperCase(), new CachedRate(rate));
    }

}
