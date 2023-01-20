package com.poc.pessoa.services;

import com.poc.pessoa.Pessoa;
import com.poc.pessoa.repositories.PessoaRepository;
import com.poc.pessoa.representations.PessoaDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public Pessoa save(PessoaDto pessoaDto) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoaRepository.save(pessoa);
    }
}
