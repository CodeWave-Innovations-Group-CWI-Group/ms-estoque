package br.edu.catolica.ms_estoque.service;

import br.edu.catolica.ms_estoque.model.Item;
import br.edu.catolica.ms_estoque.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public Item atualizarQuantidade(Long id, Integer quantidade){
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item nao encontrado"));

        Integer novaQuantidade = item.getQntdEmEstoque() + quantidade;

        if(novaQuantidade < 0){
            throw new IllegalArgumentException("Nao é permitido o estoque ficar negativo");
        }

        item.setQntdEmEstoque(novaQuantidade);
        return itemRepository.save(item);
    }

    public void baixarEstoque(Long id, Integer qntdUsada){
        atualizarQuantidade(id, qntdUsada);
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }
}
