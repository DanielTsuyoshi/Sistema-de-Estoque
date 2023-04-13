package br.com.fiap.sistemadeestoque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
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

}
