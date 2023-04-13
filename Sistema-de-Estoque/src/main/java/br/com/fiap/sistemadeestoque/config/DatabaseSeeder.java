package br.com.fiap.sistemadeestoque.config;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import br.com.fiap.sistemadeestoque.models.Item;
import br.com.fiap.sistemadeestoque.repository.ItemRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
            new Item((long) 1, "Mouse", new BigDecimal(149), "HyperX", "Mouse HyperX Pulsefire Core RGB"),
            new Item((long) 2, "Teclado", new BigDecimal(300), "Onyx", "Teclado Mecanico Gamer Mancer Onyx MK2"),
            new Item((long) 3, "Monitor", new BigDecimal(450), "RedDragon", "Monitor G REDRAGON QUARTZ, 23.8 POL")
        ));
    }


    
}
