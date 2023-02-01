package com.poc.academia.api.aula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poc.academia.api.pessoa.Pessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "AULAS_ALUNOS")
public class AulaAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "I_AULA", referencedColumnName = "ID")
    @JsonIgnore
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "I_PESSOA", referencedColumnName = "ID")
    private Pessoa pessoa;
}
