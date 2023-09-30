package com.se7en.URLshortner.Repos;

import com.se7en.URLshortner.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);


}
