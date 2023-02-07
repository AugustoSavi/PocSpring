package com.poc.academia.api.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poc.academia.api.contexto.Database;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
@Table(name = "USUARIOS_DATABASES")
public class UsuarioDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "I_USUARIOS", referencedColumnName = "ID")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "I_DATABASES", referencedColumnName = "ID")
    private Database database;
}
