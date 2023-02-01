package com.poc.academia.api.aula.Controllers;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.represetations.AulaDto;
import com.poc.academia.api.aula.services.AulaService;
import com.poc.academia.api.pessoa.Pessoa;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/aula")
@AllArgsConstructor
public class AulaController {

    private AulaService aulaService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid AulaDto aulaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aulaService.save(aulaDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> readAll(@PathVariable final UUID id){
        Optional<Aula> aula = aulaService.findOne(id);
        return ResponseEntity
                .status(aula.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(aula);
    }
}
