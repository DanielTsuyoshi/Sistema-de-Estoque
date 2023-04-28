package br.com.fiap.sistemadeestoque.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import br.com.fiap.sistemadeestoque.controllers.ItemController;
import br.com.fiap.sistemadeestoque.controllers.UsuarioController;
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
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "É necessário colocar o nome do usuário") 
    @Size(min = 5, max = 255)
    private String nome;

    @NotBlank(message = "O email é obrigatório") 
    @Size(min = 5, max = 255)
    private String email;
    
    @NotBlank(message = "É necessário colocar o seu telefone") 
    @Size(min = 5, max = 255)
    private String telefone;

    @NotBlank(message = "O Cpf é obrigatório") 
    @Size(min = 5, max = 255)
    private String cpf;

    @NotBlank(message = "É necessário colocar a sua senha") 
    @Size(min = 5, max = 255)
    private String senha;

    @OneToMany
    private Item item;

    public EntityModel<Usuario> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(UsuarioController.class).show(id)).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(UsuarioController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ItemController.class).show(this.getItem().getId())).withRel("item")
        );
    }

}
