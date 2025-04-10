package com.atividade.CrudRpg.domain;

import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemMagico {
    private Long id;
    private String nome;
    private TipoItemEnum tipoItem;
    private Integer forca;
    private Integer defesa;
}
