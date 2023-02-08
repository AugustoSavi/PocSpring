package com.poc.academia.api.contexto.services;

import com.poc.academia.api.contexto.Database;
import com.poc.academia.api.contexto.Entidade;
import com.poc.academia.api.contexto.repositories.DatabaseRepository;
import com.poc.academia.api.contexto.repositories.EntidadeRepository;
import com.poc.academia.api.contexto.representations.EntidadeDto;
import com.poc.academia.api.contexto.representations.RequestPutEntidadeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntidadeService {
    @Autowired
    private EntidadeRepository entidadeEntityRepository;

    @Autowired
    private DatabaseRepository databaseRepository;

    public Entidade save(EntidadeDto entidadeDto) {
        var entidade = new Entidade();
        BeanUtils.copyProperties(entidadeDto, entidade);
        Optional<Database> databaseOptional = databaseRepository.findById(entidadeDto.getDatabase().getId());
        if (databaseOptional.isEmpty()) {
            return null;
        }
        entidade.setDatabase(databaseOptional.get());
        return entidadeEntityRepository.save(entidade);
    }

    public Entidade update(RequestPutEntidadeDto entidadeDto) {
        Optional<Entidade> entidadeOptional = findOne(entidadeDto.getId());
        if (entidadeOptional.isEmpty()) {
            return null;
        }
        BeanUtils.copyProperties(entidadeDto, entidadeOptional.get());
        return entidadeEntityRepository.save(entidadeOptional.get());
    }

    public Optional<Entidade> delete(Long id) {
        Optional<Entidade> entidade = findOne(id);
        if (entidade.isEmpty()) {
            return Optional.empty();
        }
        entidadeEntityRepository.delete(entidade.get());
        return entidade;
    }


    public Optional<Entidade> findOne(Long id) {
        return entidadeEntityRepository.findById(id);
    }

    public Page<Entidade> findAll(Pageable pageable) {
        return entidadeEntityRepository.findAll(pageable);
    }
}
