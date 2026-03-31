package com.erick.stockwatchlist.controller;

import com.erick.stockwatchlist.model.StockSearchResult;
import com.erick.stockwatchlist.service.MarketDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "http://localhost:5173")
public class StockController {

    private final MarketDataService marketDataService;

    public StockController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/search")
    public List<StockSearchResult> searchStocks(@RequestParam String query) {
        return marketDataService.searchStocks(query);
    }

    @GetMapping("/price")
    public double getCurrentPrice(@RequestParam String ticker) {
        return marketDataService.getCurrentPrice(ticker);
    }
}
