package com.poc.academia.api.pessoa.services;

import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.repositories.PessoaRepository;
import com.poc.academia.api.pessoa.representations.PessoaDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(PessoaDto pessoaDto) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaDto> getAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(PessoaDto::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
