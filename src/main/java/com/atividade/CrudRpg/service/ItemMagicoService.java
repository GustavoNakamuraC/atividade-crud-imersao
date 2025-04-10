package com.atividade.CrudRpg.service;

import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.repository.ItemMagicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {
    private final ItemMagicoRepository repository;
    private static final String ERRO_DADOS_INVALIDOS = "Dados do item mágico inválidos.";

    public ItemMagico cadastrar(ItemMagico itemMagico) {

        if (itemMagico.getTipoItem() == TipoItemEnum.ARMA
                && itemMagico.getDefesa() > 0 && itemMagico.getForca() == 0){
            throw new RuntimeException(ERRO_DADOS_INVALIDOS);
        }

        if (itemMagico.getTipoItem() == TipoItemEnum.ARMADURA
                && itemMagico.getForca() > 0 && itemMagico.getDefesa() == 0){
            throw new RuntimeException(ERRO_DADOS_INVALIDOS);
        }

        if (itemMagico.getTipoItem() == TipoItemEnum.AMULETO
                && itemMagico.getDefesa() == 0 && itemMagico.getForca() == 0){
            throw new RuntimeException(ERRO_DADOS_INVALIDOS);
        }

        return ItemMagicoMapper.entityParaDomain(
                repository.save(ItemMagicoMapper.domainParaEntity(itemMagico)));
    }

    public ItemMagico buscarItemMagicoPorId(Long id) {
    }
}
