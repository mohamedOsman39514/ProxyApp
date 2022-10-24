package com.example.proxy.service;

import com.example.proxy.model.ServiceType;
import com.example.proxy.model.User;
import com.example.proxy.repository.ServiceTypeRepository;
import com.example.proxy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }

}
