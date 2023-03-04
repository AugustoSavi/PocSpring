package com.poc.academia.api.contexto.repositories;

import com.poc.academia.api.contexto.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
}
