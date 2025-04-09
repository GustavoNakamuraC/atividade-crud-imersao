package com.atividade.CrudRpg.mapper;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.repository.entity.ItemMagicoEntity;

public class ItemMagicoMapper {
    public static ItemMagico dtoParaDomain(ItemMagicoDto dto) {
        return ItemMagico.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .tipoItem(dto.getTipoItem())
                .forca(dto.getForca())
                .defesa(dto.getDefesa())
                .build();
    }

    public static ItemMagicoDto domainParaDto(ItemMagico domain) {
        return ItemMagicoDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .tipoItem(domain.getTipoItem())
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }

    public static ItemMagico entityParaDomain(ItemMagicoEntity entity) {
        return ItemMagico.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .tipoItem(entity.getTipoItem())
                .forca(entity.getForca())
                .defesa(entity.getDefesa())
                .build();
    }

    public static ItemMagicoEntity domainParaEntity(ItemMagico domain) {
        return ItemMagicoEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .tipoItem(domain.getTipoItem())
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }
}
