package com.se7en.URLshortner.Config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JWTRequest {

    private String email;
    private String username;
    private String password;

}
