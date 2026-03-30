package com.erick.stockwatchlist.controller;

import com.erick.stockwatchlist.model.WatchlistItem;
import com.erick.stockwatchlist.service.WatchlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

//    Returns all Watchlist items
    @GetMapping
    public List<WatchlistItem> getWatchlist() {
        return watchlistService.getWatchlist();
    }

//    Returns only the watchlist items for one user.
    @GetMapping("/user/{userId}")
    public List<WatchlistItem> getWatchlistByUserId(@PathVariable Long userId) {
        return watchlistService.getWatchlistByUserId(userId);
    }

//    Creates a new watchlist item in the database.
    @PostMapping
    public WatchlistItem createWatchlistItem(@RequestBody WatchlistItem item) {
        return watchlistService.saveWatchlistItem(item);
    }
}
