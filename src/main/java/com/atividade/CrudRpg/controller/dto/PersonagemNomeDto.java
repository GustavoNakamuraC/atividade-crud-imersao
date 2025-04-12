package com.atividade.CrudRpg.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PersonagemNomeDto {

    @JsonProperty("nome")
    private String nome;
}
