package br.com.fiap.sistemadeestoque.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.sistemadeestoque.models.Item;
import br.com.fiap.sistemadeestoque.models.Conta;
import br.com.fiap.sistemadeestoque.repository.ItemRepository;
import br.com.fiap.sistemadeestoque.repository.ContaRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {
        Item i1 = new Item((long) 1, "Mouse", new BigDecimal(149), "HyperX", "Mouse HyperX Pulsefire Core RGB");
        Item i2 = new Item((long) 2, "Teclado", new BigDecimal(300), "Onyx", "Teclado Mecanico Gamer Mancer Onyx MK2");
        Item i3 = new Item((long) 3, "Monitor", new BigDecimal(450), "RedDragon", "Monitor G REDRAGON QUARTZ, 23.8 POL");
        itemRepository.saveAll(List.of(i1,i2,i3));

        contaRepository.saveAll(List.of(
            Conta.builder().item(i1).nome("João").telefone("91234-5678").cpf("123.456.789-11").build(),
		    Conta.builder().item(i1).nome("Maria").telefone("98765-4321").cpf("234.567.890-12").build(),
            Conta.builder().item(i1).nome("Eduardo").telefone("93456-4567").cpf("345.678.901-23").build(),
            Conta.builder().item(i1).nome("Paula").telefone("94567-8524").cpf("456.654.879-20").build(),
            Conta.builder().item(i1).nome("Alice").telefone("99876-7413").cpf("789.462.798-22").build()
        ));

    }


    
}