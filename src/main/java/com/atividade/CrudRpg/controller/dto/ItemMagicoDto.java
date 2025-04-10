package com.atividade.CrudRpg.controller.dto;

import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ItemMagicoDto {

    private Long id;

    private String nome;

    private TipoItemEnum tipoItem;

    @Min(value = 0, message = "Mínimo de força permitido é 0")
    @Max(value = 10, message = "Máximo de força permitido é 10")
    private Integer forca;

    @Min(value = 0, message = "Mínimo de defesa permitido é 0")
    @Max(value = 10, message = "Máximo de defesa permitido é 10")
    private Integer defesa;
}