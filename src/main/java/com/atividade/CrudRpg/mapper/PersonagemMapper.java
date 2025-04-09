package com.atividade.CrudRpg.mapper;

import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.Personagem;
import com.atividade.CrudRpg.repository.entity.PersonagemEntity;

import java.util.List;

public class PersonagemMapper {
    public static Personagem dtoParaDomain(PersonagemDto dto){
        return Personagem.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .nomeFantasia(dto.getNomeFantasia())
                .classe(dto.getClasse())
                .level(dto.getLevel())
                .itensMagicos(dto.getItensMagicos().stream()
                        .map(ItemMagicoMapper::dtoParaDomain)
                        .toList())
                .forca(dto.getForca())
                .defesa(dto.getDefesa())
                .build();
    }

    public static PersonagemDto domainParaDto(Personagem domain){
        return PersonagemDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .nomeFantasia(domain.getNomeFantasia())
                .classe(domain.getClasse())
                .level(domain.getLevel())
                .itensMagicos(domain.getItensMagicos().stream()
                        .map(ItemMagicoMapper::domainParaDto)
                        .toList())
                .forca(domain.getForca())
                .defesa(domain.getDefesa())
                .build();
    }

    public static Personagem entityParaDomain(PersonagemEntity entity){
        return Personagem.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .nomeFantasia(entity.getNomeFantasia())
                .classe(entity.getClasse())
                .level(entity.getLevel())
                .itensMagicos(entity.getItensMagicos().stream()
                        .map(ItemMagicoMapper::entityParaDomain)
                        .toList())
                .forca(entity.getDefesa())
                .defesa(entity.getDefesa())
                .build();
    }

    public static PersonagemEntity domainParaEntity(Personagem domain){
        return PersonagemEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .nomeFantasia(domain.getNomeFantasia())
                .classe(domain.getClasse())
                .level(domain.getLevel())
                .itensMagicos(domain.getItensMagicos().stream()
                        .map(ItemMagicoMapper::domainParaEntity)
                        .toList())
                .forca(domain.getDefesa())
                .defesa(domain.getDefesa())
                .build();
    }
}
