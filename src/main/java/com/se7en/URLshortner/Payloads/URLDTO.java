package com.se7en.URLshortner.Payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class URLDTO {
    private Long id;
    private String originalURL;
    private String shortURL;
    private String shortKey;
    private LocalDateTime creationTime = LocalDateTime.now(); // Store as string or convert to LocalDateTime
    private LocalDateTime expiryDate; // Store as string or convert to LocalDateTime
    private Long userId; // User ID associated with the URL (for registered users)

    // code to timestamp the creation of short URL

}
