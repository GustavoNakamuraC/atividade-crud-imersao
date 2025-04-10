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
    //Se der, adicionar validators.

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "O tipo do item é obrigatório.")
    private TipoItemEnum tipoItem;

    @NotNull(message = "A força é obrigatória.")
    @Min(value = 0, message = "Mínimo de força permitido é 0")
    @Max(value = 10, message = "Máximo de força permitido é 10")
    private Integer forca;

    @NotNull(message = "A defesa é obrigatória.")
    @Min(value = 0, message = "Mínimo de defesa permitido é 0")
    @Max(value = 10, message = "Máximo de defesa permitido é 10")
    private Integer defesa;
}