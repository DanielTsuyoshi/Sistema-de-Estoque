package br.com.fiap.sistemadeestoque.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sistemadeestoque.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByTelefone(String telefone);
    List<Usuario> findByCpf(String cpf);
    List<Usuario> findBySenha(String senha);

    // @Query("SELECT n FROM Usuario n WHERE n.nome LIKE %?1% ")
    Page<Usuario> findByNomeContaining(String busca, Pageable pageable);

    // @Query("SELECT n FROM Usuario n ORDER BY n.id LIMIT ?1 OFFSET ?2")
    // List<Usuario> buscarPaginado(int tamanho);

}