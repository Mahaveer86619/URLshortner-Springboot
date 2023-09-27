package com.se7en.URLshortner.Repos;

import com.se7en.URLshortner.Entities.URL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface URLRepo extends JpaRepository<URL, Long> {
    // Find URLs by user ID
    List<URL> findByUserId(Long userId);
    // Find a URL by short key
    Optional<URL> findByShortKey(String shortKey);

}
