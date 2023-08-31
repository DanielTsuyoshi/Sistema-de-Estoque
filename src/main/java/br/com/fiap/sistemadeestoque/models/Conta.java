package br.com.fiap.sistemadeestoque.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import br.com.fiap.sistemadeestoque.controllers.ItemController;
import br.com.fiap.sistemadeestoque.controllers.ContaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Conta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "É necessário colocar o nome da Conta") 
    @Size(min = 5, max = 255)
    private String nome;
    
    @NotBlank(message = "É necessário colocar o seu telefone") 
    @Size(min = 5, max = 255)
    private String telefone;

    @NotBlank(message = "O Cpf é obrigatório") 
    @Size(min = 5, max = 255)
    private String cpf;

    @OneToMany
    private Item item;

    public EntityModel<Conta> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(ContaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ContaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ContaController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ItemController.class).show(this.getItem().getId())).withRel("item")
        );
    }

}
