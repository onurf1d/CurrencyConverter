package com.onur.currencyconverter.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class CachedRate {

    private final BigDecimal rate;
    private final LocalDateTime time;

    public CachedRate(BigDecimal rate) {
        this.rate = rate;
        this.time = LocalDateTime.now();
    }



    public boolean isExpired() {
        return time.isBefore(LocalDateTime.now().minusMinutes(30));

    }

    public BigDecimal getRate() {
        return rate;
    }

}
