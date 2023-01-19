package com.poc.academia.api.pessoa;

import com.poc.api.pessoa.representations.PessoaDto;
import com.poc.api.pessoa.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PessoaDto pessoaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaDto));
    }
}
