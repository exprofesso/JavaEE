package evilNerd.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("evilNerd")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({DatabaseConfig.class, ApplicationBeans.class})
public class ApplicationMainConfiguration {
}
