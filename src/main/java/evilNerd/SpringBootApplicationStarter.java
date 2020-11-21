package evilNerd;


import evilNerd.config.ApplicationBeans;
import evilNerd.config.DatabaseConfig;
import evilNerd.config.WebBeansConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages =  "evilNerd")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({DatabaseConfig.class, ApplicationBeans.class, WebBeansConfig.class})
public class SpringBootApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStarter.class, args);
    }
}
