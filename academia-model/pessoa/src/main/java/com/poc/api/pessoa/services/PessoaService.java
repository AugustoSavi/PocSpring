package com.poc.api.pessoa.services;

import com.poc.api.pessoa.Pessoa;
import com.poc.api.pessoa.repositories.PessoaRepository;
import com.poc.api.pessoa.representations.PessoaDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(PessoaDto pessoaDto){
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoaRepository.save(pessoa);
    }
}
