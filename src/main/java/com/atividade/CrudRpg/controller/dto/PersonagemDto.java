package com.atividade.CrudRpg.controller.dto;

import com.atividade.CrudRpg.domain.ClasseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PersonagemDto {
    //Se der, adicionar validators.

    private Long id;
    private String nome;
    private String nomeFantasia;
    private ClasseEnum classe;
    private Integer level;
    private List<ItemMagicoDto> itensMagicos;
    private Integer forca;
    private Integer defesa;
}
