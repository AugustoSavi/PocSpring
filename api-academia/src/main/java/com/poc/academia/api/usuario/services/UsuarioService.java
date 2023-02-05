package com.poc.academia.api.usuario.services;

import com.poc.academia.api.auth.configs.JwtService;
import com.poc.academia.api.auth.represetations.AuthenticationRequest;
import com.poc.academia.api.auth.represetations.AuthenticationResponse;
import com.poc.academia.api.usuario.Usuario;
import com.poc.academia.api.usuario.repositories.UsuarioRepository;
import com.poc.academia.api.usuario.representations.UsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    public AuthenticationResponse save(UsuarioDto usuarioDTO) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setPassword(bCryptPasswordEncoder.encode(usuarioDTO.getPassword()));
        usuarioRepository.save(usuario);

        var jwtToken = jwtService.generateToken(usuario);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
