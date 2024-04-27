package com.turkcell.authserver.core.filters;

import com.turkcell.authserver.core.services.JwtService;
import com.turkcell.authserver.services.abstracts.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.swing.*;
import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException
    {
        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader != null && jwtHeader.startsWith("Bearer "))
        {
            String jwt = jwtHeader.substring(7);



            if(jwtService.validateToken(jwt))
            {
                // Security paketini giriş yapılmış olarak güncellemek.
                String username = jwtService.extractUsername(jwt);
                // TODO: Implement roles.
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, null);
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }



        filterChain.doFilter(request,response);
    }
}
