package com.atividade.CrudRpg.controller.dto;

import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ItemMagicoDto {
    //Se der, adicionar validators.

    private Long id;
    private String nome;
    private TipoItemEnum tipoItem;
    private Integer forca;
    private Integer defesa;
}