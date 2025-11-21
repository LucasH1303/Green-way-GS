package com.greenway.greenway.service;

import com.greenway.greenway.model.User;
import com.greenway.greenway.dto.UserDTO;
import com.greenway.greenway.mapper.UserMapper;
import com.greenway.greenway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private MessageSource messageSource;

    // CREATE USER
    public UserDTO create(UserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("user.email.duplicate");
        }

        User user = mapper.toEntity(dto);

        user.setPoints(0);
        user.setPassword(""); // se houver lógica de senha, implementar depois

        user = userRepository.save(user);

        return mapper.toDto(user);
    }

    // FIND ALL USERS
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(mapper::toDto)
                .toList();
    }

    // FIND USER BY ID
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user.notfound"));
        return mapper.toDto(user);
    }

    // UPDATE USER
    public UserDTO update(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user.notfound"));

        mapper.updateEntityFromDto(dto, user); // mapeia name, email, phone, address, transportMode

        user = userRepository.save(user);

        return mapper.toDto(user);
    }

    // DELETE USER
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("user.notfound");
        }
        userRepository.deleteById(id);
    }
}
