package com.example.welcome.security;


import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("Intercepting a request");

//        if(request.getRequestURI().equals("/api/auth/login" ) || request.getRequestURI().equals("/api/auth/register" ) || request.getRequestURI().equals("/test" )) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            System.out.println("JwtAuthenticationFilter: Intercepting a request");
            username = jwtUtil.extractUsername(jwt);

            if(username == null) {
                throw new SignatureException("Invalid Token");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(userDetails == null) {
                throw new SignatureException("Invalid Token");
            }

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            request.setAttribute("username", username);
            filterChain.doFilter(request,response);
        }
        catch(Exception e) {
            System.out.println("(EXCEPTION) " + e.getClass() + ": " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            setResponseError(response, "Token is invalid or expired");
        }
    }

    private void setResponseError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print("{\"error\" : \""+ message + "\"}");
    }

}
