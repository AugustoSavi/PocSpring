package com.poc.academia.api.contexto;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class DatabaseEntity extends AbstractEntity {

    @Column(name = "I_DATABASE", updatable = false)
    private Long database;
}