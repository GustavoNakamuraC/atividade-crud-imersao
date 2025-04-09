package com.atividade.CrudRpg.domain;

import com.atividade.CrudRpg.domain.enums.ClasseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Personagem {
    private Long id;
    private String nome;
    private String nomeFantasia;
    private ClasseEnum classe;
    private Integer level;
    private List<ItemMagico> itensMagicos;
    private Integer forca;
    private Integer defesa;
}
