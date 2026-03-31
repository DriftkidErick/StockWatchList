package com.erick.stockwatchlist.repository;

import com.erick.stockwatchlist.model.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<WatchlistItem, Long> {
    List<WatchlistItem> findByUserId(Long userId);
    Optional<WatchlistItem> findByUserIdAndTickerIgnoreCase(Long userId, String ticker);
}