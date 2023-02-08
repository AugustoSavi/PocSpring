package com.poc.academia.api.contexto.services;

import com.poc.academia.api.contexto.Database;
import com.poc.academia.api.contexto.repositories.DatabaseRepository;
import com.poc.academia.api.contexto.representations.DatabaseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseRepository databaseRepository;


    public Database save(DatabaseDto databaseDto) {
        var database = new Database();
        BeanUtils.copyProperties(databaseDto, database);

        return databaseRepository.save(database);
    }

    public Database update(DatabaseDto databaseDto) {
        Optional<Database> databaseOptional = findOne(databaseDto.getId());
        if (databaseOptional.isEmpty()) {
            return null;
        }
        BeanUtils.copyProperties(databaseDto, databaseOptional.get());
        return databaseRepository.save(databaseOptional.get());
    }

    public Optional<Database> delete(Long id) {
        Optional<Database> database = findOne(id);
        if (database.isEmpty()) {
            return Optional.empty();
        }
        databaseRepository.delete(database.get());
        return database;
    }


    public Optional<Database> findOne(Long id) {
        return databaseRepository.findById(id);
    }

    public Page<Database> findAll(Pageable pageable) {
        return databaseRepository.findAll(pageable);
    }
}
