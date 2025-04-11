package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/itens-magicos")
@RequiredArgsConstructor
public class ItemMagicoController {
    private final ItemMagicoService service;

    @Operation(summary = "Realiza o cadastro de itens mágicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de item mágico realizado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro no cadastro de item mágico.")
    })
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


    @Operation(summary = "Realiza a listagens de todos os itens mágicos registrados.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de todos os itens mágicos realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca de itens mágicos registrados.")
    })
    @GetMapping()
    public ResponseEntity<ResponseDto<List<ItemMagicoDto>>> listarItensMagicos(){
        List<ItemMagicoDto> itensMagicosList = service.listarItensMagicos().stream()
                .map(ItemMagicoMapper::domainParaDto).toList();
        ResponseDto<List<ItemMagicoDto>> resposta = new ResponseDto<>(itensMagicosList);

        return ResponseEntity.ok(resposta);
    }


    @Operation(summary = "Realiza a busca de um item mágico informado.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca do item mágico realizada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro na busca do item mágico.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ItemMagicoDto>> buscarPorId(@PathVariable Long id){
        ItemMagicoDto itemMagico = ItemMagicoMapper.domainParaDto(service.buscarPorId(id));
        ResponseDto<ItemMagicoDto> resposta = new ResponseDto<>(itemMagico);

        return ResponseEntity.ok(resposta);
    }
}