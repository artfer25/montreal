package com.example.acelera_maker_blog.controller;

import com.example.acelera_maker_blog.dto.LoginRequest;
import com.example.acelera_maker_blog.model.Usuario;
import com.example.acelera_maker_blog.security.JwtUtil;
import com.example.acelera_maker_blog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(loginRequest.getEmail());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
                String token = jwtUtil.generateToken(usuario.getEmail());
                return ResponseEntity.ok(Collections.singletonMap("token", "Bearer " + token));
            }
        }
        return ResponseEntity.status(401).body(Collections.singletonMap("error", "Email ou senha inválidos"));
    }
}
