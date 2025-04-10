package com.atividade.CrudRpg.controller;

import com.atividade.CrudRpg.controller.dto.ItemMagicoDto;
import com.atividade.CrudRpg.controller.dto.ResponseDto;
import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.mapper.ItemMagicoMapper;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.service.ItemMagicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<ResponseDto<List<ItemMagicoDto>>> listarItensMagicos(){
        List<ItemMagicoDto> itensMagicosList = service.listarItensMagicos().stream()
                .map(ItemMagicoMapper::domainParaDto).toList();
        ResponseDto<List<ItemMagicoDto>> resposta = new ResponseDto<>(itensMagicosList);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ItemMagicoDto>> buscarPorId(@PathVariable Long id){
        ItemMagicoDto itemMagico = ItemMagicoMapper.domainParaDto(service.buscarPorId(id));
        ResponseDto<ItemMagicoDto> resposta = new ResponseDto<>(itemMagico);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/personagem/{id}")
    public ResponseEntity<ResponseDto<List<ItemMagicoDto>>> listarPorPersonagem(@PathVariable Long id){
        List<ItemMagicoDto> itemMagicoList = service.listarPorPersonagem(id)
                .stream().map(ItemMagicoMapper::domainParaDto).toList();

        ResponseDto<List<ItemMagicoDto>> resposta = new ResponseDto<>(itemMagicoList);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("amuleto/{id}")
    public  ResponseEntity<ResponseDto<ItemMagicoDto>> buscarAmuletoPorPersonagem(@PathVariable Long id){
        ItemMagicoDto itemMagicoDto = ItemMagicoMapper.domainParaDto(service.buscarAmuletoPorPersonagem(id));
        ResponseDto<ItemMagicoDto> resposta = new ResponseDto<>(itemMagicoDto);

        return ResponseEntity.ok(resposta);
    }
}