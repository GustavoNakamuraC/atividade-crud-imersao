package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.ItemMagicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemMagicoController {
    private final ItemMagicoService service;

    @PostMapping()
    public ResponseEntity<ResponseDto<ItemMagicoDto>> cadastrar(@RequestBody ItemMagicoDto item){
        ItemMagicoDto itemMagico = ItemMagicoMapper.domainParaDto(service.cadastrar(ItemMagicoMapper.dtoParaDomain(item)));
        ResponseDto<ItemMagicoDto> resposta = new ResponseDto<>(itemMagico);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/itens/{id}")
                                .buildAndExpand(itemMagico.getId())
                                .toUri()
                )
                .body(resposta);
    }
}