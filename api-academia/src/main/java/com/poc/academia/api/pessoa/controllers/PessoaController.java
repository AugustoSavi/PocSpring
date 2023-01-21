package com.poc.academia.api.pessoa.controllers;

import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.representations.PessoaDto;
import com.poc.academia.api.pessoa.services.PessoaService;
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
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid PessoaDto pessoaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaDto));
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> read(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> readAll(@PathVariable final UUID id){
        Optional<Pessoa> pessoa = pessoaService.findOne(id);
        return ResponseEntity
                .status(pessoa.isPresent() ? HttpStatus.FOUND : HttpStatus.NOT_FOUND)
                .body(pessoa);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid PessoaDto pessoaDto){
        Pessoa pessoa = pessoaService.update(pessoaDto);
        return ResponseEntity
                .status(null != pessoa ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND)
                .body(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable final UUID id){
        Optional<Pessoa> pessoa = pessoaService.delete(id);
        return ResponseEntity
                .status(pessoa.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(pessoa);
    }

}
