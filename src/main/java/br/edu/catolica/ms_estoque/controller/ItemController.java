package br.edu.catolica.ms_estoque.controller;

import br.edu.catolica.ms_estoque.model.Item;
import br.edu.catolica.ms_estoque.repository.ItemRepository;
import br.edu.catolica.ms_estoque.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/save")
    public Item criarItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping
    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Item buscarItem(@PathVariable Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/quantidade")
    public Item atualizarQuantidade(@PathVariable Long id, @RequestBody Integer quantidade) {
        return itemService.atualizarQuantidade(id, quantidade);
    }

    @PostMapping("/{id}/baixar")
    public void baixarDoEstoque(@PathVariable Long id, @RequestBody Integer quantidade) {
        itemService.atualizarQuantidade(id, -quantidade);
    }

    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }
}
