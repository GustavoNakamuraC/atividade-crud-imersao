package com.atividade.CrudRpg.repository.entity;

import com.atividade.CrudRpg.domain.ClasseEnum;
import com.atividade.CrudRpg.domain.ItemMagico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity (name = "Personagem")
@Table (name = "personagens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    private ClasseEnum classe;
    private Integer level;
    private List<ItemMagicoEntity> itensMagicos;
    private Integer forca;
    private Integer defesa;
}
