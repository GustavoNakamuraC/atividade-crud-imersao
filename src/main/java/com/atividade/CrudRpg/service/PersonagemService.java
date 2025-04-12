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
    private static final String ERRO_MUITOS_AMULETOS = "Mais do que 1 amuleto no personagem.";

    public Personagem cadastrar(Personagem personagem){
        somarForcaDefesa(personagem);

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
        personagem.setId(personagemSalvo.getId());
        buscarAmuletoDoPersonagem(personagem.getId());

        return personagem;
    }

    public List<Personagem> listarPersonagens() {
        List<PersonagemEntity> personagens = repository.findAll();

        return personagens.stream()
                .map(personagemEntity -> {
                    Personagem personagem = PersonagemMapper.entityParaDomain(personagemEntity);

                    return personagem;
                })
                .toList();
    }

    public Personagem buscarPorId(Long id) {
        Optional<PersonagemEntity> personagemEntity = repository.findById(id);

        if (personagemEntity.isEmpty()){
            throw new RuntimeException(ERRO_PERSONAGEM_NAO_ENCONTRADO);
        }

        Personagem personagem = PersonagemMapper.entityParaDomain(personagemEntity.get());
        System.out.println(personagem.getItensMagicos());

        return personagem;
    }

    public List<ItemMagico> listarItensMagicosDoPersonagem(Long idPersonagem) {
        Personagem personagem = buscarPorId(idPersonagem);
        return personagem.getItensMagicos();
    }

    public ItemMagico buscarAmuletoDoPersonagem(Long idPersonagem) {
        List<ItemMagico> itens = listarItensMagicosDoPersonagem(idPersonagem);
        ItemMagico amuleto = new ItemMagico();
        int contador = 0;

        for (ItemMagico item : itens) {
            if (item.getTipoItem() == TipoItemEnum.AMULETO){
                contador++;
                System.out.println(contador);
                amuleto = item;
            }
        }

        if (contador > 1){
            throw new RuntimeException(ERRO_MUITOS_AMULETOS);
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

        buscarAmuletoDoPersonagem(personagem.getId());

        return personagem;
    }

    public Personagem removerItem(Long id, ItemMagico itemMagico) {
        Personagem personagem = buscarPorId(id);

        ItemMagico itemMagicoRemove = itemMagicoService.buscarPorId(itemMagico.getId());

        personagem.getItensMagicos().remove(itemMagicoRemove);

        itemMagicoRemove.setPersonagem(null);


        itemMagicoService.cadastrar(itemMagicoRemove);

        return personagem;
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public void somarForcaDefesa(Personagem personagem){
        int forcaPersonagem = personagem.getForca();
        int defesaPersonagem = personagem.getDefesa();

        for (ItemMagico item : personagem.getItensMagicos()) {
            forcaPersonagem += item.getForca();
            defesaPersonagem += item.getDefesa();
        }

        personagem.setForca(forcaPersonagem);
        personagem.setDefesa(defesaPersonagem);
    }
}
