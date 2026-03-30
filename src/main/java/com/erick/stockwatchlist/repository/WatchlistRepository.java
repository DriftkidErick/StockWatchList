package com.erick.stockwatchlist.repository;

import com.erick.stockwatchlist.model.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<WatchlistItem, Long>{
    List<WatchlistItem> findByUserId(Long userId);

}

