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

import br.com.fiap.sistemadeestoque.models.Conta;
import br.com.fiap.sistemadeestoque.repository.ItemRepository;
import br.com.fiap.sistemadeestoque.repository.ContaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/contas")
@Slf4j
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    
    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca,@PageableDefault(size = 5) Pageable pageable){

        Page<Conta> contas = (busca == null) ?
            contaRepository.findAll(pageable):
            contaRepository.findByNomeContaining(busca, pageable);
        
        return assembler.toModel(contas.map(Conta::toEntityModel));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Conta conta){
        log.info("cadastrando conta " + conta);
        contaRepository.save(conta);
        conta.setItem(itemRepository.findById(conta.getItem().getId()).get());
        return ResponseEntity
            .created(conta.toEntityModel().getRequiredLink("self").toUri())
            .body(conta.toEntityModel());
    }

    @GetMapping("{id}")
    public EntityModel<Conta> show(@PathVariable Long id){
        log.info("Buscar conta " + id);
        var conta = contaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrado"));

        return conta.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id){
        log.info("Apagando conta " + id);
        var conta = contaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao apagar o usuário"));
        contaRepository.delete(conta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta conta){
        log.info("Atualizando contas " + id);
        contaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrado"));

        conta.setId(id);
        contaRepository.save(conta);

        return conta.toEntityModel();
    }

}
