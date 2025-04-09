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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonagemService {

    private final ItemMagicoService itemMagicoService;
    private final PersonagemRepository repository;
    private static final String ERRO_PERSONAGEM_INVALIDO = "Dados do personagem inválidos.";
    private static final String ERRO_PERSONAGEM_NAO_ENCONTRADO = "Personagem não encontrado.";
    public Personagem cadastrar(Personagem personagem){

        if (personagem.getForca() + personagem.getDefesa() > 10){
            throw new RuntimeException(ERRO_PERSONAGEM_INVALIDO);
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

    public Personagem buscarPorId(Long id) {
        Optional<PersonagemEntity> personagem = repository.findById(id);

        if (personagem.isEmpty()){
            throw new RuntimeException(ERRO_PERSONAGEM_NAO_ENCONTRADO);
        }

        return PersonagemMapper.entityParaDomain(personagem.get());
    }

    public Personagem atualizarNome(Long id, String novoNome) {
        Personagem personagem = buscarPorId(id);

        personagem.setNome(novoNome);

        return personagem;
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
