package com.poc.academia.api.usuario.services;

import com.poc.academia.api.usuario.Usuario;
import com.poc.academia.api.usuario.repositories.UsuarioRepository;
import com.poc.academia.api.usuario.representations.UsuarioDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(UsuarioDto usuarioDTO) {
        var usuario = new Usuario();
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(UsuarioDto usuarioDTO) {
        Optional<Usuario> usuarioOptional = findOne(usuarioDTO.getId());
        if (usuarioOptional.isEmpty()) {
            return null;
        }
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioOptional, usuario);
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> findOne(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> delete(UUID id) {
        Optional<Usuario> usuarioOptional = findOne(id);
        if (usuarioOptional.isEmpty()) {
            return Optional.empty();
        }
        usuarioRepository.delete(usuarioOptional.get());
        return usuarioOptional;
    }
}
