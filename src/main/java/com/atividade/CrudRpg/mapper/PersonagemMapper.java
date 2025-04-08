package com.atividade.CrudRpg.mapper;

import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.Personagem;

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
}
