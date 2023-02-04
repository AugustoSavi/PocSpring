package com.poc.academia.api.aula;

import com.poc.academia.api.aula.enums.TipoAula;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "AULAS")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String descricao;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHoraInicio;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHoraFinal;

    private TipoAula tipoAula;

    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AulaAluno> alunos = Collections.emptyList();

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
