package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.controller.dto.PersonagemNomeDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<ResponseDto<List<PersonagemDto>>> listarPersonagens(){
        List<PersonagemDto> personagemList = service.listarPersonagens().stream()
                .map(PersonagemMapper::domainParaDto)
                .toList();

        ResponseDto<List<PersonagemDto>> resposta = new ResponseDto<>(personagemList);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> buscarPorId(@PathVariable Long id){
        PersonagemDto personagemBuscado = PersonagemMapper.domainParaDto(service.buscarPorId(id));

        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemBuscado);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> atualizarNome(@PathVariable Long id, @RequestBody PersonagemNomeDto nomeDto){
        PersonagemDto personagemAlterado = PersonagemMapper.domainParaDto(service.atualizarNome(id, nomeDto.getNome()));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemAlterado);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/adicionar-item/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> adicionarItem(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto){
        PersonagemDto personagemNovo = PersonagemMapper.domainParaDto(service.adicionarItem(id, ItemMagicoMapper.dtoParaDomain(itemMagicoDto)));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemNovo);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/remover-item/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> removerItem(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto){
        PersonagemDto personagemNovo = PersonagemMapper.domainParaDto(service.removerItem(id, ItemMagicoMapper.dtoParaDomain(itemMagicoDto)));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemNovo);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
