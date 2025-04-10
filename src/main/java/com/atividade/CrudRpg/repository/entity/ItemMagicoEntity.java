package com.atividade.CrudRpg.repository.entity;

import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.Type;

@Entity(name = "ItemMagico")
@Table (name = "itens_magicos")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ItemMagicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(name = "tipo_item")
    @Enumerated(EnumType.STRING)
    private TipoItemEnum tipoItem;

    private Integer forca;
    private Integer defesa;
}
