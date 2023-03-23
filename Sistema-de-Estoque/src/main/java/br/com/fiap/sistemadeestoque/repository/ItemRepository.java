package br.com.fiap.sistemadeestoque.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.sistemadeestoque.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByProduto(String produto);
    List<Item> findByValorGreaterThan(BigDecimal valor);
    List<Item> findByMarca(String marca);
    List<Item> findByDescricao(String descricao);

}
