package com.poc.academia.api.aula.services;

import com.poc.academia.api.aula.Aula;
import com.poc.academia.api.aula.AulaAluno;
import com.poc.academia.api.aula.repositories.AulaAlunoRepository;
import com.poc.academia.api.aula.repositories.AulaRepository;
import com.poc.academia.api.aula.represetations.AulaAlunoDto;
import com.poc.academia.api.aula.represetations.AulaDto;
import com.poc.academia.api.pessoa.Pessoa;
import com.poc.academia.api.pessoa.repositories.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AulaAlunoRepository aulaAlunoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Aula save(AulaDto aulaDto) {
        var aula = new AulaDto().AulaDtoToAula(aulaDto);
        List<AulaAluno> aulaAlunos = new ArrayList<>();
        if (Objects.nonNull(aulaDto.getAlunos()) && !aulaDto.getAlunos().isEmpty()) {
            List<Pessoa> pessoas = pessoaRepository.findAllById(aulaDto.getAlunos().stream().map(AulaAlunoDto::getId).collect(Collectors.toList()));
            for (Pessoa pessoa : pessoas) {
                aulaAlunos.add(new AulaAluno(aula, pessoa));
            }
            aula.setAlunos(aulaAlunos);
        }

        return aulaRepository.save(aula);
    }

    public Aula update(AulaDto aulaDto) {
        Optional<Aula> aulaOptional = findOne(aulaDto.getId());
        if (aulaOptional.isEmpty()) {
            return null;
        }
        var aula = new Aula();
        BeanUtils.copyProperties(aulaDto, aula);

        if (Objects.nonNull(aulaDto.getAlunos()) && !aulaDto.getAlunos().isEmpty()) {
            List<UUID> collect = aulaDto.getAlunos().stream().map(AulaAlunoDto::getId).collect(Collectors.toList());
            List<AulaAluno> aulaAlunosList = aulaAlunoRepository.findAllById(collect);
            aula.setAlunos(aulaAlunosList);
        }
        return aulaRepository.save(aula);
    }

    public Optional<Aula> delete(UUID id) {
        Optional<Aula> aula = findOne(id);
        if (aula.isEmpty()) {
            return Optional.empty();
        }
        aulaRepository.delete(aula.get());
        return aula;
    }

    public Page<Aula> findAll(Pageable pageable) {
        return aulaRepository.findAll(pageable);
    }

    public Optional<Aula> findOne(UUID id) {
        return aulaRepository.findById(id);
    }
}
