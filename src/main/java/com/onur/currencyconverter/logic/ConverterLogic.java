package com.onur.currencyconverter.logic;

import java.math.BigDecimal;
import com.onur.currencyconverter.service.*;

public class ConverterLogic {

    private BigDecimal result;
    private CurrentRateService crs = new CurrentRateService();
    private String fromToString = "";
    private BigDecimal cachedRate;

    public BigDecimal convert(String from, String to, BigDecimal amount) {

        if(isSame(from, to)) {
            return cachedRate.multiply(amount);
        }
        else {
            BigDecimal rate = crs.getRate(from, to);
            cachedRate = rate;
            return rate.multiply(amount);
        }
    }

    private boolean isSame(String from, String to) {
        String newFromToString = from + "-" + to;

        if(fromToString.equals(newFromToString)) {
            return true;
        }
        else {
            fromToString = newFromToString;
            return false;
        }
    }
}
