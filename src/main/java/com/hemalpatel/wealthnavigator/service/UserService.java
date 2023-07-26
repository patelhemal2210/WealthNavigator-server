package com.hemalpatel.wealthnavigator.service;

import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void block(User user) {
        user.setLocked(true);
        userRepository.save(user);
    }
}
