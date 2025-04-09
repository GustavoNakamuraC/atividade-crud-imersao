package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/personagens")
public class PersonagemController {

    private final PersonagemService service;

    @PostMapping()
    public ResponseEntity<ResponseDto<PersonagemDto>> cadastrar(@RequestBody PersonagemDto personagem) {
        PersonagemDto personagemSalvo = PersonagemMapper.domainParaDto(
                service.cadastrar(PersonagemMapper.dtoParaDomain(personagem))
        );
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemSalvo);

        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/personagens/{id}")
                                .buildAndExpand(personagemSalvo.getId())
                                .toUri()
                )
                .body(resposta);
    }


}