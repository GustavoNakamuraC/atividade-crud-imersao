package com.atividade.CrudRpg.service;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.repository.ItemMagicoRepository;
import com.atividade.CrudRpg.repository.entity.ItemMagicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {
    private final ItemMagicoRepository repository;
    private static final String ERRO_DADOS_INVALIDOS = "Dados do item mágico inválidos.";
    private static final String ERRO_ITEM_NAO_ENCONTRADO = "Item mágico não encontrado.";

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

    public List<ItemMagico> listarItensMagicos() {
        return repository.findAll().stream()
                .map(ItemMagicoMapper::entityParaDomain).toList();
    }

    public ItemMagico buscarPorId(Long id) {
        Optional<ItemMagicoEntity> itemMagico = repository.findById(id);

        if (itemMagico.isEmpty()){
            throw new RuntimeException(ERRO_ITEM_NAO_ENCONTRADO);
        }

        return ItemMagicoMapper.entityParaDomain(itemMagico.get());
    }


}
