package br.edu.catolica.ms_estoque.service;

import br.edu.catolica.ms_estoque.model.Item;
import br.edu.catolica.ms_estoque.repository.ItemRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item atualizarQuantidade(Long id, Integer quantidade){
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item nao encontrado"));

        Integer novaQuantidade = item.getQntdEmEstoque() + quantidade;

        if(novaQuantidade < 0){
            throw new IllegalArgumentException("Nao é permitido o estoque ficar negativo");
        }

        item.setQntdEmEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    public Item[] listaDeItens(Long[] listaDeId){

        Item[] itens = {};
        for(int i = 0; i < listaDeId.length; i++){
            int finalI = i;
            Item item = itemRepository.findById(listaDeId[i]).orElseThrow(()
                    -> new RuntimeException("Item nao encontrado com o ID: " + listaDeId[finalI]));
            itens[i] = item;
        }
        return itens;
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }
}
