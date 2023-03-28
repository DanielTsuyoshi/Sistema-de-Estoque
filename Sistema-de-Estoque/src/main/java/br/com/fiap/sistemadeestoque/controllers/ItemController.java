package br.com.fiap.sistemadeestoque.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.sistemadeestoque.models.Item;
import br.com.fiap.sistemadeestoque.repository.ItemRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    Logger log = LoggerFactory.getLogger(ItemController.class);

    List<Item> items = new ArrayList<>();

    @Autowired
    ItemRepository repository;

    @GetMapping
    public List<Item> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Item item){
        log.info("cadastrando items " + item);

        repository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        log.info("Buscar item " + id);
        var itemEncontrado = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        
        return ResponseEntity.ok(itemEncontrado);
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> destroy(@PathVariable Long id){
        log.info("Apagando item " + id);
        var itemEncontrado = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao apagar. Item não encontrado"));
            
        repository.delete(itemEncontrado);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody @Valid Item item){
        log.info("Atualizando items " + id);
        repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        item.setId(id);
        repository.save(item);

        return ResponseEntity.ok(item);
    }

}