package com.poc.academia.api.contexto.controllers;

import com.poc.academia.api.contexto.Database;
import com.poc.academia.api.contexto.representations.DatabaseDto;
import com.poc.academia.api.contexto.services.DatabaseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/matriz")
@AllArgsConstructor
public class DatabaseController {

    private DatabaseService databaseService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid DatabaseDto databaseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(databaseService.save(databaseDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> read(@PathVariable final Long id) {
        Optional<Database> database = databaseService.findOne(id);
        return ResponseEntity
                .status(database.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(database);
    }

    @GetMapping("{id}/filiais")
    public ResponseEntity<Object> readEntidades(@PathVariable final Long id) {
        Optional<Database> database = databaseService.findOne(id);
        return ResponseEntity
                .status(database.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(database.isPresent() ? database.get().getEntidades() : Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<Page<Database>> read(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(databaseService.findAll(pageable));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid DatabaseDto databaseDto) {
        Database database = databaseService.update(databaseDto);
        return ResponseEntity
                .status(null != database ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .body(database);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable final Long id) {
        Optional<Database> database = databaseService.delete(id);
        return ResponseEntity
                .status(database.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(database);
    }
}
