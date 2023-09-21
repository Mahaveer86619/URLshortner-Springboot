package com.se7en.URLshortner.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalURL;
    private String shortKey;

    // Creation time of the short URL
    private final LocalDateTime creationTime = LocalDateTime.now();

    // Expiry date for the short URL
    private LocalDateTime expiryDate;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Null for unregistered users

    // Logic to set expiry date (e.g., add 7 days to the creation time)
    public void setExpiryDate(int days) {
        this.expiryDate = creationTime.plusDays(days);
    }

    // Check if the URL is expired
    public boolean isExpired() {
        return expiryDate != null && LocalDateTime.now().isAfter(expiryDate);
    }
}