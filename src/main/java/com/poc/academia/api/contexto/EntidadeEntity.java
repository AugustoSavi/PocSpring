package com.poc.academia.api.contexto;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class EntidadeEntity extends DatabaseEntity {

    @Column(name = "I_ENTIDADE", updatable = false)
    private Long entidade;
}
