package com.se7en.URLshortner.Services.Impl;


import com.se7en.URLshortner.Entities.User;
import com.se7en.URLshortner.Exceptions.ResourceNotFound;
import com.se7en.URLshortner.Payloads.UserDTO;
import com.se7en.URLshortner.Repos.UserRepo;
import com.se7en.URLshortner.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        User user = this.mapper.map(userDTO, User.class);
        User savedUser = this.repo.save(user);

        return this.mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public Optional<UserDTO> findUserById(Long userId) {

        User user = this.repo.findById(userId).orElseThrow
                (() -> new ResourceNotFound("User", "User Id", userId));

        return Optional.ofNullable(this.mapper.map(user, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {

        Optional<User> user = this.repo.findByEmail(email);

        return Optional.ofNullable(this.mapper.map(user, UserDTO.class));
    }

    @Override
    public void deleteUser(Long userId) {
        this.repo.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.repo.findAll();

        return users.stream()
                .map(user -> this.mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

}
