package br.com.fiap.sistemadeestoque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.sistemadeestoque.models.Usuario;
import br.com.fiap.sistemadeestoque.repository.ItemRepository;
import br.com.fiap.sistemadeestoque.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca,@PageableDefault(size = 5) Pageable pageable){

        Page<Usuario> usuarios = (busca == null) ?
            usuarioRepository.findAll(pageable):
            usuarioRepository.findByNomeContaining(busca, pageable);
        
        return assembler.toModel(usuarios.map(Usuario::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Usuario usuario){
        log.info("cadastrando usuario " + usuario);
        usuarioRepository.save(usuario);
        usuario.setItem(itemRepository.findById(usuario.getItem().getId()).get());
        return ResponseEntity
            .created(usuario.toEntityModel().getRequiredLink("self").toUri())
            .body(usuario.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Usuario> show(@PathVariable Long id){
        log.info("Buscar usuario " + id);
        var usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        return usuario.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("Apagando usuario " + id);
        var usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao apagar o usuário"));
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        log.info("Atualizando usuarios " + id);
        usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        usuario.setId(id);
        usuarioRepository.save(usuario);

        return usuario.toEntityModel();
    }

}
