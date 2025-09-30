package br.edu.catolica.ms_estoque.service;

import br.edu.catolica.ms_estoque.model.Item;
import br.edu.catolica.ms_estoque.repository.ItemRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Item> listaDeItens(List<Long> ids){

        return itemRepository.findBatchItems(ids);
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }
}
