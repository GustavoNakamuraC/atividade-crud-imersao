package com.atividade.CrudRpg.service;

import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.Personagem;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.repository.PersonagemRepository;
import com.atividade.CrudRpg.repository.entity.PersonagemEntity;
import com.atividade.CrudRpg.service.exception.PersonagemInvalidoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonagemService {

    private final ItemMagicoService itemMagicoService;
    private final PersonagemRepository repository;
    public static final String ERRO_PERSONAGEM_INVALIDO = "Dados do personagem inválidos.";
    public Personagem cadastrar(Personagem personagem){

        if (personagem.getForca() + personagem.getDefesa() > 10){
            throw new PersonagemInvalidoException(ERRO_PERSONAGEM_INVALIDO);
        }

        List<ItemMagico> itemMagicos = personagem.getItensMagicos().stream().map(itemMagico -> itemMagicoService.buscarItemMagicoPorId(itemMagico.getId())).toList();
        personagem.setItensMagicos(itemMagicos);

        PersonagemEntity personagemSalvo = repository.save(PersonagemMapper.domainParaEntity(personagem));

        return PersonagemMapper.entityParaDomain(personagemSalvo);
    }

    public List<Personagem> listarPersonagens() {
        List<PersonagemEntity> personagens = repository.findAll();

        return personagens.stream()
                .map(PersonagemMapper::entityParaDomain)
                .toList();
    }
}
