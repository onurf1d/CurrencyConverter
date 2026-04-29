package com.onur.currencyconverter.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import java.math.BigDecimal;

public class CurrentRateService {

    private static final String BASE_URL = "https://api.frankfurter.app/latest";
    private final CachedRateService cacheManager = new CachedRateService();

    private BigDecimal fetchRate(String from, String to) {
        try {
            String finalUrl = String.format("%s?from=%s&to=%s", BASE_URL, from, to);

            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(finalUrl)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return parseRateFromJson(response.body(), to);
            } else {
                System.err.println("API Error: " + response.statusCode());
            }
        }catch(Exception e) {
                System.err.println("Connection Error: " + e.getMessage());
            }

        return BigDecimal.ZERO;
        }

        private BigDecimal parseRateFromJson(String jsonBody, String to) {
            JSONObject obj = new JSONObject(jsonBody);

            BigDecimal rateValue = obj.getJSONObject("rates").getBigDecimal(to.toUpperCase());

            return rateValue;
        }

        public BigDecimal getRate(String from, String to) {
            CachedRate cached = cacheManager.get(from, to);

            if (cached != null) {
                return cached.getRate();
            }

            BigDecimal newRate = fetchRate(from, to);

            cacheManager.put(from, to, newRate);

            return newRate;
        }

}

