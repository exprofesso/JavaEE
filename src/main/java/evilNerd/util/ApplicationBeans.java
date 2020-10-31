package evilNerd.util;

import evilNerd.domain.Cars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {
    @Bean
    public Cars getCar(){
        return Cars.builder()
                .id(1L)
                .model("Opel")
                .price(23000F)
                .creationYear(2020)
                .color("BLACK")
                .userId(2L)
                .build();
    }

}
