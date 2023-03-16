package br.com.fiap.sistemadeestoque.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sistemadeestoque.models.Item;

@RestController
public class ItemController {

    Logger log = LoggerFactory.getLogger(ItemController.class);

    private List<Item> items = new ArrayList<>();
    
    @GetMapping("/api/items")
    public List<Item> index(){
        return items;
    }

    @PostMapping("/api/item")
    public ResponseEntity<Item> create(@RequestBody Item item){
        log.info("Cadastrar Item: " + item);
        item.setId(items.size() + 1l);
        items.add(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/api/item/{id}")
    public ResponseEntity<Item> show(@PathVariable Long id){
        log.info("Buscar item " + id);
        var itemEncontrado = items
            .stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();
        if(itemEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            return ResponseEntity.ok(itemEncontrado.get());
        
    }

    @DeleteMapping("/api/item/{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id){
        var itemEncontrado = items
            .stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();

        if(itemEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            
        items.remove(itemEncontrado.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/item/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item){
        var itemEncontrado = items
            .stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();

        if(itemEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            
        items.remove(itemEncontrado.get());
        item.setId(id);
        items.add(item);
        return ResponseEntity.ok(item);
    }

}