package com.atividade.CrudRpg.repository;

import com.atividade.CrudRpg.repository.entity.PersonagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<PersonagemEntity, Long> {
}
