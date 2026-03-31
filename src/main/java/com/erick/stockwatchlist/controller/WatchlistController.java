package com.erick.stockwatchlist.controller;

import com.erick.stockwatchlist.model.WatchlistItem;
import com.erick.stockwatchlist.service.WatchlistService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
@CrossOrigin(origins = "http://localhost:5173")
public class WatchlistController {

    private final WatchlistService watchlistService;

    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    @GetMapping
    public List<WatchlistItem> getWatchlist(Authentication authentication) {
        String username = authentication.getName();
        return watchlistService.getWatchlistForUser(username);
    }

    @PostMapping
    public WatchlistItem createWatchlistItem(@RequestBody WatchlistItem item,
                                             Authentication authentication) {
        String username = authentication.getName();
        return watchlistService.saveWatchlistItem(item, username);
    }

    @DeleteMapping("/{id}")
    public void deleteWatchlistItem(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        watchlistService.deleteWatchlistItem(id, username);
    }
}