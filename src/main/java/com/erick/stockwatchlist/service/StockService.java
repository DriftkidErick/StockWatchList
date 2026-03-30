package com.erick.stockwatchlist.service;

import com.erick.stockwatchlist.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    public List<Stock> getStocks(){
        List<Stock> stocks = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setId(1L);
        stock1.setTicker("AAPL");
        stock1.setCompanyName("Apple Inc.");
        stock1.setCurrentPrice(175.50);

        Stock stock2 = new Stock();
        stock2.setId(2L);
        stock2.setTicker("NVDA");
        stock2.setCompanyName("NVIDIA");
        stock2.setCurrentPrice(135.20);

        stocks.add(stock1);
        stocks.add(stock2);

        return stocks;
    }
}


