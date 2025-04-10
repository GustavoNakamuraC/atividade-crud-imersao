package com.atividade.CrudRpg.repository;

import com.atividade.CrudRpg.repository.entity.PersonagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemEntity, Long> {
}
