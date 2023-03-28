package br.com.fiap.sistemadeestoque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
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

    protected Usuario(){
        
    }
    
    public Usuario(String nome, String email, String telefone, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf + ", senha="
                + senha + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
