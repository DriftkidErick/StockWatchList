package com.erick.stockwatchlist.service;

import com.erick.stockwatchlist.model.User;
import com.erick.stockwatchlist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

//    Writes to repo
    public User saveUser(User user){
        return userRepository.save(user);
    }

//    Reads from repo
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
