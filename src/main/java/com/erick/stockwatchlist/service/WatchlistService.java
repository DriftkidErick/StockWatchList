package com.erick.stockwatchlist.service;

import com.erick.stockwatchlist.model.User;
import com.erick.stockwatchlist.model.WatchlistItem;
import com.erick.stockwatchlist.repository.WatchlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final MarketDataService marketDataService;
    private final UserService userService;

    public WatchlistService(WatchlistRepository watchlistRepository,
                            MarketDataService marketDataService,
                            UserService userService) {
        this.watchlistRepository = watchlistRepository;
        this.marketDataService = marketDataService;
        this.userService = userService;
    }

//    Allows users to save item to a watchlist per thier LOG IN
    public WatchlistItem saveWatchlistItem(WatchlistItem item, String username) {
        validateWatchlistItem(item);

        User user = userService.getUserByUsername(username);
        item.setUser(user);

        //Checks that the ticker isnt already saved
        if (watchlistRepository.findByUserIdAndTickerIgnoreCase(user.getId(), item.getTicker()).isPresent()) {
            throw new RuntimeException("This ticker is already in your watchlist.");
        }

        WatchlistItem savedItem = watchlistRepository.save(item);
        applyLiveCalculations(savedItem);

        return savedItem;
    }

//    Gets watchlist for logged in user
    public List<WatchlistItem> getWatchlistForUser(String username) {
        User user = userService.getUserByUsername(username);
        List<WatchlistItem> items = watchlistRepository.findByUserId(user.getId());

        for (WatchlistItem item : items) {
            applyLiveCalculations(item);
        }

        return items;
    }

//    This allows the calcualtions to be available during the POST
    private void applyLiveCalculations(WatchlistItem item) {
        double currentPrice = marketDataService.getCurrentPrice(item.getTicker());
        double priceChange = roundToTwoDecimals(currentPrice - item.getSavedPrice());
        double percentChange = roundToTwoDecimals((priceChange / item.getSavedPrice()) * 100);

        item.setCurrentPrice(currentPrice);
        item.setPriceChange(priceChange);
        item.setPercentChange(percentChange);
    }

//    Validates that the information is available
private void validateWatchlistItem(WatchlistItem item) {
//        Ensures that if there is an error it will throw a 400 Bad request
    if (item.getTicker() == null || item.getTicker().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticker is required.");
    }

    if (item.getCompanyName() == null || item.getCompanyName().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company name is required.");
    }

    if (item.getSavedPrice() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saved price is required.");
    }

    if (item.getSavedPrice() <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saved price must be greater than 0.");
    }
}

//Used to round the number to two decimals
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}