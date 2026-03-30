package com.erick.stockwatchlist.repository;

import com.erick.stockwatchlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


}
