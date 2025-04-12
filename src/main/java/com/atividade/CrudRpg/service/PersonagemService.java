package com.atividade.CrudRpg.service;

import com.atividade.CrudRpg.domain.ItemMagico;
import com.atividade.CrudRpg.domain.Personagem;
import com.atividade.CrudRpg.domain.enums.TipoItemEnum;
import com.atividade.CrudRpg.mapper.PersonagemMapper;
import com.atividade.CrudRpg.repository.PersonagemRepository;
import com.atividade.CrudRpg.repository.entity.PersonagemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final ItemMagicoService itemMagicoService;
    private final PersonagemRepository repository;
    private static final String ERRO_PERSONAGEM_INVALIDO = "Dados do personagem inválidos.";
    private static final String ERRO_PERSONAGEM_NAO_ENCONTRADO = "Personagem não encontrado.";
    public Personagem cadastrar(Personagem personagem){

        if (personagem.getForca() + personagem.getDefesa() > 10){
            throw new RuntimeException(ERRO_PERSONAGEM_INVALIDO);
        }

        PersonagemEntity personagemSalvo = repository.save(
                PersonagemMapper.domainParaEntitySemItens(personagem)
        );

        List<ItemMagico> itensMagicos = personagem.getItensMagicos() == null ? new ArrayList<>() : personagem.getItensMagicos();

        List<ItemMagico> itensCadastrados = itensMagicos.stream()
                .map(item -> {
                    item.setPersonagem(PersonagemMapper.entityParaDomain(personagemSalvo));
                    return itemMagicoService.cadastrar(item);
                }).toList();

        personagem.setItensMagicos(itensCadastrados);

        return PersonagemMapper.entityParaDomain(personagemSalvo);
    }

    public List<Personagem> listarPersonagens() {
        List<PersonagemEntity> personagens = repository.findAll();

        return personagens.stream()
                .map(personagemEntity -> {
                    Personagem personagem = PersonagemMapper.entityParaDomain(personagemEntity);
                    List<ItemMagico> itensMagicos = listarItensMagicosDoPersonagem(personagem.getId());
                    personagem.setItensMagicos(itensMagicos);

                    int forcaPersonagem = personagem.getForca();
                    int defesaPersonagem = personagem.getDefesa();

                    for (ItemMagico item : personagem.getItensMagicos()) {
                        forcaPersonagem += item.getForca();
                        defesaPersonagem += item.getDefesa();
                    }

                    personagem.setForca(forcaPersonagem);
                    personagem.setDefesa(defesaPersonagem);

                    return personagem;
                })
                .toList();
    }

    public Personagem buscarPorId(Long id) {
        Optional<PersonagemEntity> personagem = repository.findById(id);

        if (personagem.isEmpty()){
            throw new RuntimeException(ERRO_PERSONAGEM_NAO_ENCONTRADO);
        }

        return PersonagemMapper.entityParaDomain(personagem.get());
    }

    public List<ItemMagico> listarItensMagicosDoPersonagem(Long idPersonagem) {
        Personagem personagem = buscarPorId(idPersonagem);
        return personagem.getItensMagicos();
    }

    public ItemMagico buscarAmuletoDoPersonagem(Long idPersonagem) {
        Personagem personagem = buscarPorId(idPersonagem);
        List<ItemMagico> itens = personagem.getItensMagicos();
        ItemMagico amuleto = new ItemMagico();

        for (ItemMagico item : itens) {
            if (item.getTipoItem() == TipoItemEnum.AMULETO){
                amuleto = item;
            }
        }

        return amuleto;

    }

    public Personagem atualizarNome(Long id, String novoNome) {
        Personagem personagem = buscarPorId(id);

        personagem.setNome(novoNome);

        return personagem;
    }

    public Personagem adicionarItem(Long id, ItemMagico itemMagico) {
        Personagem personagem = buscarPorId(id);
        ItemMagico itemMagicoAdd = itemMagicoService.buscarPorId(itemMagico.getId());

        personagem.getItensMagicos().add(itemMagicoAdd);

        return personagem;
    }

    public Personagem removerItem(Long id, ItemMagico itemMagico) {
        Personagem personagem = buscarPorId(id);
        ItemMagico itemMagicoRemove = itemMagicoService.buscarPorId(itemMagico.getId());

        personagem.getItensMagicos().remove(itemMagicoRemove);

        return personagem;
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
