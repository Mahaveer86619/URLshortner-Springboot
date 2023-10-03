package com.se7en.URLshortner.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // check if the request is for creating or authenticating a user
        if (request.getRequestURI().equals("/auth/register") || request.getRequestURI().equals("/auth/authenticate")) {
            // skip token validation and proceed to the next filter
            filterChain.doFilter(request, response);
            return;
        }

        // otherwise, proceed with token validation
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // assign the values to jwtToken and userEmail variables
            jwtToken = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwtToken);

            // If the user is not authenticated already
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }

        } else {
            logger.info("Invalid Header Value !! ");
        }

        filterChain.doFilter(request, response);

    }
}
