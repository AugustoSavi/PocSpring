package com.poc.academia.api.pessoa;

import com.poc.academia.api.tenant.EntidadeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "PESSOAS")
public class Pessoa extends EntidadeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CPF", length = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id != null && Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
