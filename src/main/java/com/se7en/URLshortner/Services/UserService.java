package com.se7en.URLshortner.Services;

import com.se7en.URLshortner.Payloads.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // Create a new user or update an existing user
    UserDTO saveUser(UserDTO userDTO);

    // Find a user by ID
    Optional<UserDTO> findUserById(Long userId);

    // Find a user by email
    Optional<UserDTO> findUserByEmail(String email);

    // Delete a user by ID
    void deleteUser(Long userId);

    // List all users
    List<UserDTO> getAllUsers();
}
