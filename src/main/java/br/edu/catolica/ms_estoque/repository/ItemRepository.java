package br.edu.catolica.ms_estoque.repository;

import br.edu.catolica.ms_estoque.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
