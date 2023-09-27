package com.se7en.URLshortner.Repos;

import com.se7en.URLshortner.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
