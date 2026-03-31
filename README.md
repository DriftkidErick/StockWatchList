# StockWatchlist Backend

A Spring Boot backend for a stock watchlist application that lets users register, authenticate, save stocks to a personal watchlist, and track live price changes using the Twelve Data API.

## Features

- User registration with hashed passwords using BCrypt
- Authentication with Spring Security
- Protected watchlist endpoints
- PostgreSQL database integration with Spring Data JPA
- User-to-watchlist relationship
- Live stock price lookup using Twelve Data API
- Backend-calculated:
    - current price
    - price change
    - percent change
- User-specific watchlist access

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven
- Twelve Data API

## Project Structure

```text
src/main/java/com/erick/stockwatchlist
├── config
│   └── SecurityConfig.java
├── controller
│   ├── UserController.java
│   └── WatchlistController.java
├── model
│   ├── User.java
│   └── WatchlistItem.java
├── repository
│   ├── UserRepository.java
│   └── WatchlistRepository.java
├── service
│   ├── CustomUserDetailsService.java
│   ├── MarketDataService.java
│   ├── UserService.java
│   └── WatchlistService.java
└── StockwatchlistApplication.java