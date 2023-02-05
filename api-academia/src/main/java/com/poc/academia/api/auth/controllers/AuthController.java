package com.poc.academia.api.auth.controllers;

import com.poc.academia.api.auth.represetations.AuthenticationRequest;
import com.poc.academia.api.auth.represetations.AuthenticationResponse;
import com.poc.academia.api.usuario.representations.UsuarioDto;
import com.poc.academia.api.usuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.save(usuarioDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(usuarioService.authenticate(authenticationRequest));
    }


}
