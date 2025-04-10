package com.atividade.CrudRpg.controller.dto;

import com.atividade.CrudRpg.domain.enums.ClasseEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PersonagemDto {

    private Long id;
    private String nome;
    private String nomeFantasia;
    private ClasseEnum classe;
    private Integer level;
    private List<ItemMagicoDto> itensMagicos;

    @Min(value = 0, message = "Mínimo de força permitido é 0")
    @Max(value = 10, message = "Máximo de força permitido é 10")
    private Integer forca;

    @Min(value = 0, message = "Mínimo de defesa permitido é 0")
    @Max(value = 10, message = "Máximo de defesa permitido é 10")
    private Integer defesa;
}
