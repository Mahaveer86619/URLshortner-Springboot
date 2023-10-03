package com.se7en.URLshortner.Controllers;

import com.se7en.URLshortner.Entities.URL;
import com.se7en.URLshortner.Payloads.ApiResponse;
import com.se7en.URLshortner.Payloads.URLDTO;
import com.se7en.URLshortner.Repos.URLRepo;
import com.se7en.URLshortner.Services.URLService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class URLController {

    @Autowired
    private URLRepo repo;

    @Autowired
    private URLService urlService;

    @PostMapping("/create")
    public ResponseEntity<URLDTO> createURL(@RequestBody URLDTO urlDTO) {
        URLDTO createUrlDTO = this.urlService.saveURL(urlDTO);

        // Include the short URL in the response
        String shortURL = "http://localhost:8080/" + "go/" + createUrlDTO.getShortKey();
        createUrlDTO.setShortURL(shortURL);

        return new ResponseEntity<>(createUrlDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{urlId}")
    public ResponseEntity<URLDTO> getURLById(@PathVariable Long urlId) {
        return ResponseEntity.ok(this.urlService.findURLById(urlId).orElse(null));
    }

    @DeleteMapping("/{urlId}")
    public ResponseEntity<ApiResponse> deleteURL(@PathVariable Long urlId) {
        urlService.deleteURL(urlId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("URL deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<URLDTO>> getURLsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.urlService.getURLsForUser(userId));
    }

    @PutMapping("/{urlId}")
    public ResponseEntity<URLDTO> updateURL(
            @PathVariable Long urlId,
            @RequestParam(required = false) String customKeyword,
            @RequestParam(required = false) int expiryDays) {

        URLDTO updatedUrlDTO = urlService.updateURL(urlId, customKeyword, expiryDays);

        return ResponseEntity.ok(updatedUrlDTO);
    }

    @GetMapping("/go/{shortKey}")
    public ResponseEntity<Void> redirectToOriginalURL(@PathVariable String shortKey, HttpServletResponse response) {
        // Retrieve the URL entity with the given short key from the database
        Optional<URL> urlOptional = this.repo.findByShortKey(shortKey);

        if (urlOptional.isPresent()) {
            URL url = urlOptional.get();
            // Redirect to the original URL
            try {
                response.sendRedirect(url.getOriginalURL());
                return ResponseEntity.status(HttpStatus.FOUND).build();
            } catch (IOException e) {
                // Handle IO exception
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            // Handle the case when the short key doesn't exist
            return ResponseEntity.notFound().build();
        }
    }
}
