package com.se7en.URLshortner.config;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
}
