package com.se7en.URLshortner.Services;

import com.se7en.URLshortner.Payloads.URLDTO;

import java.util.List;
import java.util.Optional;

public interface URLService {
    // Create a new URL or update an existing URL
    URLDTO saveURL(URLDTO urlDTO);

    // Find a URL by ID
    Optional<URLDTO> findURLById(Long urlId);

    // Delete a URL by ID
    void deleteURL(Long urlId);

    // List all URLs for a user
    List<URLDTO> getURLsForUser(Long userId);

    //edit the URL
    URLDTO updateURL(Long urlId, String customKeyword, int expiryDays);
}
