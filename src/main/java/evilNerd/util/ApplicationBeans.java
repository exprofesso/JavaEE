package evilNerd.util;

import evilNerd.domain.Cars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationBeans {
    @Bean
    public Cars cars(){
        return Cars.builder()
                .id(1L)
                .model("Opel")
                .price(23000F)
                .creationYear(2020)
                .color("BLACK")
                .userId(2L)
                .build();
    }
    @Bean
    @Primary
    public Cars cars1(){
        return Cars.builder()
                .id(2L)
                .model("Mersedes")
                .price(123000F)
                .creationYear(2019)
                .color("RED")
                .userId(4L)
                .build();
    }

}
