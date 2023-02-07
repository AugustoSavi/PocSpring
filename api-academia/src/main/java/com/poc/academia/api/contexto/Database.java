package com.poc.academia.api.contexto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "DATABASES")
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao", nullable = false, unique = true)
    private String descricao;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "database")
    private List<Entidade> entidade;
}
