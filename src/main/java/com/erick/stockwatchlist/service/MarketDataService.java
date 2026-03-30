package com.erick.stockwatchlist.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketDataService {

//    Injecting the private Key
    @Value("${twelvedata.api.key}")
    private String apiKey;

//    Used to make HTTP requests
    private final RestTemplate restTemplate;

//    Converts Json to Java Objects
    private final ObjectMapper objectMapper;

//    Constructor
    public MarketDataService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

//    Main methof
    public double getCurrentPrice(String ticker) {
        try {
//            builds the API url
            String url = "https://api.twelvedata.com/quote?symbol=" + ticker + "&apikey=" + apiKey;

//           Call the API
            String response = restTemplate.getForObject(url, String.class);

//            Convert JSON to Readable Format
            JsonNode root = objectMapper.readTree(response);

            if (root.has("close")) {
                return root.get("close").asDouble();
            }

            if (root.has("price")) {
                return root.get("price").asDouble();
            }

//            If nothing found throw exception
            throw new RuntimeException("No price found for ticker: " + ticker);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch current price for ticker: " + ticker, e);
        }
    }
}