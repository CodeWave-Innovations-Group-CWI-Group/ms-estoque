package br.edu.catolica.ms_estoque.repository;

import br.edu.catolica.ms_estoque.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(
            """
            select it from Item it
            where it.id in :ids
            """)
    List<Item> findBatchItems(List<Long> ids);
}
