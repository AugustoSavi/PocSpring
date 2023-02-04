package com.poc.academia.api.aula.Controllers;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.represetations.AulaDto;
import com.poc.academia.api.aula.services.AulaService;
import com.poc.academia.api.pessoa.Pessoa;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Object> read(@PathVariable final UUID id) {
        Optional<Aula> aula = aulaService.findOne(id);
        return ResponseEntity
                .status(aula.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(aula);
    }

    @GetMapping
    public ResponseEntity<Page<Aula>> read(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(aulaService.findAll(pageable));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid AulaDto aulaDto) {
        Aula aula = aulaService.update(aulaDto);
        return ResponseEntity
                .status(null != aula ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .body(aula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable final UUID id) {
        Optional<Aula> aula = aulaService.delete(id);
        return ResponseEntity
                .status(aula.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(aula);
    }
}
