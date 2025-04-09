package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.domain.Personagem;
import com.atividade.CrudRpg.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/personagens")
public class PersonagemController {

    private final PersonagemService service;

    @PostMapping()
    public ResponseEntity<ResponseDto<PersonagemDto>> cadastrar(@RequestBody PersonagemDto personagem) {
        service.cadastrar(Personagem)
    }
}