package com.erick.stockwatchlist.service;

import com.erick.stockwatchlist.model.WatchlistItem;
import com.erick.stockwatchlist.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final MarketDataService marketDataService;

//    Constructor Injection
    public WatchlistService(WatchlistRepository watchlistRepository,
                            MarketDataService marketDataService) {
        this.watchlistRepository = watchlistRepository;
        this.marketDataService = marketDataService;
    }

    public WatchlistItem saveWatchlistItem(WatchlistItem item) {
        return watchlistRepository.save(item);
    }

    public List<WatchlistItem> getWatchlist() {
        List<WatchlistItem> items = watchlistRepository.findAll();

//        For each item in the watch list perform this
        for (WatchlistItem item : items) {

//            Get the current price
            double currentPrice = marketDataService.getCurrentPrice(item.getTicker());

//            Calculations
            double priceChange = roundToTwoDecimals(currentPrice - item.getSavedPrice());
            double percentChange = roundToTwoDecimals((priceChange / item.getSavedPrice()) * 100);

            item.setCurrentPrice(currentPrice);
            item.setPriceChange(priceChange);
            item.setPercentChange(percentChange);
        }

        return items;
    }

    public List<WatchlistItem> getWatchlistByUserId(Long userId) {
        return watchlistRepository.findByUserId(userId);
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}