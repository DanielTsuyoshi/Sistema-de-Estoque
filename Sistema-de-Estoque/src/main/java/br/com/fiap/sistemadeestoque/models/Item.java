package br.com.fiap.sistemadeestoque.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "É necessário colocar o nome do produto") 
    @Size(min = 5, max = 255)
    private String produto;
    @Min(0)
    private BigDecimal valor;
    @NotBlank(message = "É necesário colocar o nome da marca")
    @Size(min = 5, max = 255)
    private String marca;
    @NotBlank(message = "É necessário colocar a sua descrição")
    @Size(min = 5, max = 255)
    private String descricao;

    protected Item(){
        
    }

    public Item(String produto, BigDecimal valor, String marca, String descricao) {
        this.produto = produto;
        this.valor = valor;
        this.marca = marca;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Item [produto=" + produto + ", valor=" + valor + ", marca=" + marca + ", descricao=" + descricao + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
