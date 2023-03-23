package br.com.fiap.sistemadeestoque.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produto;
    private BigDecimal valor;
    private String marca;
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
