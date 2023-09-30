package com.se7en.URLshortner.Services;

import com.se7en.URLshortner.Entities.User;
import com.se7en.URLshortner.Exceptions.ResourceNotFound;
import com.se7en.URLshortner.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // load user from database
        User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFound("User", "email" , username));

        return user;
    }
}
