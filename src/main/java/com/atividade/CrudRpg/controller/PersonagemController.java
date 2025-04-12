package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.controller.dto.PersonagemDto;
import com.atividade.CrudRpg.controller.dto.PersonagemNomeDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(name = "/personagens")
@Tag(name = "Crud Rpg API")
public class PersonagemController {

    private final PersonagemService service;


    @Operation(summary = "Realiza o cadastro de personagens", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de personagem realizado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro no cadastro de personagem.")
    })
    @PostMapping()
    public ResponseEntity<ResponseDto<PersonagemDto>> cadastrar(@RequestBody @Valid PersonagemDto personagem) {
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


    @Operation(summary = "Realiza a listagens de todos os personagens registrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de personagens realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca de personagens.")
    })
    @GetMapping()
    public ResponseEntity<ResponseDto<List<PersonagemDto>>> listarPersonagens(){
        List<PersonagemDto> personagemList = service.listarPersonagens().stream()
                .map(PersonagemMapper::domainParaDto)
                .toList();

        ResponseDto<List<PersonagemDto>> resposta = new ResponseDto<>(personagemList);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a busca de um personagem pelo id informado.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca do personagem realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca do personagem.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> buscarPorId(@PathVariable Long id){
        PersonagemDto personagemBuscado = PersonagemMapper.domainParaDto(service.buscarPorId(id));

        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemBuscado);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a listagens de todos os itens mágicos registrados de um personagem informado.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de itens mágicos realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca de itens mágicos do personagem.")
    })
    @GetMapping("/itens/{id}")
    public ResponseEntity<ResponseDto<List<ItemMagicoDto>>> listarItensMagicosDoPersonagem(@PathVariable Long id){
        List<ItemMagicoDto> itemMagicoList = service.listarItensMagicosDoPersonagem(id)
                .stream().map(ItemMagicoMapper::domainParaDto).toList();

        ResponseDto<List<ItemMagicoDto>> resposta = new ResponseDto<>(itemMagicoList);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a busca do amuleto de um personagem informado.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca do amuleto realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca do amuleto do personagem.")
    })
    @GetMapping("amuleto/{id}")
    public  ResponseEntity<ResponseDto<ItemMagicoDto>> buscarAmuletoDoPersonagem(@PathVariable Long id){
        ItemMagicoDto itemMagicoDto = ItemMagicoMapper.domainParaDto(service.buscarAmuletoDoPersonagem(id));
        ResponseDto<ItemMagicoDto> resposta = new ResponseDto<>(itemMagicoDto);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a atualização do nome de um personagem informado.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nome do personagem atualizado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na atualização do nome do personagem.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> atualizarNome(@PathVariable Long id, @RequestBody PersonagemNomeDto nomeDto){
        PersonagemDto personagemAlterado = PersonagemMapper.domainParaDto(service.atualizarNome(id, nomeDto.getNome()));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemAlterado);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a adição de um item mágico do personagem informado.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adição do item ao personagem realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na adição do item ao personagem.")
    })
    @PutMapping("/adicionar-item/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> adicionarItem(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto){
        PersonagemDto personagemNovo = PersonagemMapper.domainParaDto(service.adicionarItem(id, ItemMagicoMapper.dtoParaDomain(itemMagicoDto)));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemNovo);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a remoção de um item mágico do personagem informado.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remoção do item ao personagem realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na remoção do item ao personagem.")
    })
    @PutMapping("/remover-item/{id}")
    public ResponseEntity<ResponseDto<PersonagemDto>> removerItem(@PathVariable Long id, @RequestBody ItemMagicoDto itemMagicoDto){
        PersonagemDto personagemNovo = PersonagemMapper.domainParaDto(service.removerItem(id, ItemMagicoMapper.dtoParaDomain(itemMagicoDto)));
        ResponseDto<PersonagemDto> resposta = new ResponseDto<>(personagemNovo);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a remoção de um personagem informado.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Remoção do personagem realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na remoção do personagem no banco de dados.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
