package com.poc.academia.api.contexto.controllers;

import com.poc.academia.api.contexto.Entidade;
import com.poc.academia.api.contexto.representations.EntidadeDto;
import com.poc.academia.api.contexto.representations.RequestPutEntidadeDto;
import com.poc.academia.api.contexto.services.EntidadeService;
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

@RestController
@RequestMapping("/filial")
@AllArgsConstructor
public class EntidadeController {

    private EntidadeService entidadeService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid EntidadeDto databaseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadeService.save(databaseDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> read(@PathVariable final Long id) {
        Optional<Entidade> entidade = entidadeService.findOne(id);
        return ResponseEntity
                .status(entidade.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(entidade);
    }

    @GetMapping
    public ResponseEntity<Page<Entidade>> read(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(entidadeService.findAll(pageable));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid RequestPutEntidadeDto entidadeDto) {
        Entidade entidade = entidadeService.update(entidadeDto);
        return ResponseEntity
                .status(null != entidade ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .body(entidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable final Long id) {
        Optional<Entidade> entidade = entidadeService.delete(id);
        return ResponseEntity
                .status(entidade.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(entidade);
    }
}
