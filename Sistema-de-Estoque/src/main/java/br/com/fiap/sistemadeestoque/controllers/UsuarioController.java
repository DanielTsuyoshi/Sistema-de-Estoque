package br.com.fiap.sistemadeestoque.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ItemRepository itemRepository;
    
    @GetMapping
    public Page<Usuario> index(@RequestParam(required = false) String busca,@PageableDefault(size = 5) Pageable pageable){
        if (busca == null) 
            return usuarioRepository.findAll(pageable);     
        return usuarioRepository.findByNomeContaining(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid Usuario usuario){
        log.info("cadastrando usuario " + usuario);
        usuarioRepository.save(usuario);
        usuario.setItem(itemRepository.findById(usuario.getItem().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id){
        log.info("Buscar usuario " + id);
        return ResponseEntity.ok( getUsuario(id));
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("Apagando usuario " + id);
        usuarioRepository.delete(getUsuario(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
        log.info("Atualizando usuarios " + id);
        getUsuario(id);
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    private Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

}
