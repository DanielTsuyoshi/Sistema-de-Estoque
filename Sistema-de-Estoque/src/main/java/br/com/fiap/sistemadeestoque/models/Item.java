package br.com.fiap.sistemadeestoque.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Item {
    
    public Item(long l, String string, BigDecimal bigDecimal, String string2, String string3) {
    }

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

    
}
