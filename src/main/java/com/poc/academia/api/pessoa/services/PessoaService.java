package com.poc.academia.api.pessoa.services;

import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.repositories.PessoaRepository;
import com.poc.academia.api.pessoa.representations.PessoaDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa save(PessoaDto pessoaDto) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(PessoaDto pessoaDto) {
        Optional<Pessoa> pessoaOptional = findOne(pessoaDto.getId());
        if (pessoaOptional.isEmpty()) {
            return null;
        }
        BeanUtils.copyProperties(pessoaDto, pessoaOptional.get());
        pessoaOptional.get().setUpdatedIn(LocalDateTime.now());
        pessoaOptional.get().setVersion(pessoaOptional.get().getVersion() + 1);
        return pessoaRepository.save(pessoaOptional.get());
    }

    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Optional<Pessoa> findOne(UUID id) {
        return pessoaRepository.findById(id);
    }

    public Optional<Pessoa> delete(UUID id) {
        Optional<Pessoa> pessoaOptional = findOne(id);
        if (pessoaOptional.isEmpty()) {
            return Optional.empty();
        }
        pessoaRepository.delete(pessoaOptional.get());
        return pessoaOptional;
    }
}
