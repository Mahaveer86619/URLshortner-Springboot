package com.se7en.URLshortner.Services.Impl;

import com.se7en.URLshortner.Entities.URL;
import com.se7en.URLshortner.Exceptions.ResourceNotFound;
import com.se7en.URLshortner.Payloads.URLDTO;
import com.se7en.URLshortner.Payloads.UserDTO;
import com.se7en.URLshortner.Repos.URLRepo;
import com.se7en.URLshortner.Services.URLService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class URLServiceImpl implements URLService {

    @Autowired
    private URLRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public URLDTO saveURL(URLDTO urlDTO) {
        String originalURL = urlDTO.getOriginalURL();
        String shortKey = generateShortKey(); // Generate a unique short key

        URL url = this.mapper.map(urlDTO, URL.class);

        url.setOriginalURL(originalURL);
        url.setShortKey(shortKey);
        url.setCreationTime(urlDTO.getCreationTime()); // Set the creation time
        url.setExpiryDate(3);
        url.setShortURL(urlDTO.getShortURL());

        URL savedURL = this.repo.save(url);

        return this.mapper.map(savedURL, URLDTO.class);
    }

    @Override
    public Optional<URLDTO> findURLById(Long urlId) {
        URL user = this.repo.findById(urlId).orElseThrow
                (() -> new ResourceNotFound("URL", "URL Id", urlId));

        return Optional.ofNullable(this.mapper.map(user, URLDTO.class));
    }

    @Override
    public void deleteURL(Long urlId) {
        this.repo.deleteById(urlId);
    }

    @Override
    public List<URLDTO> getURLsForUser(Long userId) {
        List<URL> userURLs = this.repo.findByUserId(userId);
        return userURLs.stream()
                .map(url -> this.mapper.map(url, URLDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public URLDTO updateURL(Long urlId, String customKeyword, int expiryDays) {
        // Retrieve the URL entity from the database by its ID
        URL url = this.repo.findById(urlId)
                .orElseThrow(() -> new ResourceNotFound("URL", "URL Id", urlId));

        // Check if the user is the owner of the URL (you may need to implement user authentication)
        // For this example, we assume that the user is allowed to edit any URL
        // You can add user authentication and authorization checks here

        // Update the custom keyword if provided
        if (customKeyword != null && !customKeyword.isEmpty()) {
            url.setCustomKeyword(customKeyword);
        }

        // Update the expiry date if provided
        if (expiryDays > 0) {
            url.setExpiryDate(expiryDays);
        }

        // Save the updated URL entity
        URL updatedURL = this.repo.save(url);

        return this.mapper.map(updatedURL, URLDTO.class);
    }

    // Generate a random short key (you can customize this)
    private String generateShortKey() {
        int length = 6; // Length of the short key
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortKey = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            shortKey.append(characters.charAt(index));
        }

        return shortKey.toString();
    }
}
