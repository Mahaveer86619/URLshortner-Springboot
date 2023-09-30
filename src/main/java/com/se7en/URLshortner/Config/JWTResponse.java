package com.se7en.URLshortner.Config;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JWTResponse {

    private String jwtToken;
    private String userName;

}
