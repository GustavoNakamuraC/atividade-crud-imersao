package com.atividade.CrudRpg.repository;

import com.atividade.CrudRpg.repository.entity.ItemMagicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagicoEntity, Long> {
}
