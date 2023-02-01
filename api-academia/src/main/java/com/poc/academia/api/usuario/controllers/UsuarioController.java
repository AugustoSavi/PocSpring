//package com.poc.academia.api.usuario.controllers;
//
//import com.poc.academia.api.usuario.Usuario;
//import com.poc.academia.api.usuario.representations.UsuarioDto;
//import com.poc.academia.api.usuario.services.UsuarioService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/usuario")
//@AllArgsConstructor
//public class UsuarioController {
//
//    private final UsuarioService usuarioService;
//
//    @PostMapping
//    public ResponseEntity<Object> create(@RequestBody @Valid UsuarioDto usuarioDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDto));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<Usuario>> read(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioService.findAll(pageable));
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<Object> readAll(@PathVariable final UUID id) {
//        Optional<Usuario> usuario = usuarioService.findOne(id);
//        return ResponseEntity
//                .status(usuario.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
//                .body(usuario);
//    }
//
//    @PutMapping
//    public ResponseEntity<Object> update(@RequestBody @Valid UsuarioDto usuarioDto) {
//        Usuario usuario = usuarioService.update(usuarioDto);
//        return ResponseEntity
//                .status(null != usuario ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
//                .body(usuario);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> delete(@PathVariable final UUID id) {
//        Optional<Usuario> usuario = usuarioService.delete(id);
//        return ResponseEntity
//                .status(usuario.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
//                .body(usuario);
//    }
//
//}
