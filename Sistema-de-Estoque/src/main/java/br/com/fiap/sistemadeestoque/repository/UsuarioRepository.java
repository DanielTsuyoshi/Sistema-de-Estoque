package br.com.fiap.sistemadeestoque.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.sistemadeestoque.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByTelefone(String telefone);
    List<Usuario> findByCpf(String cpf);
    List<Usuario> findBySenha(String senha);

}