package com.example.acelera_maker_blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

   @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getServletPath();

    //  Liberar todas as rotas públicas
    if (path.startsWith("/api/auth") ||
        path.equals("/api/usuarios") ||
        path.startsWith("/api/usuarios/") ||
        path.startsWith("/swagger-ui") ||
        path.startsWith("/v3/api-docs") ||
        path.equals("/") ||
        path.equals("/index.html")) {
        
        filterChain.doFilter(request, response);
        return;
    }

    //  Verifica se existe um token no header Authorization
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);

        if (jwtUtil.validateJwtToken(token)) {
            String username = jwtUtil.getUsernameFromJwt(token);

            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    
    filterChain.doFilter(request, response);
}
}