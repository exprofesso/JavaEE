package evilNerd.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("google.video.comedy")

public class GoogleConfig {

    private  String  in6;

    private String password;

    private String city;

    private String since;

    private String peole;


}
