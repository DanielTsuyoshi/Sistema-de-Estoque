package br.com.fiap.sistemadeestoque.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sistemadeestoque.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByNome(String nome);
    List<Conta> findByTelefone(String telefone);
    List<Conta> findByCpf(String cpf);

    // @Query("SELECT n FROM Usuario n WHERE n.nome LIKE %?1% ")
    Page<Conta> findByNomeContaining(String busca, Pageable pageable);

    // @Query("SELECT n FROM Usuario n ORDER BY n.id LIMIT ?1 OFFSET ?2")
    // List<Usuario> buscarPaginado(int tamanho);

}