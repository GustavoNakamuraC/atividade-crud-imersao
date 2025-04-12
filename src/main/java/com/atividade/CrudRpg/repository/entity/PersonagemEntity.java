package com.atividade.CrudRpg.repository.entity;

import com.atividade.CrudRpg.domain.enums.ClasseEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity (name = "Personagem")
@Table (name = "personagens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonagemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Enumerated(EnumType.STRING)
    private ClasseEnum classe;

    private Integer level;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemMagicoEntity> itensMagicos = new ArrayList<>();

    private Integer forca;
    private Integer defesa;

    public PersonagemEntity(String nome, String nomeFantasia, ClasseEnum classe, Integer level, Integer forca, Integer defesa) {
            this.nome = nome;
            this.nomeFantasia = nomeFantasia;
            this.classe = classe;
            this.level = level;
            this.forca = forca;
            this.defesa = defesa;
    }
}
