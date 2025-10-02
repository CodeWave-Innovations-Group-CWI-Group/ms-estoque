package br.edu.catolica.ms_estoque.controller;

import br.edu.catolica.ms_estoque.model.Item;
import br.edu.catolica.ms_estoque.repository.ItemRepository;
import br.edu.catolica.ms_estoque.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("listAll")
    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItem(@PathVariable Long id) {
        return itemRepository.findById(id).map(item -> ResponseEntity.ok(item)).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/quantidade")
    public Item atualizarQuantidade(@PathVariable Long id, @RequestBody Integer quantidade) {
        return itemService.atualizarQuantidade(id, quantidade);
    }

    @PatchMapping("/{id}/baixar")
    public void baixarDoEstoque(@PathVariable Long id, @RequestBody Integer quantidade) {
        itemService.atualizarQuantidade(id, -quantidade);
    }

    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }

    @PostMapping("/listaDeItens")
    public List<Item> listaDeItens(@RequestBody List<Long> ids){
        return itemService.listaDeItens(ids);
    }

    @PutMapping("/atualizarItem")
    public Item atualizarItem(@PathVariable Long id, @RequestBody Item itemAtualizado){
        return itemService.atualizarItem(id, itemAtualizado);
    }

}
