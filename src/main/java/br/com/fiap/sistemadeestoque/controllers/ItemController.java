package br.com.fiap.sistemadeestoque.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/items")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Items", description = "Items do usuário")
public class ItemController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    @Operation(
        summary = "Listar items", 
        description = "Retorna todas os items cadastrados"
    )
    public List<Item> index(){
        return itemRepository.findAll();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos")
        }
    )

    public ResponseEntity<Item> create(@RequestBody @Valid @ParameterObject Item item){
        log.info("cadastrando items " + item);
        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> show(@PathVariable Long id){
        log.info("Buscar item " + id);
        return ResponseEntity.ok( getItem(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Item> destroy(@PathVariable Long id){
        log.info("Apagando item " + id);
        itemRepository.delete(getItem(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody @Valid Item item){
        log.info("Atualizando items " + id);
        getItem(id);
        item.setId(id);
        itemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    private Item getItem(Long id) {
        return itemRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }

}