package com.sofistai.back.service;

import com.sofistai.back.dto.UserRequestDTO;
import com.sofistai.back.mapper.UserMapper;
import com.sofistai.back.model.User;
import com.sofistai.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.userRequestToNewUser(userRequestDTO);
        return userRepository.save(user);
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
