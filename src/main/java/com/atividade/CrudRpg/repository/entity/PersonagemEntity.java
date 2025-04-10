package com.atividade.CrudRpg.repository.entity;

import com.atividade.CrudRpg.domain.enums.ClasseEnum;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personagem")
    private List<ItemMagicoEntity> itensMagicos;

    private Integer forca;
    private Integer defesa;
}
