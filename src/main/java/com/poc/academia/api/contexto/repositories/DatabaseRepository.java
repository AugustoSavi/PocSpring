package com.poc.academia.api.contexto.repositories;

import com.poc.academia.api.contexto.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<Database, Long> {
}
