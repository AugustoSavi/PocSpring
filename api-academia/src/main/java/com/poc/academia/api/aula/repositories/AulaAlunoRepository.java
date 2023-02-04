package com.poc.academia.api.aula.repositories;

import com.poc.academia.api.aula.AulaAluno;
import com.poc.academia.api.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AulaAlunoRepository extends JpaRepository<AulaAluno, UUID> {

    List<AulaAluno> findAllByAulaAndPessoa(UUID aula, Pessoa pessoa);
}
