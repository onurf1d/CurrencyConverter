package com.onur.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrentRateService {

    private static final String BASE_URL = "https://api.frankfurter.app/latest";

    public String getFinalUrl(String from, String to) {
        return String.format("%s?from=%s&to=%s", BASE_URL, from, to);
    }
    public void fetchRate(String from, String to) {
        String finalUrl = getFinalUrl(from, to);
    }
}
